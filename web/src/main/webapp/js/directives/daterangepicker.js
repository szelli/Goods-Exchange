var app = angular.module('formDirectives');

app.directive('daterangepicker', function () {
	return {
        restrict: 'A',
        require: '?ngModel',
		link: function ($scope, $element, $attributes) {
            var myoptions = {
                locale : {
                    applyLabel: 'Kiválasztás',
                    cancelLabel: 'Mégse',
                    fromLabel: 'Ettől:',
                    toLabel: 'Eddig:',
                    weekLabel: 'W',
                    customRangeLabel: 'Egyéni',
                    daysOfWeek: moment()._lang._weekdaysMin.slice(),
                    monthNames: moment()._lang._monthsShort.slice(),
                    firstDay: 1
                },
                minDate: moment().add('days', 1),
                ranges: {
                    'Holnap': [moment().add('days', 1), moment().add('days', 1)],
                    'Következő 7 nap': [moment().add('days', 1), moment().add('days', 7)],
                    'Jövő hónap': [moment().add('month', 1).startOf('month'), moment().add('month', 1).endOf('month')],
                    'Ez a hónap': [moment().startOf('day').add('days', 1), moment().endOf('month')]
                },
                startDate: moment().add('days', 1),
                endDate: moment(),
                singleDatePicker: false
            };
            var myfunction = function(start, end) {
                moment.lang("hu");
                $scope.$apply(function () {
                    $scope.dateRange = start.format('YYYY. MMMM D, dddd') + ' - ' + end.format('YYYY. MMMM D, dddd');
                    $scope.startDate = start.format('YYYY-MM-DD');
                    $scope.endDate = end.format('YYYY-MM-DD');
				});
            };
            
           $element.daterangepicker(myoptions, myfunction);
		}
	};
});

app.directive('showdateranges', function (forRentServices) {
	return {
        restrict: 'A',
        require: '?ngModel',
		link: function ($scope, $element, $attributes) {
            var productId = 5; //törlendő
            $scope.getForRents = function(initialise){
                forRentServices.getForRentsByProduct(productId).success(function(datas){
                    if(datas){
                        $scope.dateRanges = [];
                        angular.forEach(datas, function(value, key) {
                            this.push({startDate: Date.parse(value.fromDate), endDate: Date.parse(value.toDate), id : value.id});
                        },$scope.dateRanges);

                        angular.element("#calendar").daterangepicker({singleDatePicker:true, dateRanges: $scope.dateRanges});
                        if(initialise){
                            $(".dropdown-false").ready(function(){
                                $(this).delegate(".single [data-delete-daterange]","click",function(){
                                    if(confirm("Biztosan törölni akarod a lefoglathatóságot!")){
                                        $scope.deleteDateRange($(this).attr("data-delete-daterange"));
                                    }
                                });
                            });
                        }
                    }
                }).error(function(){
                    alert("Hiba történt a termék lefoglalhatóság lekérése közben, kérlek próbálkozz később!");
                });
            };
            $scope.getForRents(true);
        }
    }
});
           