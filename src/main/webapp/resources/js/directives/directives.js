var directives = angular.module('myproject.directives', []);

function tmpl(name) {
	return 'tmpl/' + encodeURIComponent(name);
}


directives.directive('currency', ['$filter', Currency]);


// Fixes select multiple inside modals in Chrome
directives.directive('multiFix', function () {
	return {
		restrict: 'A',
		link: function ($scope, $element, $attrs) {
			$element.on('click', function (evt) {
				evt.stopPropagation();
			});
		}
	};
});

directives.directive('errSrc', function() {
	  return {
	    link: function(scope, element, attrs) {
	      element.bind('error', function() {
	        element.attr('src', attrs.errSrc);
	      });
	    }
	  };
});



directives.directive("dynamicName",function($compile, $interpolate){
  return {
      restrict:"A",
      terminal:true,
      priority:1000,
//	      scope: {
//	    	elementName: '@dynamicName',  
//	      },
      link:function(scope,element,attrs){
          element.attr('name', $interpolate(element.attr('dynamic-name'))(scope));
          element.removeAttr("dynamic-name");
          $compile(element)(scope);
      }
   };
});

