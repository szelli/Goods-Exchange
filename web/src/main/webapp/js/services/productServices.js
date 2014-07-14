var services = angular.module('services');

services.factory('productServices', function($http/*, localStorageService*/) {
	var product = {};
	
	product.saveProduct = function(product) {
		return $http({
			url : 'api/productUpload',
			data : {
				"name" : product.name,
                "categoryId" : product.categoryId,
				"descriptions" : product.description,
				"cityId" : product.cityId,
				"area" : product.area,
                "ownerId" : product.ownerId
            },
			method : "POST",
			contentType : "application/json"
		});
	};
	
	product.updateProduct = function(product) {
		return $http({
			url : 'api/updateProduct',
			data : {
				"id": product.id,
				"name" : product.name,
                "categoryId" : product.categoryId,
				"descriptions" : product.description,
				"cityId" : product.cityId,
				"area" : product.area,
                "ownerId" : product.ownerId
            },
			method : "POST",
			contentType : "application/json"
		});
	}; 
	
	return product;
});
