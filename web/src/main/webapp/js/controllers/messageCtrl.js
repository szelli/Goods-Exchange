var messageCtrl = angular.module('messageCtrl', []);

messageCtrl.controller('messageCtrl', ['$scope', '$rootScope', '$location', 'messageServices', 'sharedDatas',
function ($scope, $rootScope, $location, messageServices, sharedDatas) {
	$scope.changeContent = 'incoming';
	$scope.showMessageDetails = false;
	$scope.userId = $location.search().id;
	$scope.messages = [];
	$scope.messages.length = 0;
	$scope.currentMessage = {};
	$scope.totalItems = 0;
	$scope.currentPage = 1;
	$scope.itemsPerPage = 10;
	$scope.queue = [];
	$scope.selectedAll = false;

	$scope.changeContentFunction = function(value) {
		$scope.changeContent = value;
		$scope.clearQueue();
		$scope.loadMessages();
	};
	
	$scope.showMessageDetailsFunction = function() {
		$scope.clearQueue();
		$scope.showMessageDetails = !$scope.showMessageDetails;
	};
	
	$scope.onClick = function(message) {
		$scope.getMessageDetails(message);
		$scope.showMessageDetailsFunction();
		$scope.changeMessageStatus(message);
	};
	
	$scope.selectAll = function() {
		for(var i in $scope.messages) {
			var match = false;
			for(var j in $scope.queue) {
				if($scope.messages[i].id == $scope.queue[j]) {
					match = true;
					break;
				}
			}
			if(!match) {
				$scope.queue.push($scope.messages[i].id);
				$scope.messages[i].checked = true;
			}
		}
		$scope.selectedAll = true;
	};
	
	$scope.deselectAll = function() {
		$scope.queue = [];
		for(var i in $scope.messages) {
			$scope.messages[i].checked = false;
		}
		$scope.selectedAll = false;
	};
	
	$scope.addToQueue = function(message) {
		$scope.queue.push(message.id);
		message.checked = true;
	};
	
	$scope.removeFromQueue = function(message) {
		for(var i in $scope.queue) {
			if($scope.queue[i] == message.id){
				$scope.queue.splice(i, 1);
				message.checked = false;
			}
		}
	};
	
	$scope.clearQueue = function() {
		$scope.queue = [];
	};
	
	$scope.loadMessages = function(){
		var receiverId, senderId;
		$scope.messages = [];
		$scope.messages.length = 0;
		if($scope.changeContent) {
			if($scope.changeContent == 'incoming') {
				receiverId = $rootScope.loggedUser.id;
			} else if($scope.changeContent == 'sent') {
				senderId = $rootScope.loggedUser.id;
			}
			messageServices.getMessages(receiverId, senderId).success(function(result){
				if(result && result.length != 0) {
					angular.copy(result, $scope.messages);
					for(var i in $scope.messages) {
						$scope.messages[i].senderName = $scope.setFullName($scope.messages[i].senderId);
						$scope.messages[i].receiverName = $scope.setFullName($scope.messages[i].receiverId);
						$scope.messages[i].checked = false;
					}
					$scope.totalItems = $scope.messages.length;
					$scope.noIncomeMessage = false;
					$scope.noSentMessage = false;
				} else if(result.length == 0) {
					$scope.noIncomeMessage = true;
					$scope.noSentMessage = true;
				} else {
					alert("Hiba történt!");
				}
			});
		}
	};
	if($location.path() == "/messages" && $rootScope.loggedUser != null && $rootScope.loggedUser != "" && $scope.messages.length) {
		$scope.loadMessages();
	};	
	
	$scope.setFullName = function(id) {
		for(var i in $rootScope.users) {
			if($rootScope.users[i].id == id) {
				return $rootScope.users[i].fullName;
			}
		}
	};
	
	$scope.getMessageDetails = function(message) {
		angular.copy(message, $scope.currentMessage);
	};
	
	$scope.toUser = function() {
		$scope.newMessage = {};
		$scope.newMessage.receiverId = $scope.userId;
		$rootScope.recipient = $scope.setFullName($scope.userId);
		console.log($scope.newMessage.receiverId, $rootScope.recipient);
	};
	
	$scope.toAdmin = function() {
		$scope.newMessage = {};
		$scope.newMessage.receiverId = 100;
		$rootScope.recipient = 'Admin';
		console.log($scope.newMessage.receiverId, $rootScope.recipient);
	};
	
	$scope.reply = function() {
		$scope.newMessage = {};
		$scope.newMessage.receiverId = $scope.currentMessage.senderId;
		$rootScope.recipient = $scope.currentMessage.senderName;
		console.log($scope.newMessage.receiverId, $scope.recipient);
	};
	
	$scope.sendMessage = function() {
		$scope.newMessage.senderId = $rootScope.loggedUser.id;
		$scope.newMessage.sendDate = new Date();
		$scope.newMessage.senderStatus = true;
		$scope.newMessage.receiverStatus = true;
		$scope.newMessage.read = false;
		messageServices.saveMessage($scope.newMessage).success(function(result){
			if(result) {
				alert("Üzenet elküldve!");
			} else {
				alert("Sikertelen üzenetküldés!");
			}
		});	
	};
	
	$scope.changeMessageStatus = function(message) {
		if(message.read == false) {
			var read = true;
			messageServices.changeStatus(message.id, read);
		}
	};
	
	$scope.deleteMessage = function(id) {
		var receiverStatus = null;
		var senderStatus = null;
		var read = true;
		if($scope.changeContent) {
			if($scope.changeContent == 'incoming') {
				receiverStatus = false;
			} else if($scope.changeContent == 'sent') {
				senderStatus = false;
			}
			messageServices.changeStatus(id, read, receiverStatus, senderStatus);
		}
	};
	
}]);
	