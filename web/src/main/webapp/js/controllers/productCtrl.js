var productCtrl = angular.module('productCtrl', []);

productCtrl.controller('productUploadCtrl', ['$scope', '$rootScope', '$http', 'productServices', '$location',
function($scope, $rootScope, $http, productServices, $location) {

	$scope.product = {};
	$scope.product.id = null;
	$scope.product.ownerId = $rootScope.loggedUser.id;
	$scope.product.categoryName = "Kategória";
	$scope.product.cityName = "Város";
	$scope.product.description = "";
	$scope.product.area = null;
	$scope.product.images = null;
    $scope.previewImages = [];
    $scope.showPreviewImages = false;
	$scope.category_active = false;
	$scope.city_active = false;
	$scope.error_category = false;
	$scope.error_city = false;
	$scope.error_offpristine = false;
	$scope.productUpErr = false;
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
        if($scope.previewImages.length){
            $scope.showPreviewImages = true;
        };
    };
	
	$scope.convertImages = function (input){
        if (input.files && input.files[0]) {
            $scope.previewImages = [];
            for(i=0; i<input.files.length; i++){
                var reader = new FileReader();

                reader.onload = function (e) { 
                    $scope.previewImages.push( e.target.result );
                };
                reader.readAsDataURL(input.files[i]);
            }
        }
    };
	
	$scope.label = "Termék feltöltés";
	$scope.btnlabel = "Feltöltés";
	
    
	
	$scope.productUpload = function(form, imageForm){
		$scope.productUpErr = false;
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
			console.log($scope.product);
			$scope.error_offpristine = false;
			productServices.saveProduct($scope.product).success(function(status_message){
			    if(status_message == "ok"){
			    	alert("A terméket mentettük.");
			    	$location.path("/product");
			     }else{
			         $scope.productUpErr = true;
			         $scope.productUpErr_message = status_message;
			     }
			 }).error(function(){
			     $scope.productUpErr = true;
			     $scope.productUpErr_message = "Hiba történt a feltötésben!";
			});		
		}else {
		    $scope.error_offpristine = true;
		}
    
	};
}]);

productCtrl.controller('productUpdateCtrl', ['$scope', '$rootScope', '$http', 'productServices', '$location',
function($scope, $rootScope, $http, productServices, $location) {
	
	$scope.product = {};
	$scope.product.id = null;
	$scope.product.ownerId = $rootScope.loggedUser.id;
	$scope.product.categoryName = "Kategória";
	$scope.product.cityName = "Város";
	$scope.product.description = "";
	$scope.product.area = null;
	
	$scope.category_active = false;
	$scope.city_active = false;
	$scope.error_category = false;
	$scope.error_city = false;
	$scope.error_offpristine = false;
	$scope.productUpErr = false;
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
	
	$scope.label = "Termék szerkesztés";
    $scope.btnlabel = "Szerkesztés";
    
    $scope.product = $rootScope.currentProduct;
    
    $scope.productUpload = function(form){
    	if(form.$valid){
    		$scope.error_offpristine = false;
			productServices.updateProduct($scope.product).success(function(status_message){
				if(status_message == "ok"){
					alert("A terméket sikeresen módosítottuk.");
					$location.path("/product");
				}else{
				     $scope.productUpErr = true;
				     $scope.productUpErr_message = status_message;
				}
			}).error(function(){
		    $scope.productUpErr = true;
		    $scope.productUpErr_message = "Hiba történt a módosításban!";
		 });
	    }else {
		    $scope.error_offpristine = true;
		}
    };
    
}]);