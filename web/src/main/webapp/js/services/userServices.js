var services = angular.module('services', ['ngResource']);

services.factory('userServices', function($http/*, localStorageService*/) {
	var user = {};

	user.getUser = function(username, password) {
		return $http({
			url : 'api/loginRequest',
			data : {
				"userName" : username,
				"password" : password
			},
			method : "POST",
			contentType : "application/json"
		});
	};

	user.saveUser = function(user) {
		return $http({
			url : 'api/registrationRequest',
			data : {
				"userName" : user.username,
                "fullName" : user.fullname,
				"password" : user.password,
				"email" : user.email,
				"city" : user.city,
                "address" : user.address,
                "postcode" : user.postcode,
                "status" : 1
            },
			method : "POST",
			contentType : "application/json"
		});
	};
	
	user.getUserById = function(userId) {
		return $http({
			url : 'api/getUserRequest',
			data : {
				"id" : userId
			},
			method: "POST",
			contentType: "application/json"
		});
	};
	
	user.editProfile = function(user) {
		console.log(user.id, user.postcode, user.city, user.address, user.email);
		return $http({
			url : 'api/editProfileRequest',
			data : {
				"id" : user.id,
				"postcode" : user.postcode,
				"city" : user.city,
				"address" : user.address,
				"email" : user.email
            },
			method : "POST",
			contentType: "application/json"
		});
	};
	
	return user;
});