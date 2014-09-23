var goods_exchange = angular.module('goods_exchange', [
    'ui.router',
    'ngRoute',
	'LocalStorageModule',
	'angular-md5',
    'registrationCtrl',
    'productCtrl',
    'loginCtrl',
	'profileCtrl',
	'privateProfileCtrl',
    'productListingCtrl',
	'adminCtrl',
	'messageCtrl',
	'menuCtrl',
    'formDirectives',
    'itemDirectives',
    'paginatorFilters',
    'generalFilters',
	'sharedDatas',
	'location',
    'services'
	//'ngCookies'
]);

var states = [
    { name: 'public.index', url: '/index', templateUrl: 'pages/index.html', requireLogin: false },
    { name: 'public.profile', url: '/profile', templateUrl: 'pages/profile.html', requireLogin: false },
    { name: 'private.privateProfile', url: '/privateProfile', templateUrl: 'pages/private_profile.html', requireLogin: true },
	{ name: 'private.myProducts', url: '/myProducts', templateUrl: 'pages/my_products.html', requireLogin: true },
    { name: 'private.productUpload', url: '/productUpload', templateUrl: 'pages/product_upload.html', requireLogin: true},
	{ name: 'private.reservation', url: '/reservation', templateUrl: 'pages/reservation.html', requireLogin: true},
	{ name: 'private.messages', url: '/messages', templateUrl: 'pages/messages.html', requireLogin: true},
    { name: 'admin.index', url: '/beCareful', templateUrl: 'pages/beCareful.html', requireLogin: true},
	{ name: 'admin.home', url: '/admin', templateUrl: 'pages/admin.html', requireLogin: true}
]
									
goods_exchange.config(['$routeProvider', '$stateProvider', '$urlRouterProvider', '$locationProvider', '$httpProvider', 'localStorageServiceProvider', 
  function($routeProvider, $stateProvider, $urlRouterProvider, $locationProvider, $httpProvider, localStorageServiceProvider) {
	localStorageServiceProvider.setStorageType('sessionStorage');
	$httpProvider.defaults.useXDomain = true;
	$httpProvider.defaults.headers.common.xsrfHeaderName = 'X-XSRF-TOKEN';
		
	delete $httpProvider.defaults.headers.common['X-Requested-With'];
    
    $urlRouterProvider.otherwise("/index");
    
    $stateProvider
        .state('public', {
            url: "",
            abstract: true,
            template: "<div ui-view></div>",
			/*resolve: {
				app: function() {
					var username;
					if(!$rootScope.loggedUser) {
						username = "anonymus";
					} else {
						username = $rootScope.loggedUser.username;
					}
					
				}
			}*/
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

goods_exchange.run(function ($rootScope, localStorageService, cityServices, categoryServices, userServices, $location, $http) {
	//$http.defaults.headers.post['X-XSRF-TOKEN'] = $cookies.csrftoken;
    //console.log($cookies.XSRF_TOKEN);
	$rootScope.header = 'pages/header.html';
	$rootScope.loginModal = 'pages/loginModal.html';
    $rootScope.regModal = 'pages/regModal.html';
	$rootScope.newMessageModal = 'pages/newMessageModal.html';
    $rootScope.footer = 'pages/footer.html';
	$rootScope.userMenu = 'pages/user_menu.html';
	$rootScope.productListing = 'pages/product_listing.html';
	$rootScope.adminUsers = 'pages/admin_users.html';
	$rootScope.adminProducts = 'pages/admin_products.html';
	$rootScope.adminCategories = 'pages/admin_categories.html';
	$rootScope.adminMessages = 'pages/admin_messages.html';
	$rootScope.emptyCategory = 'pages/empty_category.html';
    $rootScope.preventURL='';
    
	/*$rootScope.roles = [
		{ id: 1, role: 'admin'},
		{ id: 2, role: 'user'},
		{ id: 3, role: 'public'}
	];*/
	
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
	
	
    cityServices.getCities().success(function(cities) {
    	$rootScope.cities = [];
        for(var i=0; i<cities.length; i++){
            var city = {};
            city.name = cities[i].city;
            city.id = cities[i].id;
			city.countyId = cities[i].countyId;
            $rootScope.cities.push(city);
        }
        $rootScope.$broadcast('productListingCtrl');
    });
	
	cityServices.getCounties().success(function(counties) {
    	$rootScope.counties = [];
        for(var i=0; i<counties.length; i++){
            var county = {};
            county.name = counties[i].county;
            county.id = counties[i].id;
            $rootScope.counties.push(county);
        }
    });
	
	userServices.getAllUsers().success(function(users) {
		$rootScope.users = [];
		for(var i=0; i<users.length; i++){
			var user = {};
			user.id = users[i].id;
			user.fullName = users[i].fullName;
			$rootScope.users.push(user);
		}
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
	};
	
	$rootScope.getCategories = function() {
        categoryServices.getCategories().success(function(categories){
			$rootScope.categoriesList = categories;
			$rootScope.subCategories = [];
			$rootScope.categories = createCategoryHierarchy(categories, null);
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
	
});