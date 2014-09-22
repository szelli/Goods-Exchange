var app = angular.module('sharedDatas', []);

app.factory('sharedDatas', [ '$rootScope', 'messageServices', function ($rootScope, messageServices) {
    var datas =
        {
            ownerId: 0,
			activeTab: 'users'
        };

    return {
        getOwnerId: function () {
            return datas.ownerId;
        },
        setOwnerId: function (ownerId) {
            datas.ownerId = ownerId;
        },
		getActiveTab: function () {
			return datas.activeTab;
		},
		setActiveTab: function (value) {
			datas.activeTab = value;
		},
		setCategoryName: function (id) {
			for (var i in $rootScope.categoriesList) {
				if($rootScope.categoriesList[i].id == id) {
					return $rootScope.categoriesList[i].name;
				}
			}
		},
    };
}]);