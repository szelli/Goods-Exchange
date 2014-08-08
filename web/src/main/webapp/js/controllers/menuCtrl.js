var menuCtrl = angular.module('menuCtrl', []);

menuCtrl.controller('menuCtrl', ['$scope', '$rootScope',
function($scope, $rootScope) {
	
	$scope.loggedUser = function() {
		if($rootScope.loggedUser != null && $rootScope.loggedUser != '') {
			return true;
		} else {
			return false;
		}
	};
	
	$scope.isAdminUser = function() {
		if($rootScope.loggedUser.role === 1) {
			return true;
		} else {
			return false;
		}
	};
	
}]);