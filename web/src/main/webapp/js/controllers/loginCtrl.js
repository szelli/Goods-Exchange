var loginCtrl = angular.module('loginCtrl', []);

loginCtrl.controller('loginCtrl', ['$scope', '$http', 'userServices', 'localStorageService', '$rootScope', '$location', 'sharedDatas', 'md5',
function($scope, $http, userServices, localStorageService, $rootScope, $location, sharedDatas, md5) {
    $scope.username = null;
    $scope.password = null;
    $scope.error_offpristine = false;
    $scope.login_error = false;
    $scope.login_error_message = "";

    $scope.logout = function(){
    	userServices.logout().success(function(response){
    		if(response == "ok") {
	    		$rootScope.loggedUser = null;
	            localStorageService.add("loggedUser", null);
	    		sharedDatas.setOwnerId(0);
	    		$location.path("/index");
    		}
    	});
    };
    
    $scope.login = function(form){
        $scope.login_error = false;
        
        if(!$rootScope.loggedUser){
            if(form.$valid){
                $scope.error_offpristine = false;
                $scope.password = md5.createHash($scope.password || '');
                userServices.login($scope.username, $scope.password).success(function(result){
                    if(result){
                        $scope.loggedUser = {};
                        $scope.loggedUser.username = result.userName;
                        $scope.loggedUser.id = result.id;
                        $scope.loggedUser.role = result.role.id;
                        localStorageService.add("loggedUser", $scope.loggedUser);
                        $rootScope.loggedUser = $scope.loggedUser;
                        $("#loginModal").modal("hide");
                    }else{
                        $scope.login_error = true;
                        $scope.login_error_message = "Hibás felhasználónév vagy jelszó!";
                    }
                }).error(function(){
                    $scope.login_error = true;
                    $scope.login_error_message = "Hiba történt a bejelentkezés közben, kérlek próbálkozz később!";
                });
            }else {
                $scope.error_offpristine = true;
            }
        }
    };

}]);