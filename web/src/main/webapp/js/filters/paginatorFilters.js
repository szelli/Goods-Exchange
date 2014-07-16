var app = angular.module('paginatorFilters', []);

app.filter('forLoop', function() {
    return function(input, total, currentPage) {
    total = parseInt(total);
    currentPage = parseInt(currentPage)
    if ((total-currentPage) < 5){
        for (var i=total-4; i<=total; i++)
            input.push(i);
    } else {
        for (var i=currentPage; i<=total && i<currentPage+5; i++)
            input.push(i);
    }
    return input;
  };
})