var services = angular.module('myproject.services', ['ngResource']);

services.factory('Lookups', ['$http', LookupService]);

services.factory('Messages', ['$timeout', MessageService]);

services.factory('QuickSearchService', ['$http', QuickSearchService]);