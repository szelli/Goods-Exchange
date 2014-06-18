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
	return user;
});