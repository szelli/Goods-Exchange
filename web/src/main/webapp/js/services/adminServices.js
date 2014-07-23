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
	
	admin.saveCategory = function(categoryName, parentId) {
		return $http({
			url : 'api/saveCategoryRequest',
			data : {
				"parentId" : parentId,
				"name" : categoryName
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
	
	admin.editCategory = function(id, categoryName, parentId) {
		return $http({
			url : 'api/editCategoryRequest',
			data : {
				"id" : id,
				"parentId" : parentId,
				"name" : categoryName
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