var goods_exchange = angular.module('goods_exchange', [
    'ui.router',
    'LocalStorageModule',
    'registrationCtrl',
    'productCtrl',
    'loginCtrl',
    'productListingCtrl',
    'formDirectives',
    'paginatorFilters',
    'services'
]);

window.routes =
{
    "/index": {
    	templateUrl: 'pages/index.html',
        requireLogin: false
    },
    "/productUpload": {
    	templateUrl: 'pages/product_upload.html',
        requireLogin: true,
    },
    "/productUpdate": {
    	templateUrl: 'pages/product_upload.html',
        requireLogin: true,
        requireProduct: true,
    },
    "/product":{
	    templateUrl: 'pages/product.html', 
	    requireLogin: false,
    }
};


goods_exchange.config(['$stateProvider', '$urlRouterProvider', '$locationProvider', '$httpProvider','localStorageServiceProvider',
  function($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider,localStorageServiceProvider) {
		localStorageServiceProvider.setStorageType('sessionStorage');
		$httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
    
    $urlRouterProvider.otherwise("/index");
    
    $stateProvider
        .state('public', {
            url: "",
            abstract: true,
            template: "<div ui-view></div>"
        })
        
        .state('public.index', {
            url: '/index',
            templateUrl: 'pages/index.html'
        })
    
        .state('private', {
            url: "",
            abstract: true,
            template: "<div ui-view></div>"
        })
        
        .state('private.productUpload', {
            url: '/productUpload',
            templateUrl: 'pages/product_upload.html'
        });
  }]);

goods_exchange.run(function ($rootScope, localStorageService, cityServices, categoryServices, $location) {
    $rootScope.header = 'pages/header.html';
    $rootScope.regModal = 'pages/regModal.html';
    $rootScope.footer = 'pages/footer.html';
    $rootScope.currentProduct = null;
    
    
    $rootScope.$on("$locationChangeStart", function(event, next, current) {
        for(var i in window.routes) {
            if(next.indexOf(i) != -1) {
                if(window.routes[i].requireLogin && !$rootScope.loggedUser) {
                	$location.path('/index');
                	$("#loginModal").modal("show");
                }
                if(window.routes[i].requireProduct && !$rootScope.currentProduct){
                	$location.path('/index');
                }
            }
        }
    });
    
    cityServices.getCities().success(function(cities){
    	$rootScope.cities = cities;
        $rootScope.$broadcast('productListingCtrl');
    });
    
    categoryServices.getCategories().success(function(categories){
    	$rootScope.categories = categories;
    });
   
    if (localStorageService.get("loggedUser") != null && localStorageService.get("loggedUser") != '') {
		$rootScope.loggedUser = localStorageService.get("loggedUser");
	}
});