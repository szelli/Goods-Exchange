var goods_exchange = angular.module('goods_exchange', [
    'ui.router',
    'LocalStorageModule',
    'registrationCtrl',
    'productCtrl',
    'loginCtrl',
	'profileCtrl',
	'privateProfileCtrl',
    'productListingCtrl',
	'adminCtrl',
	'menuCtrl',
    'formDirectives',
    'itemDirectives',
    'paginatorFilters',
    'generalFilters',
	'sharedDatas',
    'services'
]);

/*window.routes =
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
};*/

var states = [
    { name: 'public.index', url: '/index', templateUrl: 'pages/index.html', requireLogin: false },
    { name: 'public.profile', url: '/profile', templateUrl: 'pages/profile.html', requireLogin: false },
    { name: 'private.privateProfile', url: '/privateProfile', templateUrl: 'pages/private_profile.html', requireLogin: true },
	{ name: 'private.myProducts', url: '/myProducts', templateUrl: 'pages/my_products.html', requireLogin: true },
    { name: 'private.productUpload', url: '/productUpload', templateUrl: 'pages/product_upload.html', requireLogin: true},
	{ name: 'private.reservation', url: '/reservation', templateUrl: 'pages/reservation.html', requireLogin: true},
    { name: 'admin.home', url: '/admin', templateUrl: 'pages/admin.html', requireLogin: true}
];

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

        .state('private', {
            url: "",
            abstract: true,
            template: "<div ui-view></div>"
        })
    
        .state('admin', {
            url: "",
            abstract: true,
            template: "<div ui-view></div>"
        })
      
      angular.forEach(states, function (state) {
            $stateProvider.state(state.name, state);
      });
}]);

goods_exchange.run(function ($rootScope, localStorageService, cityServices, categoryServices, $location) {
    $rootScope.header = 'pages/header.html';
    $rootScope.regModal = 'pages/regModal.html';
    $rootScope.footer = 'pages/footer.html';
	$rootScope.userMenu = 'pages/user_menu.html';
	$rootScope.productListing = 'pages/product_listing.html';
	$rootScope.adminUsers = 'pages/admin_users.html';
	$rootScope.adminProducts = 'pages/admin_products.html';
	$rootScope.adminCategories = 'pages/admin_categories.html';
	$rootScope.adminMessages = 'pages/admin_messages.html';
	$rootScope.emptyCategory = 'pages/empty_category.html';
    $rootScope.preventURL='';
    
    $rootScope.$on("$locationChangeSuccess", function() {
        if ($rootScope.preventURL != $location.path()){
            for (var i=0; i<states.length; i++){
                if ($location.path() == states[i].url){
                    $rootScope.preventURL=$location.path();
                    if (states[i].requireLogin && !$rootScope.loggedUser){
                        $location.path('/index');
                        $("#loginModal").modal("show");
                    }
                    i=states.length;
                }
            }
        }
    });
    
    cityServices.getCities().success(function(cities){
    	$rootScope.cities = [];
    	
        for(var i=0; i<cities.length; i++){
            var city = {};
            city.name = cities[i].city;
            city.id = cities[i].id;
            $rootScope.cities.push(city);
        }
        $rootScope.$broadcast('productListingCtrl');
    });
	
	
	
	createCategoryHierarchy = function(categories, parentId) {
		var outResult = [];
		for(var i in categories) {
			if(categories[i].parentId == parentId) {
				var children = createCategoryHierarchy(categories, categories[i].id);
				if(children != null) {
					categories[i].children = children;
				}
				outResult.push(categories[i]);
			}
		}
		return outResult;
	}
	
    //$rootScope.$on('productListingCtrl', function(e) {
	
	$rootScope.getCategories = function() {
        categoryServices.getCategories().success(function(categories){
			$rootScope.subCategories = [];
			$rootScope.categories = createCategoryHierarchy(categories, 0);
			for(var i in $rootScope.categories) {
				for(var j in $rootScope.categories[i].children) {
					$rootScope.subCategories.push($rootScope.categories[i].children[j]);
				}
			}
			
        });
	};
	$rootScope.getCategories();

        if (localStorageService.get("loggedUser") != null && localStorageService.get("loggedUser") != '') {
            $rootScope.loggedUser = localStorageService.get("loggedUser");
        }
    //});
});