//var services = angular.module('services', ['ngResource']);

services.factory('messageServices', function($http) {
	var message = {};
	
	message.getMessages = function(receiverId, senderId) {
		return $http({
			url : 'api/getMessagesRequest',
			data : {
				"receiverId" : receiverId,
				"senderId" : senderId
			},
			method : "POST",
			contentType : "application/json"
		});
	};
	
	message.deleteMessage = function(id) {
		return $http({
			url : 'api/deleteMessageRequest',
			data : {
				"id" : id
			},
			method : "POST",
			contentType : "application/json"
		});
	};
	
	message.changeStatus = function(id, read, receiverStatus, senderStatus) {
		return $http({
			url : 'api/changeStatusRequest',
			data : {
				"id" : id,
				"read" : read,
				"receiverStatus" : receiverStatus,
				"senderStatus" : senderStatus
			},
			method : "POST",
			contentType : "application/json"
		});
	};
	
	message.saveMessage = function(message) {
		return $http({
			url : 'api/saveMessageRequest',
			data : {
				"senderId" : message.senderId,
				"receiverId" : message.receiverId,
				"sendDate" : message.sendDate,
				"subject" : message.subject,
				"message" : message.message,
				"senderStatus" : message.senderStatus,
				"receiverStatus" : message.receiverStatus,
				"read" : message.read
			},
			method : "POST",
			contentType : "application/json"
		});	
	};
	
	return message;
});