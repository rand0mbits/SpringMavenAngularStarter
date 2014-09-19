var HomeCtrl = function($scope, $http, $timeout, Lookups,$state) {
	
	$scope.lastWeek=new Date();
	$scope.lastWeek.setDate($scope.lastWeek.getDate()-7);	
	$scope.lookups = Lookups.data;

    Lookups.promise.then(function(){
    });
    
    var model = $scope.model = {};
    
    model.welcomeText = "Welcome to the homepage, User!";

};
   
