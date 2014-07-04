var forRentCtrl = angular.module('forRentCtrl', []);

forRentCtrl.controller('forRentCtrl', ['$scope', '$http', '$rootScope', 'forRentServices',
function($scope, $http, $rootScope, forRentServices) {
    $scope.startDate = null;
    $scope.endDate = null;
    
    $scope.saveForRent = function(){
        if($scope.startDate!=null && $scope.endDate!=null){
            var productId = 5; //törlendő
            forRentServices.saveForRent(productId, $scope.startDate, $scope.endDate).success(function(status_message){
                if(status_message == "ok"){
                    alert("A termék lefoglalhatóságát mentettük.");
                    $scope.getForRents();
                }else{
                    alert(status_message);
                }
            }).error(function(){
                alert("Hiba történt a termék lefoglalhatóság mentése közben, kérlek próbálkozz később!");
            });
        }
    };
    $scope.deleteDateRange = function(forRentId){
        forRentServices.deleteForRent(forRentId).success(function(status_message){
            if(status_message == "ok"){
                alert("A termék lefoglalhatóságát töröltük.");
                $scope.getForRents();
            }else{
                alert(status_message);
            }  
        }).error(function(){
            alert("Hiba történt a termék lefoglalhatóság törlése közben, kérlek próbálkozz később!");
        });
    };
}]);