var controllers = angular.module('myproject.controllers', ['ui.bootstrap', 'ui.sortable', 'ui.select2', 'ui.unique']);

function tmpl(name) {
	return 'tmpl/' + encodeURIComponent(name);
}

controllers.controller('HeaderCtrl', ['$scope', '$location', '$state', '$http', '$cookieStore', 'Lookups', 'QuickSearchService', '$rootScope', HeaderCtrl]);

controllers.controller('HomeCtrl', ['$scope', '$http',  '$timeout',  'Lookups','$state',HomeCtrl]);

controllers.controller('ModalMessagesCtrl', ModalMessagesCtrl);

controllers.controller('MessagesCtrl', MessagesCtrl);

controllers.controller('UtilCtrl', ['$scope', 'Lookups', '$modal', '$http', UtilCtrl]);



