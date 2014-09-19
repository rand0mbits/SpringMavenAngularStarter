'use strict';
var myproject = angular.module('myproject', [
        'ngCookies',
        'ui.router',
        'myproject.controllers',
        'myproject.services',
        'myproject.directives',
        'myproject.filters',
        'ngGrid',
        'ngTable',
        'ui.multiselect',
        'angularFileUpload',
        'checklist-model'
]);

myproject.run(['$rootScope', '$state', '$stateParams', '$http', '$timeout', '$interval', '$cookieStore', '$location', '$modal',
          function ($rootScope, $state, $stateParams, $http, $timeout, $interval, $cookieStore, $location, $modal) {
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;
    $rootScope.modifiedForms = 0;
    $rootScope.activeHttpRequests = 0;
    $rootScope.blockingHttpRequests = 0; // block whole screen
    $rootScope.nonblockingHttpRequests = 0; // show nonblocking spinner
    $rootScope.tempCookies = []; // FOR REMOVING COOKIES ON SIGN OUT
    
    // responsible for logging the user out after a specified period of inactivity
    $rootScope.idleLogout = {
    		timeout: 30, // amount of inactivity in minutes after which the user is presented with the pre-timeout dialog
    		gracePeriod: 5, // how long (in minutes) after the pre-logout dialog, until the user is logged out
    		promise: undefined,
    		// function that restarts the timer
    		restartTimeout: function() {
    			//console.log('restarting inactivity timeout');
    			if (!angular.isUndefined($rootScope.idleLogout.promise)) {
    				$timeout.cancel($rootScope.idleLogout.promise);
    			}
    			$rootScope.idleLogout.promise = $timeout(function() {
    				console.log('initiating pre-logout');
    				$rootScope.idleLogout.preLogout();
    			}, $rootScope.idleLogout.timeout * 1000 * 60);
    		},
    		// function that begins the pre-logout process/grace period
    		preLogout: function() {
    			$modal.open({
    				templateUrl: util.tmpl('pre-idle-logout'),
    				backdrop: 'static',
    				controller: function($scope, $modalInstance, $timeout) {
    					var model = $scope.model = {};
    					console.log('starting pre-logout timer');
    					model.remainingGracePeriod = $rootScope.idleLogout.gracePeriod * 1000 * 60;
    					// if user doesn't click OK, this timer will log the user out.
    					var timerPromise = $timeout(function() {
    						console.log('logging out due to inactivity.');
    						$rootScope.logout();
    					}, model.remainingGracePeriod);
    					// decrement remaining time by 1 seconds every second. this is just for display purposes.
    					var decrementPromise = $interval(function() {
    						model.remainingGracePeriod -= 1000;
    					}, 1000);
    					// handler for when the user clicks the OK button on the timeout dialog
    					model.ok = function() {
    						console.log('canceling timeout');
    						$timeout.cancel(timerPromise);
    						$interval.cancel(decrementPromise);
    						$rootScope.idleLogout.restartTimeout();
    						// ping the server so that the session is kept alive.
    						$http.get('ping', {omitRequestId: true});
    						$modalInstance.dismiss('cancel');
    					};
    				}
    			});
    		}
    };
    $rootScope.idleLogout.restartTimeout();
    
    // logout function
    $rootScope.logout = function () {
		/*window.location.href = 'j_spring_security_logout';*/
	};
    
    // datepickers
    
	$rootScope.datepickerOptions = {
			showWeeks: false
		};
	
    $rootScope.openDatepicker = function($event, attachment) {
        $event.preventDefault();
        $event.stopPropagation();
      
        attachment.datepickerOpened = true;
    };
    
    $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams){
    	var rootScope = this;
    	
    	if (rootScope.modifiedForms > 0) {
    		var answer = confirm ("You have not saved the modifications on this page, are you sure you want to continue and lose them?");
    		if (answer) {
    			rootScope.modifiedForms = 0;
    		}
    		else {
    			event.preventDefault();
    		}
    	}
	}.bind($rootScope));
}]);

