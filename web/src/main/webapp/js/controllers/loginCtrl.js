var loginCtrl = angular.module('loginCtrl', []);

loginCtrl.controller('loginCtrl', ['$scope', '$http', 'userServices', 'localStorageService', '$rootScope',
function($scope, $http, userServices, localStorageService, $rootScope) {
    $scope.username = null;
    $scope.password = null;
    $scope.error_offpristine = false;
    $scope.login_error = false;
    $scope.login_error_message = "";
    
    $scope.setCountry = function(country){
        $scope.newUser.country = country;
    };
     $scope.setCity = function(city){
        $scope.newUser.city = city;
    };
    
    $scope.logout = function(){
        $rootScope.loggedUser = null;
        localStorageService.add("loggedUser", null);
    };
    
    $scope.login = function(form){
        $scope.login_error = false;
        
        if(!$rootScope.loggedUser){
            if(form.$valid){
                $scope.error_offpristine = false;
                userServices.getUser($scope.username, $scope.password).success(function(result){
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