(function (ng) {

    var mainApp = ng.module('mainApp', [
        'tranviaModule',
        'ngRoute'
    ]);

    mainApp.config(['$routeProvider', function ($routeProvider) {
            $routeProvider
                .when('/tranvia', {
                    templateUrl: 'src/modules/tranvia/tranvia.tpl.html',
                    controller: 'tranviaCtrl',
                    controllerAs: 'ctrl'
                })
                .otherwise('/tranvia');
        }]);

})(window.angular);
