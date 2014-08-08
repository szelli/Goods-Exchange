var app = angular.module('sharedDatas', []);

app.factory('sharedDatas', function(){
    var datas =
        {
            ownerId: 0
        };

    return {
        getOwnerId: function () {
            return datas.ownerId;
        },
        setOwnerId: function (ownerId) {
            datas.ownerId = ownerId;
        }
    };
});