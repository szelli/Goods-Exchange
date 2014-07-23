var productCtrl = angular.module('productCtrl', ['ui.bootstrap']);

productCtrl.controller('productUploadCtrl', ['$scope', '$rootScope', '$http', 'productServices', '$location',
function($scope, $rootScope, $http, productServices, $location) {

	$scope.product = {};
	$scope.product.ownerId = $rootScope.loggedUser.id;
	$scope.product.images = [];
    $scope.previewImages = [];
    $scope.showPreviewImages = false;
	$scope.category_active = false;
	$scope.city_active = false;
	$scope.error_offpristine = false;
	$scope.productUpErr_message = "";
	
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