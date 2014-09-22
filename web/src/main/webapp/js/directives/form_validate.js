var app = angular.module('formDirectives', []);

app.directive('equalpasswds', function () {
    return {
        require: '?ngModel',
        link: function (scope, elm, attrs, ctrl) {
            if (!ctrl) return;
            if(scope.newPassword2==null) {
                ctrl.$setValidity('equalpasswds', false);
            }
            
            ctrl.$parsers.unshift(function(confirmPassword, $scope) {
                var match = false;
                if(confirmPassword == scope.userDatas.newPassword && confirmPassword!=null){
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
		    scope.imageUploadLabel = "";
		    el.on('change', function () {
		        scope.showPreviewImages = false;
		        if(this.files.length > 0 && this.files.length <= 5 && (scope.product.images.length + this.files.length) <= 5){
		            ngModel.$setViewValue(this.files);
		            scope.convertImages(this.files);
		
		            var numFiles = this.files.length ? this.files.length : 1;
		            var label = angular.element(this).val().replace(/\\/g, '/').replace(/.*\//, '');
		            
		            var log = numFiles > 1 ? numFiles + ' files selected' : label;
		            scope.imageUploadLabel = log;
		            
		            scope.$apply(function(){
		                ngModel.$setValidity('selectimages', true);
		            });		            
		        } else {
		            if(scope.product.images.length > 0){
		                scope.showPreviewImages = true;
		            }
		            if(scope.product.images.length + this.files.length > 5){
		                alert("5-nél több képet nem tölthetsz fel!");
		            }
		        }
		        this.files = null;
			});
		}
	};
});
