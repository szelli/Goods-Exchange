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

app.directive('selectimages', function () {
  return {
    restrict: 'A',
    require: '?ngModel',
    link: function (scope, el, attrs, ngModel) {
        if (!ngModel) return;
        if(el.val()==""){
            ngModel.$setValidity('selectimages', false);
        }
        
        el.on('change', function () {
        	scope.showPreviewImages = false;
            if(this.files.length>0 && this.files.length<=5){
                ngModel.$setViewValue(this.files);
                scope.convertImages(this);

                var numFiles = this.files.length ? this.files.length : 1;
                var label = angular.element(this).val().replace(/\\/g, '/').replace(/.*\//, '');

                var displayInput = angular.element(this).parents('.input-group').find(':text');
                var log = numFiles > 1 ? numFiles + ' files selected' : label;
                if(displayInput.length){
                    displayInput.val(log);
                }
                
                scope.$apply(function(){
                    ngModel.$setValidity('selectimages', true);
                });
                
            } else {
                scope.$apply(function(){
                    ngModel.$setValidity('selectimages', false);
                });
            }
        });
    }
  };
});