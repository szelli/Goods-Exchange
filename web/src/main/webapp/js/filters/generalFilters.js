var app = angular.module('generalFilters', []);

app.filter('reverse', function() {
    return function(items) {
        return items.slice().reverse();
    };
});