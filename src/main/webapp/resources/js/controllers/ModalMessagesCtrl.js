var ModalMessagesCtrl = function($scope, $modalInstance, messages) {
	$scope.messages = messages;
	$scope.bgClass = {
		'error': 'bg-danger',
		'info': 'bg-success',
	};
	
	$scope.style = (function() {
		for (var i = 0; i < messages.length; i++) {
			if (messages[i].level == 'error') {
				return 'bg-danger';
			}
		}
		return 'bg-success';
	})();
};