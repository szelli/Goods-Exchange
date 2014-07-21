var productCtrl = angular.module('productCtrl', ['ui.bootstrap']);

productCtrl.controller('productUploadCtrl', ['$scope', '$rootScope', '$http', 'productServices', '$location',
function($scope, $rootScope, $http, productServices, $location) {

	$scope.product = {};
	$scope.product.ownerId = $rootScope.loggedUser.id;
	$scope.product.categoryName = "Kategória";
	$scope.product.cityName = "Város";
	$scope.product.images = [];
    $scope.previewImages = [];
    $scope.showPreviewImages = false;
	$scope.category_active = false;
	$scope.city_active = false;
	$scope.error_category = false;
	$scope.error_city = false;
	$scope.error_offpristine = false;
	$scope.productUpErr_message = "";
	
	$scope.toggleCityActive = function(){
	    $scope.city_active = !$scope.city_active;
	};
	
	$scope.toggleCategoryActive = function(){
	    $scope.category_active = !$scope.category_active;
	};
	
	$scope.setSelectedCity = function (SelectedCity) {
		$scope.product.cityName = SelectedCity.city;
		$scope.product.cityId = SelectedCity.id;
		$scope.error_city = false;
	};
	
	$scope.setSelectedCategory = function (SelectedCategory) {
		$scope.product.categoryName = SelectedCategory.name;
		$scope.product.categoryId = SelectedCategory.id;
		$scope.error_category = false;
	};
	
	$scope.imagesUpload = function(){
        if($scope.images){
            for(var i = 0; i<$scope.images.length; i++){
                $scope.product.images.push($scope.images[i]);
            }

            if($scope.previewImages.length){
                $scope.showPreviewImages = true;
                $scope.imageUploadLabel = "";
            };
        }
    };
	
	$scope.convertImages = function (images){
        if (images && images[0]) {
            for(var i=0; i<images.length; i++){
                var reader = new FileReader();

                reader.onload = function (e) { 
                    $scope.previewImages.push( e.target.result );
                };
                reader.readAsDataURL(images[i]);
            }
        }
    };
    
    $scope.removeImage = function(index, reverse){
        var itemIndex;
        if(reverse){
            itemIndex = $scope.previewImages.length-1-index;
        }else{
            itemIndex = index;
        }
        $scope.product.images.splice(itemIndex, 1);
        $scope.previewImages.splice(itemIndex, 1);
        
        if(!$scope.previewImages.length){
            $scope.showPreviewImages = false;
            $scope.imageUpload.productImages.$setValidity('selectimages', false);
        }
    };
	
	$scope.label = "Termék feltöltés";
	$scope.btnlabel = "Termék feltöltés";
	
	$scope.productUpload = function(form, imageForm){
		if($scope.product.categoryName=="Kategória"){
		    $scope.error_category = true;
		}else{
		    $scope.error_category = false;
		}
		
		if($scope.product.cityName=="Város"){
		    $scope.error_city = true;
		}else{
		    $scope.error_city = false;
		}
		if(form.$valid && imageForm.$valid && $scope.showPreviewImages){
			$scope.error_offpristine = false;
			productServices.saveProduct($scope.product).success(function(status_message){
			    if(status_message == "ok"){
			    	alert("A terméket mentettük.");
			    	$location.path("/product");
			    }else{
			        $scope.productUpErr_message = status_message;
			    }
			}).error(function(){
			    $scope.productUpErr_message = "Hiba történt a feltötésben!";
			});		
		}else {
		    $scope.error_offpristine = true;
		}
	};
}]);