var profileCtrl = angular.module('profileCtrl', []);


profileCtrl.controller('profileCtrl', ['$scope', '$rootScope', '$http', '$location', 'userServices',
	function($scope, $rootScope, $http, $location, userServices) {
		$scope.data = {};
		$scope.userId = $location.search().id;
		$scope.ownProfile = false;
		$scope.view_error = false;
		$scope.view_error_message = "";
		
		$scope.backToPrivateProfile = function() {
			$location.path("/privateProfile");
		}
		
		if($rootScope.loggedUser!=null && $rootScope.loggedUser.id == $scope.userId){
			$scope.ownProfile = true;
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
