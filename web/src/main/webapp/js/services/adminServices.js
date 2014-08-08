//var services = angular.module('services', ['ngResource']);

services.factory('adminServices', function($http) {
	var admin = {};

	admin.getUsers = function() {
		return $http({
			url : 'api/getUsersRequest',
			method : "POST",
			contentType : "application/json"
		});
	};
	
	admin.deleteUser = function(id) {
		return $http({
			url : 'api/deleteUserRequest',
			data : {
				"id" : id
			},
			method : "POST",
			contentType : "application/json"
		});	
	};
	
	admin.saveCategory = function(category) {
		return $http({
			url : 'api/saveCategoryRequest',
			data : {
				"parentId" : category.parentId.id,
				"name" : category.name
			},
			method : "POST",
			contentType : "application/json"
		});
	};
	
	admin.listCategories = function() {
		return $http({
			url : 'api/getCategoriesRequest',
			method : "POST",
			contentType : "application/json"
		});
	};
	
	admin.editCategory = function(category) {
		console.log(category.id, category.parentId.id, category.name);
		return $http({
			url : 'api/editCategoryRequest',
			data : {
				"id" : category.id,
				"parentId" : category.parentId.id,
				"name" : category.name
			},
			method : "POST",
			contentType : "application/json"
		});	
	};
	
	admin.deleteCategory = function(id) {
		return $http({
			url : 'api/deleteCategoryRequest',
			data : {
				"id" : id
			},
			method : "POST",
			contentType : "application/json"
		});	
	};
	
	admin.getCategory = function(id) {
		console.log("adminService: ", id);
		return $http({
			url : 'api/getCategoryRequest',
			data : {
				"id" : id
			},
			method : "POST",
			contentType : "application/json"
		});	
	};
	
	return admin;
});