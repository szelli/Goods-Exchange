var productUploadCtrl = angular.module('productUploadCtrl', []);

productUploadCtrl.controller('productUploadCtrl', ['$scope', '$rootScope', '$http', 'productServices', 'cityServices', 'cityServices',
function($scope, $rootScope, $http, productServices, cityServices, cityServices) {
	
	$scope.newProduct = {};
	$scope.newProduct.ownerId = 258;//$rootScope.loggedUser.id;
	$scope.productUpErr = false;
	$scope.productUpErr_message = "";
	$scope.newProduct.category = "Kategória";
    $scope.newProduct.city = "Város";
    $scope.category_active = false;
    $scope.city_active = false;
    $scope.error_category = false;
    $scope.error_city = false;
    $scope.error_offpristine = false;
    
    //console.log($scope.cities['id']);
	
	$scope.setCategory = function(category){
        $scope.newProduct.category = category;
    };
    
    $scope.setCity = function(city){
        $scope.newProduct.city = city;
    };
    
    $scope.toggleCityActive = function(){
        $scope.city_active = !$scope.city_active;
    };
    
    $scope.toggleCategoryActive = function(){
        $scope.category_active = !$scope.category_active;
    };
    
    cityServices.getCities().success(function(cities){
		console.log(cities);
    });
    
    $scope.dropdown = function(form){
        $scope.productUpErr = false;
        if($scope.newProduct.category=="Kategória"){
            $scope.error_category = true;
        }else{
            $scope.error_category = false;
        }
        
        if($scope.newProduct.city.name=="Város"){
            $scope.error_city = true;
        }else{
            $scope.error_city = false;
        }
    };
	
	$scope.productUpload = function(form){
		console.log("kjasdhihsdkihjdlk");
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
	 };
}]);