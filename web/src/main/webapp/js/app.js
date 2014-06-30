var goods_exchange = angular.module('goods_exchange', [
    'ngRoute',
    'LocalStorageModule',
    'registrationCtrl',
    'loginCtrl',
	'profileCtrl',
	'privateProfileCtrl',
    'formDirectives',
    'services'
]);


goods_exchange.config(['$routeProvider', '$locationProvider', '$httpProvider','localStorageServiceProvider',
  function($routeProvider, $locationProvider, $httpProvider,localStorageServiceProvider) {
		localStorageServiceProvider.setStorageType('localStorage');
		$httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
    $routeProvider.
        when('/index', {
            templateUrl: 'pages/index.html',
        }).
		when('/profile', {
            templateUrl: 'pages/profile.html',
        }).
		when('/privateProfile', {
            templateUrl: 'pages/private_profile.html',
        }).
		when('/privateProfileEdit', {
            templateUrl: 'pages/private_profile_edit.html',
        }).
		
        otherwise({
            redirectTo: '/index'
        }); 
  }]);

goods_exchange.run(function ($rootScope, localStorageService) {
    $rootScope.header = 'pages/header.html';
    $rootScope.regModal = 'pages/regModal.html';
    $rootScope.footer = 'pages/footer.html';
    
    if (localStorageService.get("loggedUser") != null && localStorageService.get("loggedUser") != '') {
		$rootScope.loggedUser = localStorageService.get("loggedUser");
	}
});