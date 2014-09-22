var adminCtrl = angular.module('adminCtrl', []);

adminCtrl.controller('adminCtrl', ['$scope', '$rootScope', 'adminServices', 'userServices', 'messageServices', 'sharedDatas', 'productServices', 'location',
	function($scope, $rootScope, adminServices, userServices, messageServices, sharedDatas, productServices, location) {
	$scope.changeContent = 'users';
	$scope.showUserDetails = false;
	$scope.editDatas = false;
	$scope.showProductsByUser = false;
	$scope.showChildCategories = false;
	$scope.isEmpty = false;
	$scope.saveOrEditSwitch = 'save';
	$scope.products = [];
	$scope.productsByOwner = [];
	$scope.currentProduct = {};
	$scope.users = [];
	$scope.currentUser = {};
	$scope.currentCategory = {};
	$scope.newCategory = {};
	$scope.edit_error = false;
	$scope.totalItems = 0;
	$scope.currentPage = 1;
	$scope.itemsPerPage = 10;
	
	$scope.changeContentFunction = function(value) {
		$scope.changeContent = value;
		if(value == 'users') {
			$scope.totalItems = $scope.users.length;
		} else if(value == 'products') {
			$scope.totalItems = $scope.products.length;
		} else if(value == 'categories') {
			$scope.setCategories();
		}
	};
		
	$scope.setInitalStates = function() {
		$scope.showUserDetails = false;
		$scope.editDatas = false;
		$scope.showProductsByUser = false;
		$scope.showChildCategories = false;
	};
	
	$scope.showUserDetailsFunction = function(value) {
		$scope.showUserDetails = !$scope.showUserDetails;
	};
	
	$scope.showChildCategoriesFunction = function() {
		$scope.showChildCategories = !$scope.showChildCategories;
	};	
	
	$scope.editDatasFunction = function() {
		$scope.editDatas = !$scope.editDatas;
	};	
	
	$scope.saveOrEditSwitchFunction = function(value) {
		$scope.saveOrEditSwitch = value;
	};	
		
	$scope.setCategories = function(parentId) {
		$scope.categories = [];
		if($scope.showChildCategories) {
			for(var i in $rootScope.subCategories) {
				if(parentId == $rootScope.subCategories[i].parentId) {
					$scope.categories.push($rootScope.subCategories[i]);
				}
			}
		} else {
			$scope.categories = $rootScope.categories;
		}
		$scope.totalItems = $scope.categories.length;
		if($scope.categories.length == 0) {
			$scope.isEmpty = true;
		} else {
			$scope.isEmpty = false;
		}
	}
	
	userServices.getAllUsers().success(function(result) {
		if(result){
			angular.copy(result, $scope.users);
			$scope.totalItems = $scope.users.length;
		} else {
			alert("Hiba történt!");
		}
	});
		
	productServices.getAllProducts().success(function(result) {
		if(result) {
			for(var i in result) {
				result[i].city = location.getCity(result[i].cityId);
				result[i].category = sharedDatas.setCategoryName(result[i].categoryId);
			}
			angular.copy(result, $scope.products);
		} else {
			alert("Hiba történt!");
		}
	});		
	
	
	
	/* users */
	$scope.getCurrentUser = function(id) {
		var city;
		userServices.getUserById(id).success(function(result) {
			if(result){
				city = result.cityId;
				angular.copy(result, $scope.currentUser);
				$scope.currentUser.county = location.getCounty(location.getCountyId(city));
				$scope.currentUser.city = location.getCity(city);
			} else {
				alert("Hiba történt!");
			}
		});
	};
	
	$scope.editUserDatas = function() {
		userServices.editProfile($scope.currentUser);
	};	
		
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
				angular.copy(result, $scope.productsByOwner);
				for(var i in $scope.productsByOwner) {
					$scope.productsByOwner[i].city = location.getCity($scope.productsByOwner[i].cityId);
					$scope.productsByOwner[i].category = sharedDatas.setCategoryName($scope.productsByOwner[i].categoryId);
				}
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
		console.log($scope.currentCategory.id, $scope.currentCategory.name, $scope.currentCategory.parentId);
		if($scope.currentCategory.parentId == "" || !$scope.currentCategory.parentId) {
			$scope.currentCategory.parentId = 0;
		}
		console.log($scope.currentCategory.id, $scope.currentCategory.name, $scope.currentCategory.parentId);
		$scope.editDatasFunction();
	};
	
	$scope.saveCategoryFunction = function() {
		$scope.status=false;
		if($scope.newCategory.parentId == "" || !$scope.newCategory.parentId) {
			$scope.newCategory.parentId = 0;
		}
		adminServices.saveCategory($scope.newCategory).success(function(result) {
			if(result=="ok") {
				alert("Sikeres művelet!");
				$scope.newCategory = {};
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
		$scope.edit_error = $scope.checkFirst($scope.currentCategory.id, $scope.currentCategory.parentId);
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
		$scope.setCategories();
		$rootScope.setItems();
	};
	/* categories - end */	
		
}]);