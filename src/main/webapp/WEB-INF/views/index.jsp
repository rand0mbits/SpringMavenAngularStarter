<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en" data-framework="angularjs" x-ng-app="myproject">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>MY PROJECT</title>
		<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/favicon.ico" />" />
		<meta name="description" content="">
		<!-- <meta name="viewport" content="width=device-width">-->
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" href="resources/css/bootstrap.css">
		<link rel="stylesheet" href="resources/css/ng-grid.css">
		<link rel="stylesheet" href="resources/css/ng-table.css">
		<link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="resources/css/main.css">
		<link rel="stylesheet" href="resources/css/select2.css">
		<style>[ng-cloak] { display: none; }</style>
	     
		<script src="resources/js/lib/modernizr-2.6.2-respond-1.1.0.min.js"></script>
	     
		<!-- LIB -->
		<script src="resources/js/lib/jquery-1.11.0.min.js"></script>
		<script src="resources/js/lib/jquery.ui.widget.js"></script>
		<script src="resources/js/lib/jquery.ui.mouse.js"></script>
		<script src="resources/js/lib/jquery.ui.sortable.js"></script>
		<script src="resources/js/lib/jquery-ui.js"></script>
		
		<script src="resources/js/lib/select2.js"></script>
		
		
		<script src="resources/js/lib/angular.js"></script>
		<script src="resources/js/lib/angular-cookies.js"></script>
		<script src="resources/js/lib/angular-ui-router.js"></script>
		<script src="resources/js/lib/angular-ui-sortable.js"></script>
		<script src="resources/js/lib/angular-ui-select2.js"></script>
		<script src="resources/js/lib/angular-ui-unique.js"></script>
		<script src="resources/js/lib/angular-resource.js"></script>
		<script src="resources/js/lib/ng-grid.js"></script>
		<script src="resources/js/lib/ng-grid-flexible-height.js"></script>
		<script src="resources/js/lib/ng-table.js"></script>
		<script src="resources/js/lib/multiselect-tpls.js"></script>
		<script src="resources/js/lib/checklist-model.js"></script>
		
		<script src="resources/js/lib/angular-file-upload.js"></script>
        
		<script src="resources/js/lib/ui-bootstrap-tpls-0.11.0.js"></script>
		
		<script src="resources/js/lib/bootstrap.js"></script>
        
		<!-- APP -->
		<script src="resources/js/util.js"></script>
		<script src="resources/js/app.js"></script>
		
		<!-- Controllers -->
		
		<script src="resources/js/controllers/HeaderCtrl.js"></script>
		<script src="resources/js/controllers/HomeCtrl.js"></script>
		<script src="resources/js/controllers/MessagesCtrl.js"></script>
		<script src="resources/js/controllers/ModalMessagesCtrl.js"></script>
		<script src="resources/js/controllers/UtilCtrl.js"></script>
		<script src="resources/js/controllers/controllers.js"></script>
		
		<!-- Services -->
		<script src="resources/js/services/LookupService.js"></script>
		<script src="resources/js/services/MessageService.js"></script>
		<script src="resources/js/services/QuickSearchService.js"></script>
		<script src="resources/js/services/services.js"></script>
		
		<!-- Directives -->
		<script src="resources/js/directives/Currency.js"></script>
		<script src="resources/js/directives/directives.js"></script>
		
		<!-- Filters -->
		<script src="resources/js/filters/GlobalFilters.js"></script>
		<script src="resources/js/filters/filters.js"></script>

</head>

<body>
	<div id="appwide-spinner" x-ng-show="blockingHttpRequests">
		<div id="appwide-backdrop"></div>
		<img src="resources/images/spinner-new.gif" width="150" height="150" />
	</div>
	<div id="nonblocking-spinner" x-ng-show="nonblockingHttpRequests">
		<img src="resources/images/spinner-new.gif" width="123" height="150" alt="">
	</div>
	<div id="content-wrapper">
		<div id="messages" x-ng-controller="MessagesCtrl" x-ng-include="'tmpl/messages|messages'">
		</div>

		<div id="team-header-bg"></div>
		<div id="nav-header-bg"></div>
		<div id="header" data-ng-controller="HeaderCtrl">
			Navigation: <a x-ui-sref="homepage">Home</a> | <a x-ui-sref="util">Util</a>
		</div>
		<div class="clearfix"></div>
		<ui-view autoscroll="false" />
		<div id="content"></div>
	</div>
</body>
</html>