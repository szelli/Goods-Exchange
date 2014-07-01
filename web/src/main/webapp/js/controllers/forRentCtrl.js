var forRentCtrl = angular.module('forRentCtrl', []);

forRentCtrl.controller('forRentCtrl', ['$scope', '$http', '$rootScope', 'forRentServices',
function($scope, $http, $rootScope, forRentServices) {
    $scope.startDate = null;
    $scope.endDate = null;
    $scope.dateRanges = [];
    
    $scope.getForRents = function(){
        var productId = 5; //törlendő
            forRentServices.getForRentsByProduct(productId).success(function(datas){
            	if(datas){
	            	$scope.dateRanges = [];
	                angular.forEach(datas, function(value, key) {
	                    this.push({startDate: Date.parse(value.fromDate), endDate: Date.parse(value.toDate)});
	                },$scope.dateRanges);
	                
	                angular.element("#calendar").daterangepicker({singleDatePicker:true, dateRanges: $scope.dateRanges});
            	}
            }).error(function(){
                alert("Hiba történt a termék lefoglalhatóság lekérése közben, kérlek próbálkozz később!");
            });
    };
    $scope.getForRents();
    
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
}]);