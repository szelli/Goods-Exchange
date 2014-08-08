var adminCtrl = angular.module('adminCtrl', []);

adminCtrl.controller('adminCtrl', ['$scope', '$rootScope', '$http', 'adminServices', 'userServices', 'categoryServices', 'sharedDatas', 'productServices',
	function($scope, $rootScope, $http, adminServices, userServices, categoryServices, sharedDatas, productServices) {
	$scope.changeContent = 'users';
	$scope.showUserDetails = false;
	$scope.editDatas = false;
	$scope.showProductsByUser = false;
	$scope.saveOrEditSwitch = 'save';
	$scope.users = {};
	$scope.currentUser = {};
	$scope.currentCategory = {};
	$scope.newCategory = {};
	$scope.currentProduct = {};
	$scope.productsByOwner = {};
	$scope.edit_error = false;
	$scope.category_active = false;	
	
	$scope.changeContentFunction = function(value) {
		$scope.changeContent = value;
	};
		
	$scope.showDetailsFunction = function() {
		$scope.showUserDetails = false;
	};
	
	$scope.showUserDetailsFunction = function(value) {
		$scope.showUserDetails = !$scope.showUserDetails;
	};
	
	$scope.editDatasFunction = function() {
		$scope.editDatas = !$scope.editDatas;
	};
	
	$scope.setInitialState = function() {
		$scope.currentUser = {};
		$scope.currentCategory = {};
		$scope.newCategory = {};
		$scope.currentProduct = {};
	}
		
	$scope.saveOrEditSwitchFunction = function(value) {
		$scope.saveOrEditSwitch = value;
	};			
	
	/* users */
	$scope.getCurrentUser = function(id) {
		userServices.getUserById(id).success(function(result) {
			if(result){
				angular.copy(result, $scope.currentUser);
			} else {
				alert("Hiba történt!");
			}
		});
	};
	
	$scope.editUserDatas = function() {
		userServices.editProfile($scope.currentUser);
	}	
	
	adminServices.getUsers().success(function(result){
		if(result){
			$scope.users = result;
		} else {
			alert("Hiba történt!");
		}
	});
		
	$scope.deleteUserFunction = function(id) {
		adminServices.deleteUser(id);
	};
	/* users - end */
	
	/* products */
		
	$scope.showProductsByUserFunction = function() {
		$scope.showProductsByUser = !$scope.showProductsByUser;
	};
		
	$scope.getProductByOwner = function(id) {
		productServices.getProductsByOwner(id).success(function(result) { 
			if(result) {
				$scope.productsByOwner = result;
			} else {
				alert("Hiba történt!");
			}
		});
	};
	
	$scope.setCurrentProduct = function(product) {
		angular.copy(product, $scope.currentProduct);
		$scope.editDatasFunction();
	};
	/* products - end */
	
	/* categories */
	$scope.setCurrentCategory = function(category) {
		angular.copy(category, $scope.currentCategory);
		$scope.editDatasFunction();
	};
	
	$scope.saveCategoryFunction = function() {
		$scope.status=false;
		adminServices.saveCategory($scope.newCategory).success(function(result) {
			if(result=="ok") {
				alert("Sikeres művelet!");
				$scope.refreshCategories();
			} else if(result == "already exist") {
				alert("Már létezik ez a nevű kategória!");
			} else {
				alert("Hiba történt!");
			}
		});
	};	

	$scope.editCategoryFunction = function() {
		$scope.status=false;
		$scope.edit_error = $scope.checkFirst($scope.currentCategory.id, $scope.currentCategory.parentId.id);
		if(!$scope.edit_error){
			adminServices.editCategory($scope.currentCategory).success(function(result) {
				if(result == "ok") {
					alert("Sikeres művelet!");
					$scope.refreshCategories();
				} else {
					alert("Hiba történt!");
				}
			});
		}
	};
		
	$scope.deleteCategoryFunction = function(id) {
		$scope.status=false;
		adminServices.deleteCategory(id).success(function(result) {
			if(result) {
				alert("Sikeres művelet!");
				$scope.refreshCategories();
			} else {
				alert("Hiba történt!");
			}
		});
	};
	
	$scope.checkFirst = function(id, parentId) {
		var found = false;
		for(var i in $scope.categories) {
			if($scope.categories[i].id == id && parentId == 0) {
				found = true;
				console.log("Főkategória");
				if($scope.categories[i].children.length) {
					alert("Alkategória tartozik ehhez a kategóriához!");
					return true;
				} else {
					return false;
				}
				
			}
		}
		if(!found) {
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
		}
	
	};
		
	$scope.getCategoryById = function(id) {
		for(var i in $rootScope.subCategories) {
			if($rootScope.subCategories[i].id == id) {
				$scope.catId = $rootScope.subCategories[i].name;
			}
		}
			
	};
		
	$scope.refreshCategories = function() {
		$rootScope.getCategories();
	}
	/* categories - end */	
		
}]);