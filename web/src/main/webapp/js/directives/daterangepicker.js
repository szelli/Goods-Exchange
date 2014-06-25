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
                    $scope.startDate = start;
                    $scope.endDate = end;
				});
            };
            
           $element.daterangepicker(myoptions, myfunction);
		}
	};
});