myproject.config(['$stateProvider', '$urlRouterProvider', '$httpProvider',
             function($stateProvider, $urlRouterProvider, $httpProvider) {
	
	// extra options available in the http config are: noSpinner, omitRequestId
	$httpProvider.interceptors.push(function($q, Messages, $rootScope) {
		function incrementHttpRequests(config) {
			 $rootScope.activeHttpRequests++;
    		 if (!config.nonBlockSpinner) {
    			 $rootScope.blockingHttpRequests++;
    		 }
    		 else {
    			 $rootScope.nonblockingHttpRequests++;
    		 }
		}
		function decrementHttpRequests(config) {
			if ($rootScope.activeHttpRequests) $rootScope.activeHttpRequests--;
	   		 if (!config.nonBlockSpinner && $rootScope.blockingHttpRequests) {
	   			 $rootScope.blockingHttpRequests--;
	   		 }
	   		 else if($rootScope.nonblockingHttpRequests) {
	   			 $rootScope.nonblockingHttpRequests--;
	   		 }
		}
		
	    return {
	     'request': function(config) {
	    	 if (!config.noSpinner && !config.url.startsWith('tmpl/') && !config.url.startsWith('template/')) {
	    		 //console.log('request for ' + config.url);
	    		 incrementHttpRequests(config);
	    	 }
	    	 if (!config.omitRequestId) {
	    		 config.headers['Request-Id'] = new Date().getTime();
	    	 }
	    	 //console.log('config headers', config.headers);
	    	 return config || $q.when(config);
	    	 
	      },
	 
	      'response': function(response) {
	    	  var isBootstrapTemplate = response.config.url.startsWith('template/');
	    	  var isProjectTemplate = response.config.url.startsWith('tmpl/');
	    	  var isTemplate = isProjectTemplate || isBootstrapTemplate;
	    	  if (!isTemplate || (isProjectTemplate && response.config.url.indexOf('pre-idle-logout') == -1)) {
	    		  $rootScope.idleLogout.restartTimeout();
	    	  }
	    	  
	    	  if (!response.config.noSpinner && !isTemplate) {
	    		  //console.log('response for ' + response.config.url);
	    		  // if the counter is already at 0, don't decrement it more.
	    		  decrementHttpRequests(response.config);
	    		  
	    	  }
	    	  //console.log('response headers', response.headers());
	    	  // check messages only if 1: not retrieving a template and 2: request-id header is present
	    	  if (!isTemplate && 'request-id' in response.headers()) {
	    		  //console.log('checking messages from: ' + response.config.method + ':' + response.config.url);
	    		  Messages.getMessages(response);
	    	  }
	    	  return response || $q.when(response);
	      },
	      'responseError': function(rejection) {
	    	  if (!rejection.config.noSpinner && !rejection.config.url.startsWith('tmpl/') && !rejection.config.url.startsWith('template/')) {
	    		  // if the counter is already at 0, don't decrement it more.
	    		  decrementHttpRequests(rejection.config);
	    	  }
	    	  return $q.reject(rejection);
	      }
	    };
	  });
	
	function tmpl(name) {
		return 'tmpl/' + encodeURIComponent(name);
	}
	
	var lookupsResolve = {
		lookups: function(Lookups) {
			return Lookups.promise;
		}
	};

	$urlRouterProvider.otherwise('/homepage');
	$stateProvider
		.state('homepage', {
			url: '/homepage', templateUrl: tmpl('homepage'), controller: 'HomeCtrl'
		})
		.state('util', {
			url: '/util', templateUrl: tmpl('util|util'), controller: 'UtilCtrl'
		});
}]);

myproject.run(function ($http, $modal, Messages) {
    Messages.http = $http;
    Messages.modal = $modal;
});