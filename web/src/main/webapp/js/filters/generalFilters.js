var app = angular.module('generalFilters', []);

app.filter('reverse', function() {
    return function(items) {
        return items.slice().reverse();
    };
});

app.filter('createLabel', function() {
    return function(input, label) {
        if(input){
            return input;
        } else{
            return label;
        }
    };
});