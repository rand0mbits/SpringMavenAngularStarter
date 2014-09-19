var MessagesCtrl = function($scope, Messages) {
	$scope.messages = function() {
		return Messages.messages;
	};
	$scope.successes = function() {
		return Messages.successCollections;
	};
	$scope.errors = function() {
		return Messages.errorCollections;
	};
	$scope.warnings = function() {
		return Messages.warningCollections;
	};
	$scope.bgClass = {
		'error': 'bg-danger',
		'info': 'bg-success',
	};

	
	$scope.removeMessage = function(message) {
		if (angular.isArray(message)) {
			Messages.removeMessages(message);
		}
		else {
			Messages.removeMessages([message]);
		}
	};

	
	$scope.addMessages = function(messages) {
		Messages.addMessages(messages);
	};
};