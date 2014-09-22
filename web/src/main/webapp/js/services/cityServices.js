var services = angular.module('services');

services.factory('cityServices', function($http) {
	var city = {};
	
	city.getCities = function() {
		return $http({
			url : 'api/cityResponse',
			method : "GET",
			contentType : "application/json"
		});
	};
	
	city.getCounties = function() {
		return $http({
			url : 'api/getCountiesRequest',
			method : "POST",
			contentType: "application/json"
		});
	};
	
	return city;
});
