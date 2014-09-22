var app = angular.module('location', []);

app.factory('location', ['$rootScope', function($rootScope) {
    return {
		getCounty: function(id) {
			for(var i in $rootScope.counties) {
				if($rootScope.counties[i].id === id) {
					return $rootScope.counties[i].name;
				}
			}
		},
		
		getCountyId: function(id) {
			for(var i in $rootScope.cities) {
				if($rootScope.cities[i].id == id) {
					return $rootScope.cities[i].countyId;
				}
			}
		},
		
        getCity: function(id) {
			for(var i in $rootScope.cities) {
				if($rootScope.cities[i].id === id) {
					return $rootScope.cities[i].name;
				}
			}
		},
    };
}]);