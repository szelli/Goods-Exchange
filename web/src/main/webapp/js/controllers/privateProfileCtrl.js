var privateProfileCtrl = angular.module('privateProfileCtrl', []);

privateProfileCtrl.controller('privateProfileCtrl', ['$scope', '$rootScope', '$http', '$location', 'userServices',
	function($scope, $rootScope, $http, $location, userServices) {
		$scope.myData = {};
		$scope.editUser = {};
		$scope.editUser.id = $rootScope.loggedUser.id;
		
		$scope.buttonSwitch = false;
		$scope.setNewPassword = false;
		
		$scope.error_city = false;
		$scope.edit_error = false;
		
		$scope.buttonSwitchFunction = function(){
			$scope.buttonSwitch = !$scope.buttonSwitch;
			if($scope.setNewPassword){
				$scope.setNewPassword = !$scope.setNewPassword;
			}
		};
		
		$scope.setNewPasswordFunction = function(){
			$scope.setNewPassword = !$scope.setNewPassword;
		};
		
		$scope.setCity = function(city){
			$scope.editUser.city = city;
		};
		
		$scope.toPublicView = function(){
			$location.path("/profile");
		}
		
		userServices.getUserById($rootScope.loggedUser.id).success(function(result){
			if(result){
				$scope.myData = result;
				angular.copy($scope.myData, $scope.editUser);
			} else {
				alert("Hiba történt, kérlek próbálkozz később!");
			}
		});
		
		$scope.submitForm = function(isValid) {
			if(isValid){
				if($scope.setNewPassword){
						userServices.validatePassword($rootScope.loggedUser.id, $scope.editUser.oldPassword).success(function(status_message) {
							if(status_message == "ok"){
								$scope.edit_error = false;
								$scope.editProfileFunction();
								$scope.changePasswordFunction();
							} else {
								$scope.validPassword = false;
								$scope.edit_error = true;
								alert("Rossz jelszót adtál meg!");
								console.log("edit_error when password invalid:", $scope.edit_error);
							}
						});
				} else {
					$scope.editProfileFunction();
				}
			}
		};
		
		$scope.editProfileFunction = function(){
			userServices.editProfile($scope.editUser).success(function(status_message) {
				if(status_message == "ok"){
					angular.copy($scope.editUser, $scope.myData);
					$scope.edit_error = false;
					alert("Sikeres módosítás!");
				} else {
					alert("Hiba történt módosítás közben, kérlek próbálkozz később!");
					$scope.edit_error = true;
					$scope.edit_error_message = "Hiba történt módosítás közben, kérlek próbálkozz később!";
				}
			});
		};
		
		$scope.changePasswordFunction = function(){
			userServices.changePassword($rootScope.loggedUser.id, $scope.editUser.newPassword).success(function(status_message) {
				if(status_message == "ok"){
					$scope.edit_error = false;
					alert("Sikeres jelszó módosítás!");
				} else {
					alert("Hiba történt módosítás közben, kérlek próbálkozz később!");
					$scope.edit_error = true;
					$scope.edit_error_message = "Hiba történt módosítás közben, kérlek próbálkozz később!";
				}
			});
		};
}]);
