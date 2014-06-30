var services = angular.module('services');

services.factory('cityServices', function($http) {
	var city = {};
	
	city.getCities = function() {
		
		return $http({
			url : 'api/cityResponse',
			//data : "",
			method : "GET",
			contentType : "application/json"
		});
		/*return $http({
		    url: 'api/productUpload', 
		    method: "GET",
		    data: ""
		    /*success: function(data) { 
		        alert(data.id + " " + data.name);
		    },
		    error:function(data,status,er) { 
		        alert("error: "+data+" status: "+status+" er:"+er);
		    }
		*/
		// });
			  /*return $http.get('api/cityResponse').then(function(response) {
			            $scope.newMessage = response.data.queries.request.totalResults;
			            $scope.messages.push($scope.newMessage);
			  });*/
	};
	return city;
});
