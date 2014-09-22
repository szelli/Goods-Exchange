//var services = angular.module('services', ['ngResource']);

services.factory('adminServices', function($http) {
	var admin = {};
	
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
	
	admin.editCategory = function(category) {
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
	
	return admin;
});