var services = angular.module('services');

services.factory('forRentServices', function($http) {
	var forRent = {};

	forRent.saveForRent = function(productId, start, end) {
		return $http({
			url : 'api/saveForRentRequest',
			data : {
				"productId" : productId,
                "fromDate" : start,
				"toDate" : end
            },
			method : "POST",
			contentType : "application/json"
		});
	};
    
    forRent.getForRentsByProduct = function(productId) {
		return $http({
			url : 'api/getForRentsByProduct',
			data : {
				"id" : productId
            },
			method : "POST",
			contentType : "application/json"
		});
	};
	return forRent;
});