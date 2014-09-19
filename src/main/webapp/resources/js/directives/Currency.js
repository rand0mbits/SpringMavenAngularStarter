var Currency = function ($filter) {
	function link($scope, $element, $attrs, ngModel) {
		if(!ngModel) return;
		
		ngModel.$render = function() {
			$element.val(ngModel.$viewValue || '');
		};
		
		ngModel.$formatters.push(function (value) {
			if(typeof value != 'undefined' && value != null) {
				return $filter('number')(value.toString(), 0);
			}
		});
		
		ngModel.$parsers.push(function (value) {
			// if the user begins entering a negative number, we want to be able to store
			// the "-" in the model before the next char is entered, -0 is a valid value for that.
			if (value === '-') return -0;
			if(typeof value != 'undefined') {
				// allow only digit characters, the period, and the minus sign.
				return parseInt(value.toString().replace(/[^\d\.-]/g,''));
			}
		});
		
		$element.on('change blur', function() {
			$scope.$apply(read);
		});
		
		function read() {
			ngModel.$setViewValue($element.val());
			ngModel.$modelValue = $element.val();
		}
	};
	
	return {
		restrict: 'A',
		require: '?ngModel',
		scope: {},
		link: link
	};
};