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
                    "categoryId" : product.category.id,
                    "descriptions" : product.description,
                    "cityId" : product.city.id,
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
	
	product.updateProduct = function(product) {
		return $http({
			url : 'api/updateProduct',
			data : {
				"name" : product.name,
                "categoryId" : product.categoryId,
				"descriptions" : product.description,
				"cityId" : product.cityId,
				"area" : product.area,
				"status" : product.status
            },
			method : "POST",
			contentType : "application/json"
		});
	};
    
    product.loadProducts = function(datas) {
		return $http({
			url : 'api/productList',
			data : {
                "sort": datas.sort,
                "limit": datas.limit,
                "currentPage": datas.currentPage,
                "pageCount": datas.pageCount,
                "productsCount": datas.productsCount,
                "offset": datas.offset,
                "tab": datas.tab,
				"categoryId": datas.categoryId,
				"ownerId": datas.ownerId
                
            },
			method : "POST",
			contentType : "application/json"
		});
	};

	product.getProductsByOwner = function(id) {
	return $http({
			url : 'api/getProductsByOwner',
			data : {
				"ownerId": id
                
            },
			method : "POST",
			contentType : "application/json"
		});
	};
	
    product.GetProductsCount = function(ownerId, categoryId) {
        return $http({
			url : 'api/productsCount',
			data : {
				"categoryId": categoryId,
				"ownerId": ownerId	
			},
			method : "POST",
			contentType : "application/json"
		});
	};
	
	return product;
});
