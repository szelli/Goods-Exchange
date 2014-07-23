var adminCtrl = angular.module('adminCtrl', []);

adminCtrl.controller('adminCtrl', ['$scope', '$rootScope', '$http', 'adminServices', 'userServices',
	function($scope, $rootScope, $http, adminServices, userServices) {
	$scope.changeContent = 'users';
	$scope.showDetails = false;
	$scope.editData = false;
	$scope.saveOrEditSwitch = 'save';
	$scope.users = [];
	$scope.users.length = 0;
	$scope.currentUser = {};
	$scope.categories = [];
	$scope.categories.length = 0;
	$scope.currentCatId;
	$scope.edit_error = false;
		
	$scope.changeContentFunction = function(value) {
		$scope.changeContent = value;
	};
	
	$scope.showDetailsFunction = function(value) {
		$scope.showDetails = !$scope.showDetails;
	};
	
	$scope.editDataFunction = function() {
		$scope.editData = !$scope.editData;
	};
	
	$scope.saveOrEditSwitchFunction = function(value) {
		$scope.saveOrEditSwitch = value;
	}	
		
	$scope.saveCatIdForEdit = function(value) {
		$scope.currentCatId = value;
		$scope.editDataFunction();
	};
		
	
	/* users */
	$scope.getCurrentUser = function(id) {
		userServices.getUserById(id).success(function(result) {
			if(result){
				$scope.currentUser = result;
			} else {
				alert("Hiba történt!");
			}
		});
	};
		
	adminServices.getUsers().success(function(result){
		if(result){
			$scope.users.push.apply($scope.users, result);
		} else {
			alert("Hiba történt!");
		}
	});
	/* users - end */
	
	/* categories */
	$scope.saveCategoryFunction = function(categoryName, parentId) {
		adminServices.saveCategory(categoryName, parentId).success(function(result) {
			if(result=="ok") {
				alert("Sikeres művelet!");
			} else if(result == "already exist") {
				alert("Már létezik ez a nevű kategória!");
			} else {
				alert("Hiba történt!");
			}
		});
	};	
	
	$scope.getNestedChildren = function(result, parentId) {
		var outResult = [];
		for(var i in result) {
			if(result[i].parentId == parentId) {
				var children = $scope.getNestedChildren(result, result[i].id);
				if(children != null) {
					result[i].children = children;
				}
				outResult.push(result[i]);
			}
		}
		return outResult;
	}	
		
	$scope.listCategoriesFunction = function() {
		adminServices.listCategories().success(function(result) {
			if(result) {
				$scope.categories = $scope.getNestedChildren(result, 0);
			} else {
				alert("Hiba történt!");
			}
		});
	};
	
	$scope.editCategoryFunction = function(id, categoryName, parentId) {
		$scope.edit_error = $scope.checkFirst(id, parentId);
		if(!$scope.edit_error){
			adminServices.editCategory(id, categoryName, parentId).success(function(result) {
				if(result == "ok") {
					alert("Sikeres művelet!");
				} else {
					alert("Hiba történt!");
				}
			});
		}
	};
		
	$scope.deleteCategoryFunction = function(id) {
		adminServices.deleteCategory(id).success(function(result) {
			if(result) {
				alert("Sikeres művelet!");
			} else {
				alert("Hiba történt!");
			}
		});
	};
	
	$scope.checkFirst = function(id, parentId) {
		for(var i in $scope.categories) {
			if($scope.categories[i].id == id && parentId == 0) {
				console.log("Főkategória");
				if($scope.categories[i].children.length) {
					alert("Alkategória tartozik ehhez a kategóriához!");
					return true;
				} else {
					return false;
				}
				
			}
		}
		for(var i in $scope.categories) {
			if($scope.categories[i].id == parentId) {
				for(var j in $scope.categories[i].children) {
					if($scope.categories[i].children[j].id == id) {
						console.log("Alkategória");
						if($scope.categories[i].children[j].length) {
							alert("Alkategória tartozik ehhez a kategóriához!");
							return true;
						} else {
							return false;
						}
					}
				}
			}
		}	
	
	};
	/* categories - end */	
		
}]);