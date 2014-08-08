var productListingCtrl = angular.module('productListingCtrl', []);

productListingCtrl.controller('productListingCtrl', ['$scope', '$rootScope', '$http', 'productServices','$location', 'sharedDatas',
function($scope, $rootScope, $http, productServices, $location, sharedDatas) {
    var j, find;
    var id = [];
	var base = $location.url().split('#');
    var url = $location.url().split('%');
    var page = [];
	$scope.haveChildNode;
	$scope.isEmpty = false;
    $scope.datas = {};
    $scope.datas.limit= 12;
    $scope.datas.sort= "DESC";
    $scope.datas.pageCount={};
    $scope.datas.currentPage=1;
    $scope.datas.productsCount=0;
    $scope.datas.offset=0;
	$scope.datas.categoryId=0;
    $scope.datas.tab={};
    $scope.currentTab={};
	$scope.tabs = [{
            title: 'Legújabb',
            url: '#'+base[0]+'#new'
        }, {
            //legtöbbet bérelt
            title: 'Név szerint',
            url: '#'+base[0]+'#name'
    }];
	
	$scope.setCategory = function(id) {
		$scope.status=false;
		$scope.datas.categoryId = id;
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
    if (url.length == 2){
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

//ProductListing
    $scope.refreshProducts = function() {
		productServices.GetProductsCount(sharedDatas.getOwnerId(), $scope.datas.categoryId).success(function(productsCount){
			if(productsCount != 0){
				$scope.isEmpty = false;
				$scope.datas.pageCount= Math.ceil(parseInt(productsCount) / parseInt($scope.datas.limit));
				$scope.datas.productsCount = productsCount;
				$scope.datas.offset=Math.ceil((parseInt($scope.datas.currentPage)-1) * parseInt($scope.datas.limit));
				$scope.datas.ownerId = sharedDatas.getOwnerId();
				productServices.loadProducts($scope.datas).success(function(productsList){
					$scope.products = productsList;
					//$scope.getIndexPictures();
					$scope.getCitys();
					$scope.status=true;
				});
			} else {
				$scope.isEmpty = true;
			}
		});
    };
	
    $scope.getIndexPictures = function(){
        for (var i=0; i<$scope.products.length; i++){
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

    $scope.getCitys = function(){
        for (var i=0; i<$scope.products.length; i++){
            j=0;
            find=false;
            while(!find){
                if ($rootScope.cities[j].id == $scope.products[i].cityId){
                    $scope.products[i].city = $rootScope.cities[j].name;
                    find = true;
                }
                j++;
            };
        };
    };

//Tabbing 
    $scope.orderChange = function(){
        switch($scope.datas.sort) {
            case 'DESC':
                $scope.datas.sort = 'ASC';
            break;
            default:
                $scope.datas.sort = 'DESC';
        }
    };

    $scope.isActiveTab = function(tabUrl) {
        return tabUrl == $scope.currentTab;
    };

    $scope.onClickTab = function (tab) {
        $scope.status=false;
        $scope.currentTab = tab.url;
        switch(tab.title){
            case 'Legújabb':
                if ($scope.datas.tab!='uploadTime'){
                    $scope.datas.tab='uploadTime';
                    $scope.datas.sort= 'DESC';
                    $scope.datas.currentPage = 1;
                } else {
                    $scope.orderChange();
                };
                break;
            case 'Név szerint':
                if ($scope.datas.tab!='name'){
                    $scope.datas.tab='name';
                    $scope.datas.sort= 'DESC';
                    $scope.datas.currentPage = 1;
                } else {
                    $scope.orderChange();
                };
                break;
        };
        $scope.refreshProducts();
    };

//Paging
    $scope.currentPage = function (page){
        $scope.status=false;
        $scope.datas.currentPage = page;
        $scope.refreshProducts();
    };

    $scope.previousPage = function(){
        $scope.status=false;
        $scope.datas.currentPage = 1;
        $scope.refreshProducts();
    };

    $scope.nextPage = function(){
        $scope.status=false;
        $scope.datas.currentPage = $scope.datas.pageCount;
        $scope.refreshProducts();
    };

    $scope.isActivePage = function(page) {
        return page == $scope.datas.currentPage;
    };

//Refresh page
    $scope.refreshProducts();
}]);