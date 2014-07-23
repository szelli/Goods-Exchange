var regCtrl = angular.module('registrationCtrl', []);

regCtrl.controller('registrationCtrl', ['$scope', '$http', 'userServices',
function($scope, $http, userServices) {
    $scope.newUser = {};
    $scope.newUser.address = "";
    $scope.password2 = null;
    $scope.error_offpristine = false;
    $scope.reg_error = false;
    $scope.reg_error_message = "";
    $scope.city_active = false;
    
    $scope.registration = function(form){
        $scope.reg_error = false;
        
        if(form.$valid){
            $scope.error_offpristine = false;
            $scope.newUser.address = $scope.newUser.street+" "+$scope.newUser.houseNumber+" "+$scope.newUser.floorAndDoor;
            
            userServices.saveUser($scope.newUser).success(function(status_message){
                if(status_message == "ok"){
                    alert("A regisztrációd mentettük.");
                    $("#registrationModal").modal("hide");
                }else{
                    $scope.reg_error = true;
                    $scope.reg_error_message = status_message;
                }
            }).error(function(){
                $scope.reg_error = true;
                $scope.reg_error_message = "Hiba történt a regisztrációban, kérlek próbálkozz később!";
            });
        }else {
            $scope.error_offpristine = true;
        }
    };

}]);