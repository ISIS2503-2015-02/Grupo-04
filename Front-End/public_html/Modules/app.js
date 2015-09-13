(function () {

    var mainApp = angular.module('mainApp', [
        'tranviaModule',
        'ngRoute'
    ]);
    mainApp.directive('toolbar', function(){
        return{
            restrict:'E',
            templateUrl: 'partials/toolbar.html',
            controller:function(){
                this.tab=0;
                this.selectTab=function(setTab){
                    this.tab=setTab;
                };
                this.isSelected=function(tabParam){
                    return this.tab===tabParam;
                };
            },
            controllerAs:'toolbar'
        };
    });

    mainApp.config(['$routeProvider', function ($routeProvider) {
            $routeProvider
                .when('/tranvia', {
                    restrict:'E',
                    templateUrl: '/modules/tranvia/tranvia.tpl.html',
                    controller: 'tranviaCtrl'
                })
                .otherwise('/tranvia');
        }]);

})(window.angular);
