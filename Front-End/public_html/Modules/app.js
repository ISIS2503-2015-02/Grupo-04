(function () {

    var mainApp = angular.module('mainApp', ['tranviaModule']);
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
    mainApp.directive("tranvia",function(){
        return{
            restrict:'E',
            templateUrl:'partials/tranvia.html',
            controller: 'tranviaController'
        }
    });
    mainApp.controller("tranviaController", function($http, $scope) {
        
            $http.get('http://localhost:9000/tranvias').
                success(function(data, status, headers, config) {
                    $scope.tranvias = data;
                }).
                error(function(data, status, headers, config) {
                    // log error
                });
  
    }); 
    
})();
