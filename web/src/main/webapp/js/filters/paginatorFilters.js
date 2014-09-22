var app = angular.module('paginatorFilters', []);

app.filter('forLoop', function() {
    return function(input, total, currentPage) {
    if (total < 5){
        for (var i=1; i<=total; i++)
            input.push(i);
    } else {
        for (var i=currentPage; i<=total && i<currentPage+5; i++)
            input.push(i);
    }
    return input;
  };
});

app.filter('offset', function() {
	return function(input, start) {
		start = parseInt(start,10);
		return input.slice(start);
	};
});