var regCtrl = angular.module('registrationCtrl', []);

regCtrl.controller('registrationCtrl', ['$scope', '$http', 'userServices',
function($scope, $http, userServices) {
    $scope.newUser = {};
    $scope.newUser.country = "Megye";
    $scope.newUser.city = "Város";
    $scope.newUser.address = "";
    $scope.password2 = null;
    $scope.error_country = false;
    $scope.error_city = false;
    $scope.error_offpristine = false;
    $scope.reg_error = false;
    $scope.reg_error_message = "";
    $scope.country_active = false;
    $scope.city_active = false;
    
    $scope.setCountry = function(country){
        $scope.newUser.country = country;
    };
    
    $scope.setCity = function(city){
        $scope.newUser.city = city;
    };
    
    $scope.toggleCityActive = function(){
        $scope.city_active = !$scope.city_active;
    };
    
    $scope.toggleCountryActive = function(){
        $scope.country_active = !$scope.country_active;
    };
    
    $scope.registration = function(form){
        $scope.reg_error = false;
        if($scope.newUser.country=="Megye"){
            $scope.error_country = true;
        }else{
            $scope.error_country = false;
        }
        
        if($scope.newUser.city=="Város"){
            $scope.error_city = true;
        }else{
            $scope.error_city = false;
        }
        
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