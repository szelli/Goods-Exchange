var privateProfileCtrl = angular.module('privateProfileCtrl', []);

privateProfileCtrl.controller('privateProfileCtrl', ['$scope', '$rootScope', '$http', '$location', 'userServices', 'sharedDatas', 'location',
	function($scope, $rootScope, $http, $location, userServices, sharedDatas, location) {
		$scope.userDatas = {};
		//$scope.userDatas.newPassword2 = "";
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
			$scope.userDatas.oldPassword = null;
			$scope.newPassword2 = null;
		}
		
		$scope.refreshUserDatas = function() {
			userServices.getUserById($rootScope.loggedUser.id).success(function(result){
				var city;
				if(result){
					$scope.userDatas = result;
					$scope.userDatas.county = location.getCounty(location.getCountyId(result.cityId));
					city = result.cityId;
					$scope.userDatas.city = {};
					$scope.userDatas.city.id = city;
					$scope.userDatas.city.name = location.getCity(city);
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
						userServices.validatePassword($rootScope.loggedUser.id, $scope.userDatas.oldPassword).success(function(status_message) {
							if(status_message == "ok"){
								$scope.edit_error = false;
								$scope.editProfileFunction();
						} else {
							$scope.validPassword = false;
							$scope.edit_error = true;
								alert("Rossz jelszót adtál meg!");
							}
					});
				} else {
					$scope.editProfileFunction();
				}
			}
		};
		
		$scope.editProfileFunction = function(){
			userServices.editProfile($scope.userDatas).success(function(status_message) {
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
