var profileCtrl = angular.module('profileCtrl', []);

profileCtrl.controller('profileCtrl', ['$scope', '$rootScope', '$http', '$location', 'userServices', 'sharedDatas', 'location',
	function($scope, $rootScope, $http, $location, userServices, sharedDatas, location) {
		$scope.datas = {};
		$scope.userId = $location.search().id;
		$scope.ownProfile = false;
		$scope.messageButton = false;
		$scope.view_error = false;
		$scope.view_error_message = "";
		
		if($rootScope.loggedUser != null && $rootScope.loggedUser.id == $scope.userId){
			$scope.ownProfile = true;
			sharedDatas.setOwnerId($rootScope.loggedUser.id);
		} else {
			sharedDatas.setOwnerId($scope.userId);
		}
		
		if($rootScope.loggedUser != null && $rootScope.loggedUser != "" && $rootScope.loggedUser.id != $scope.userId){
			$scope.messageButton = true;
		};
		
		userServices.getUserById($scope.userId).success(function(result){
			var city;
			if(result){
				city = result.cityId;
				$scope.datas = result;
				$scope.datas.county = location.getCounty(location.getCountyId(city));
				$scope.datas.city = location.getCity(city);
			} else {
				$scope.view_error = true;
				$scope.view_error_message = "Hiba történt, kérlek próbálkozz később!";
			}
		});
}]);
