var privateProfileCtrl = angular.module('privateProfileCtrl', []);


privateProfileCtrl.controller('privateProfileCtrl', ['$scope', '$rootScope', '$http', '$location', 'userServices',
	function($scope, $rootScope, $http, $location, userServices) {
		$scope.myData = {};
		
		$scope.switch = false;
		
		$scope.editUser = {};
		$scope.editUser.id = $rootScope.loggedUser.id;
		
		userServices.getUserById($rootScope.loggedUser.id).success(function(result){
			if(result){
				$scope.myData = result;
			} else {
				alert("Hiba történt, kérlek próbálkozz később!");
				}
			});
		
		$scope.editProfile = function(){
			userServices.editProfile($scope.editUser).success(function(status_message) {
				if(status_message == "ok"){
					$scope.myData.postcode = $scope.editUser.postcode;
					$scope.myData.city = $scope.editUser.city;
					$scope.myData.address = $scope.editUser.address;
					$scope.myData.email = $scope.editUser.email;
					alert("Sikeres módosítás!");
				} else {
					alert("Hiba történt módosítás közben, kérlek próbálkozz később!");
				}
			});
		};
}]);

/*var editProfileCtrl = angular.module('editProfileCtrl', []);

editProfileCtrl.controller('editProfileCtrl', ['$scope', '$rootScope', '$http', '$location', 'userServices',
	function($scope, $rootScope, $http, $location, userServices) {
*/		
		
		
		
		/*userServices.editProfile($scope.newUser).success(function(status_message){
			if(status_message == "ok"){
				alert("A változtatásokat mentettük.");
				//$("#registrationModal").modal("hide");
			}else{
				alert("Valami gallyra ment.");
				//$scope.reg_error = true;
				//$scope.reg_error_message = status_message;
			}
		}).error(function(){
			//$scope.reg_error = true;
			//$scope.reg_error_message = "Hiba történt a regisztrációban, kérlek próbálkozz később!";
		});*/