var services = angular.module('services');

services.factory('productServices', function($http/*, localStorageService*/) {
	var product = {};
	
	product.saveProduct = function(product) {
		return $http({
			url : 'api/productUpload',
			data : {
				"name" : product.name,
                "categoryId" : 1,
				"descriptions" : product.descriptions,
				"cityId" : 1,
				"area" : product.area,
                "ownerId" : product.ownerId
            },
			method : "POST",
			contentType : "application/json"
		});
	};
	return product;
});
