var services = angular.module('services');

services.factory('productServices', function($http/*, localStorageService*/) {
	var product = {};
	
	product.saveProduct = function(product) {
		return $http({
			url : 'api/productUpload',
            headers: {
                'Content-Type': undefined // --> makes sure the boundary is set in the Content-Type header, see result file
            },
            method: "POST",
            data: {
                product:{
                    "name" : product.name,
                    "categoryId" : product.categoryId,
                    "descriptions" : product.description,
                    "cityId" : product.cityId,
                    "area" : product.area,
                    "ownerId" : product.ownerId,
                    "uploadTime" : new Date()
                },
                productImages: product.images
            },
            transformRequest: function(data) {
                var fd = new FormData();
                angular.forEach(data, function(value, key) {
                    if(key=="productImages" && value){
                        for (var i = 0; i < value.length; i++) {
                            fd.append("file" + i, value[i]);
                        }
                    }else{
                        fd.append(key, JSON.stringify(value));
                    }
                });
                return fd;
            }
		});
	};
	
	/*product.updateProduct = function(product) {
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
	};*/ 
	
	return product;
});
