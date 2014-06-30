var cityCtrl = angular.module('cityCtrl', []);

cityCtrl.controller('cityCtrl', ['$scope', '$http', 'cityServices', '$rootScope',
function($scope, $http, cityServices, $rootScope) {
	
	$scope.city_error = false;
	$scope.city_error_message = "";
	
	
}]);