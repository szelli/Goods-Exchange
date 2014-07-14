var goods_exchange = angular.module('goods_exchange', [
    'ngRoute',
    'LocalStorageModule',
    'registrationCtrl',
    'productUploadCtrl',
    'loginCtrl',
    'formDirectives',
    'services'
]);


goods_exchange.config(['$routeProvider', '$locationProvider', '$httpProvider','localStorageServiceProvider',
  function($routeProvider, $locationProvider, $httpProvider,localStorageServiceProvider) {
		localStorageServiceProvider.setStorageType('sessionStorage');
		$httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
    $routeProvider.
        when('/index', {
            templateUrl: 'pages/index.html',
        }).
        when('/productUpload', {
            templateUrl: 'pages/product_upload.html',
        }).
        when('/productUpdate', {
            templateUrl: 'pages/product_upload.html',
        }).
        when('/product', {
            templateUrl: 'pages/product.html',
        }).
        otherwise({
            redirectTo: '/index'
        }); 
  }]);

goods_exchange.run(function ($rootScope, localStorageService, cityServices, categoryServices) {
    $rootScope.header = 'pages/header.html';
    $rootScope.regModal = 'pages/regModal.html';
    $rootScope.footer = 'pages/footer.html';
    $rootScope.updateProduct = false;
    $rootScope.currentProduct = null;
    
    cityServices.getCities().success(function(cities){
    	$rootScope.cities = cities;
    });
    
    categoryServices.getCategories().success(function(categories){
    	$rootScope.categories = categories;
    });
   
    if (localStorageService.get("loggedUser") != null && localStorageService.get("loggedUser") != '') {
		$rootScope.loggedUser = localStorageService.get("loggedUser");
	}
});