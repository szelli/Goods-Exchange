var app = angular.module('formDirectives', []);

app.directive('equalpasswds', function () {
    return {
        require: '?ngModel',
        link: function (scope, elm, attrs, ctrl) {
            if (!ctrl) return;
            if(scope.password2==null) {
                ctrl.$setValidity('equalpasswds', false);
            }
            
            ctrl.$parsers.unshift(function(confirmPassword, $scope) {
                var match = false;
                if(confirmPassword == scope.newUser.password && confirmPassword!=null){
                    match = true;
                };
                ctrl.$setValidity('equalpasswds', match);
            });
        }
    }
});