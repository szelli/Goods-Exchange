var profileCtrl = angular.module('profileCtrl', []);

profileCtrl.controller('profileCtrl', ['$scope', '$rootScope', '$http', '$location', 'userServices', 'sharedDatas',
	function($scope, $rootScope, $http, $location, userServices, sharedDatas) {
		$scope.data = {};
		$scope.userId = $location.search().id;
		$scope.ownProfile = false;
		$scope.view_error = false;
		$scope.view_error_message = "";
		
		if($rootScope.loggedUser!=null && $rootScope.loggedUser.id == $scope.userId){
			$scope.ownProfile = true;
			sharedDatas.setOwnerId($rootScope.loggedUser.id);
		} else {
			sharedDatas.setOwnerId($scope.userId);
		}
		
		userServices.getUserById($scope.userId).success(function(result){
			if(result){
				$scope.data = result;
			} else {
				$scope.view_error = true;
				$scope.view_error_message = "Hiba történt, kérlek próbálkozz később!";
			}
		});
}]);
