var services = angular.module('services');

services.factory('categoryServices', function($http) {
	var category = {};
	
	category.getCategories = function() {
		return $http({
			url : 'api/categoryResponse',
			method : "GET",
			contentType : "application/json"
		});
	};
	return category;
});
