var DateFilter = function () {
	return function (timestamp) {

		if(timestamp == null || timestamp=='') return ''; 
		
		var date = new Date(timestamp);
		var month = date.getMonth() + 1;
		var day = date.getDate();
		
		if(month < 10) {
			month = '0' + month;
		}
		
		if(day < 10) {
			day = '0' + day;
		}
		
		return month + '/' + day + '/' + date.getFullYear();
	};
};

var SumArrayOfObjectsFilter = function () {
	return function(arrayOfObjects, propertyToSum) {
		return util.sum(arrayOfObjects, propertyToSum);
	};
};

var BoolYesNoFilter = function () {
	return function(boolValue) {
		return Boolean(boolValue) ? 'Yes' : 'No';
	};
};

var NotNullYesNoFilter = function () {
	return function(notNullValue) {
		return (notNullValue != null) ? 'Yes' : 'No';
	};
};

var NumberSignFilter = function () {
	return function(num) {
		if(num == null || typeof num == 'undefined') return 0;
		return (num > 0) ? '+' + num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") : num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	};
};

var CurrencyDollarsFilter = function ($filter) {
	return function(amount) {
		if(amount == null || typeof amount == 'undefined') return '';
		return $filter('currency')(amount.toString(), '').replace(/\.00$/, '');
	};
};

var CurrencyDollarsRoundedFilter = function ($filter) {
	return function(amount) {
		if(amount == null || typeof amount == 'undefined') return '';
		return $filter('currency')(amount.toFixed().toString(), '').replace(/\.00$/, '');
	};
};

var CapFirstLetter = function() {
	return function(string) {
		if(string == null || typeof string == 'undefined') return '';
		return string.charAt(0).toUpperCase() + string.slice(1);
	};
};