var filters = angular.module('myproject.filters', []);

// Global Filters
filters.filter('dateFilter', DateFilter);
filters.filter('sumArrayOfObjects', SumArrayOfObjectsFilter);
filters.filter('boolYesNo', BoolYesNoFilter);
filters.filter('notNullYesNo', NotNullYesNoFilter);
filters.filter('numberSignFilter', NumberSignFilter);
filters.filter('currencyDollars', ['$filter', CurrencyDollarsFilter]);
filters.filter('currencyDollarsRounded', ['$filter', CurrencyDollarsRoundedFilter]);

