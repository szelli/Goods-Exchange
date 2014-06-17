var loginCtrl = angular.module('loginCtrl', []);

loginCtrl.controller('loginCtrl', ['$scope', '$http', 'userServices',
function($scope, $http, userServices) {
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
    
    $scope.login = function(form){
        $scope.login_error = false;
        
        if(form.$valid){
            $scope.error_offpristine = false;
            userServices.getUser($scope.username, $scope.password).success(function(result){
                if(result){
                    alert("Sikeresen bejelentkeztél!");
                    $("#loginModal").modal("hide");
                }else{
                    $scope.reg_error = true;
                    $scope.reg_error_message = "Hibás felhasználónév vagy jelszó!";
                }
            }).error(function(){
                $scope.login_error = true;
                $scope.login_error_message = "Hiba történt a bejelentkezés közben, kérlek próbálkozz később!";
            });
        }else {
            $scope.error_offpristine = true;
        }
    };

}]);