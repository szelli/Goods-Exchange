var productListingCtrl = angular.module('productListingCtrl', []);

productListingCtrl.controller('productListingCtrl', ['$scope', '$rootScope', '$http', 'productServices', 'userServices', '$location', 'sharedDatas', 'location',
function($scope, $rootScope, $http, productServices, userServices, $location, sharedDatas, location) {
    //var id = [];
	//var base = $location.url().split('#');
    //var url = $location.url().split('%');
    //var page = [];
	$scope.haveChildNode;
	$scope.showProductDetails = false;
	$scope.currentProduct = {};
	$scope.isEmpty = false;
	$scope.categoryId = null;
	$scope.products = [];
 	$scope.currentTab = {};
	$scope.totalItems = 0;
	$scope.currentPage = 1;
	$scope.itemsPerPage = 12;
	$scope.order = '-uploadTime';
	$scope.az = true;
	
	$scope.setCategory = function(id) {
		$scope.status=false;
		$scope.categoryId = id;
		sharedDatas.setOwnerId(0);
		$scope.refreshProducts();
	}
	
	$scope.haveChild = function(id){
		for(var i in $scope.categories) {
			if($scope.categories[i].id == id) {
				if($scope.categories[i].children.length) {
					$scope.haveChildNode = true;
					break;
				} else {
					$scope.haveChildNode = false;
					break;
				}
			}
		}
	};
	
//URL setting
/*    if (url.length == 2){
        page = url[1].split('=');
    } else {
        page[1] = 1;
    };

    $scope.datas.currentPage=page[1];

    switch(url[0]){
        case '/index#name':
            $scope.currentTab = '#/index#name';
            $scope.datas.tab = 'name';
            break;
        default:
            $scope.currentTab = '#/index#new';
            $scope.datas.tab = 'uploadTime';
    };
*/
//ProductListing
	$scope.refreshProducts = function() {
		$scope.ownerId = sharedDatas.getOwnerId();
		productServices.getAllProducts($scope.ownerId, $scope.categoryId).success(function(result) {
			if(result && result.length != 0) {
				for(var i in result) {
					result[i].city = location.getCity(result[i].cityId);
					result[i].category = sharedDatas.setCategoryName(result[i].categoryId);
				}
				angular.copy(result, $scope.products);
				$scope.totalItems = $scope.products.length;
				$scope.status=true;
			} else if(result.length == 0) {
				$scope.isEmpty = true;
			} else {
				alert("Hiba történt!");
			}
		});
	};
	$scope.refreshProducts();
		
		
	$scope.changeOrder = function(value) {
		$scope.status = false;
		switch(value){
            case 'Legújabb':
				$scope.order = '-uploadTime';
				break;
			 case 'Név szerint':
				if($scope.order == 'name') {
					$scope.order = '-name';
					$scope.az = false;
				} else {
					$scope.order = 'name';
					$scope.az = true;
				}
				break;
		}
		$scope.status = true;
	};

    $scope.getIndexPictures = function(){
		for(var i in $scope.products) {
            var j=0;
            find=false;
            while(!find){
                if ($scope.products[i].pictures[j].isMainPicture == 1){
                    $scope.products[i].indexPicture = '../GoodsExchangePublic/images/'+$scope.products[i].pictures[j].link;
                    find = true;
                }
                j++;
            };
        };
    };

	$scope.showProductById = function(product) {
		$scope.showProductDetails = true;
		angular.copy(product, $scope.currentProduct);
		userServices.getUserById($scope.currentProduct.ownerId).success(function(result){
			if(result) {
				$scope.currentProduct.ownerName = result.userName;
			}
		});
		$scope.currentProduct.category = sharedDatas.setCategoryName($scope.currentProduct.categoryId);
	};
	
	$scope.notShowProductDetails = function() {
		$scope.showProductDetails = false;
	};

}]);