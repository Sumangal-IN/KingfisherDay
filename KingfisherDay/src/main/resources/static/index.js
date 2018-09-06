angular.module('kfApp', [])
   .controller('kfController', function($scope) {
	   $scope.showErrorInvalidEmail=false;
	   $scope.showErrorEmptyPassword=false;
	   $scope.showLoginPage=true;
	   
	   $scope.loginSubmit=function(){
		   if($scope.loginUsername==undefined || ($scope.loginUsername!=undefined && !$scope.loginUsername.endsWith('@tcs.com')))
		   {
			   $scope.showErrorInvalidEmail=true;
			   return;
		   }
		   else
		   {
			   $scope.showErrorInvalidEmail=false; 
		   }
		   if($scope.loginPassword==undefined || ($scope.loginPassword!=undefined && !$scope.loginPassword==null))
		   {
			   $scope.showErrorEmptyPassword=true;
			   return;
		   }
		   else
		   {
			   $scope.showErrorEmptyPassword=false;
		   }
		   $scope.showLoginPage=false;
	   }
	   
	   
});