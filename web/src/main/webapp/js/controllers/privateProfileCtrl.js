var privateProfileCtrl = angular.module('privateProfileCtrl', []);

privateProfileCtrl.controller('privateProfileCtrl', ['$scope', '$rootScope', '$http', '$location', 'userServices', 'sharedDatas',
	function($scope, $rootScope, $http, $location, userServices, sharedDatas) {
		$scope.myData = {};
		$scope.editUser = {};
		$scope.editUser.id = $rootScope.loggedUser.id;
		sharedDatas.setOwnerId($rootScope.loggedUser.id);
		
		$scope.buttonSwitch = false;
		$scope.setNewPassword = false;
		
		$scope.edit_error = false;
		
		$scope.buttonSwitchFunction = function(){
			$scope.buttonSwitch = !$scope.buttonSwitch;
			if($scope.setNewPassword){
				$scope.setNewPassword = !$scope.setNewPassword;
			}
		};
		
		$scope.setOwnerId = function() {
			sharedDatas.setOwnerId(0);
		}
		
		$scope.setNewPasswordFunction = function(){
			$scope.setNewPassword = !$scope.setNewPassword;
		};
		
		$scope.setInitialState = function() {
			$scope.editUser.oldPassword = null;
			//$scope.editUser.newPassword = null;
			$scope.editUser.newPassword2 = null;
		}
		
		$scope.refreshUserDatas = function() {
			userServices.getUserById($rootScope.loggedUser.id).success(function(result){
				if(result){
					$scope.myData = result;
					angular.copy($scope.myData, $scope.editUser);
					$scope.myData.city = {};
					$scope.myData.city.name = $scope.editUser.city;
					$scope.status=true;
				} else {
					alert("Hiba történt, kérlek próbálkozz később!");
				}
			});
		};
		$scope.refreshUserDatas();
		
		$scope.submitForm = function(isValid) {
			$scope.status=false;
			if(isValid){
				if($scope.setNewPassword){
						userServices.validatePassword($rootScope.loggedUser.id, $scope.editUser.oldPassword).success(function(status_message) {
							if(status_message == "ok"){
								$scope.edit_error = false;
								console.log($scope.editUser.newPassword);
								$scope.editProfileFunction();
						} else {
							$scope.validPassword = false;
							$scope.edit_error = true;
								alert("Rossz jelszót adtál meg!");
							}
					});
				} else {
					console.log("editUser: ", $scope.editUser.city);
					$scope.editProfileFunction();
				}
			}
		};
		
		$scope.editProfileFunction = function(){
			userServices.editProfile($scope.editUser).success(function(status_message) {
				if(status_message == "ok"){
					$scope.edit_error = false;
					$scope.refreshUserDatas();
					alert("Sikeres módosítás!");
				} else {
					alert("Hiba történt módosítás közben, kérlek próbálkozz később!");
					$scope.edit_error = true;
					$scope.edit_error_message = "Hiba történt módosítás közben, kérlek próbálkozz később!";
				}
			});
		};
}]);
