var productUploadCtrl = angular.module('productUploadCtrl', []);

productUploadCtrl.controller('productUploadCtrl', ['$scope', '$rootScope', '$http', 'productServices',
function($scope, $rootScope, $http, productServices) {
	
	$scope.newProduct = {};
	$scope.newProduct.ownerId = /*256;*/ $rootScope.loggedUser.id;
	$scope.newProduct.categoryName = "Kategória";
    $scope.newProduct.cityName = "Város";
    $scope.newProduct.description = "";
    $scope.newProduct.area = null;
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
    	$scope.newProduct.cityName = SelectedCity.city;
    	$scope.newProduct.cityId = SelectedCity.id;
    	$scope.error_city = false;
    };
    
	$scope.setSelectedCategory = function (SelectedCategory) {
		$scope.newProduct.categoryName = SelectedCategory.name;
		$scope.newProduct.categoryId = SelectedCategory.id;
		$scope.error_category = false;
 	};
	
 	$scope.productUpload = function(form){
        $scope.productUpErr = false;
        if($scope.newProduct.categoryName=="Kategória"){
            $scope.error_category = true;
        }else{
            $scope.error_category = false;
        }
        
        if($scope.newProduct.cityName=="Város"){
            $scope.error_city = true;
        }else{
            $scope.error_city = false;
        }
        if(form.$valid){	
				$scope.error_offpristine = false;
				 productServices.saveProduct($scope.newProduct).success(function(status_message){
				     if(status_message == "ok"){
				    	 alert("A terméket mentettük.");
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