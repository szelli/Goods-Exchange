var app = angular.module('itemDirectives', []);

app.directive('customselect', function () {
    return {
        restrict: 'EA',
        replace: true,
        template: '<div class="select-dropdown" ng-class="{active : activate}">'
                +   '<span ng-click="toggleActive()" ng-class="{'+"'select-error'"+' : error}">{{selected.name | createLabel:label}}</span>'
                +   '<ul class="uldropdown" ng-click="toggleActive()">'
                +        '<li ng-repeat="item in items" ng-click="selectfunction(item)">{{item.name}}</li>'
                +   '</ul>'
                +'</div>',
        scope: {
            activate: "=",
            items: "=",
            selected: "=",
            label: "@",
            error: "="
        },
        link: function($scope, attr){
            $scope.toggleActive = function(){
                $scope.activate = !$scope.activate;
            };
            $scope.selectfunction = function(selectedItem){
                $scope.selected = selectedItem;
            }
        }
    }
});