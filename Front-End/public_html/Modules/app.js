(function () {

    var mainApp = angular.module('mainApp', []);
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
            templateUrl:'Modules/modules/tranvia/tranvia.html',
            controller: 'tranviaController'
        }
    });
    mainApp.controller("tranviaController", function($http, $scope) {
        
            $http.get('http://localhost:9000/tranvia').
                success(function(data, status, headers, config) {
                    $scope.tranvias = data;
                }).
                error(function(data, status, headers, config) {
                    // log error
                });
  
    });
    mainApp.directive("vcubs",function(){
        return{
            restrict:'E',
            templateUrl:'Modules/modules/estacionVcub/estacionVcub.html',
            controller: 'estacionVcubController'
        }
    });
    mainApp.controller("estacionVcubController", function($http, $scope) {
        
            $http.get('http://localhost:9000/estacionvcub').
                success(function(data, status, headers, config) {
                    $scope.estacionVcubs = data;
                }).
                error(function(data, status, headers, config) {
                    // log error
                });
  
    });
    
    mainApp.directive("movibus",function(){
        return{
            restrict:'E',
            templateUrl:'Modules/modules/movibus/movibus.html',
            controller: 'movibusController'
        }
    });
    mainApp.controller("movibusController", function($http, $scope) {
        
            $http.get('http://localhost:9000/movibus').
                success(function(data, status, headers, config) {
                    $scope.movibus = data;
                }).
                error(function(data, status, headers, config) {
                    // log error
                });
  
    });
    
    mainApp.directive("estadisticas",function(){
        return{
            restrict:'E',
            templateUrl:'Modules/modules/estadisticas/estadisticas.html',
            controller: 'estadisticasController'
        }
    });
    mainApp.controller("estadisticasController", function($http, $scope) {
        
            $http.get('http://localhost:9000/estadisticas').
                success(function(data, status, headers, config) {
                    $scope.estadisticas = data;
                }).
                error(function(data, status, headers, config) {
                    // log error
                });
  
    }); 
    
    mainApp.controller("pedidoMovibusController", function($http, $scope) {
    var usuario;
        this.crearUsuario = function (id) {
            $http.defaults.headers.post = { 'Content-Type':'application/json' };
            $http.post('http://localhost:9000/usuario', JSON.stringify($scope.currentRecord),({headers:{'Content-Type':'application/json'}})).success(function(data,headers){
                usuario=data;
            });
        }
        this.hacerPedido = function (id) {
            $http.post('http://localhost:9000/usuario/'+data.id+'/solicitarMovibus', JSON.stringify($scope.competitor),({headers:{'Content-Type':'application/json'}})).success(function(data,headers){
                usuario=data;
            });
        };
        
            
  
    });
    
    
})();
