(function (ng) {

    var mainApp = ng.module('mainApp', [
        'bookModule',
        'ngRoute'
    ]);

    mainApp.config(['$routeProvider', function ($routeProvider) {
            $routeProvider
                .when('/book', {
                    templateUrl: 'src/modules/book/book.tpl.html',
                    controller: 'bookCtrl',
                    controllerAs: 'ctrl'
                })
                .otherwise('/book');
        }]);
})(window.angular);
