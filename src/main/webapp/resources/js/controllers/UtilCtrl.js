var UtilCtrl = function($scope, lookups, $modal, $http) {

	
	$scope.messages = {
		newMessageType: 'info',
		newMessageText: '',
		// sample messages
		messages: [{level: 'info', value: 'info one'}, {level: 'info', value: 'info two'}, {level: 'warning', value: 'warning one'}, 
		           {level: 'error', value: 'error one'}],
	};
	$scope.messages.addNewMessage = function() {
		if ($scope.messages.newMessageText.trim().length > 0 && $scope.messages.newMessageType) {
			$scope.messages.messages.push({level: $scope.messages.newMessageType, value: $scope.messages.newMessageText});
		}
	};
	$scope.messages.submitMessages = function(options) {
		options = angular.extend({
			override: false,
		}, options);
		if ($scope.messages.messages.length > 0) {
			$http({
				url: 'utilities/messages?override=' + options.override,
				method: 'POST',
				nonBlockSpinner: true,
				confirmMethod: function() {
					$scope.messages.submitMessages({override: true});
				},
				data: $scope.messages.messages,
			});
		}
	};

};