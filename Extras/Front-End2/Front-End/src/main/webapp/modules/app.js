(function () {
    this.usuario=0;
   
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
    mainApp.directive('adminToolbar', function(){
        return{
            restrict:'E',
            templateUrl: 'partials/adminToolbar.html',
            controller:function(){
                this.tab=0;
                this.selectTab=function(setTab){
                    this.tab=setTab;
                };
                this.isSelected=function(tabParam){
                    return this.tab===tabParam;
                };
            },
            controllerAs:'adminToolbar'
        };
    });
    mainApp.directive("tranvia",function(){
        return{
            restrict:'E',
            templateUrl:'partials/tranvia.html',
            controller: 'tranviaController'
        };
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
            templateUrl:'partials/estacionVcub.html',
            controller: 'estacionVcubController'
        };
    });
    mainApp.controller("estacionVcubController", function($http, $scope) {
        
            $http.get('http://localhost:9000/estacionvcub').
                success(function(data, status, headers, config) {
                    $scope.estacionVcubs = data;
                }).
                error(function(data, status, headers, config) {
                    // log error
                });
            this.llenarEstacion= function(currentRecord){
                $http.get('http://localhost:9000/estacionvcub/'+currentRecord.id+'/llenarEstacion').
                    success(function(data,headers){
                     $http.get('http://localhost:9000/estacionvcub').
                     success(function(data, status, headers, config) {
                            $scope.estacionVcubs = data;
                     });  
                }).error(function(data, status, headers, config) {
                    // log error
                });
                                  
            };
            this.calcularPorcentaje= function(currentRecord){
                
                if(currentRecord.vcubs<=currentRecord.capacidad*0.1)
                {
                    return true;
                }
               return false;                  
            };
                
                
    });
    
    mainApp.directive("movibus",function(){
        return{
            restrict:'E',
            templateUrl:'partials/movibus.html',
            controller: 'movibusController'
        };
    });
    mainApp.controller("movibusController", function($http, $scope) {
        
            $http.get('http://localhost:9000/movibus').
                success(function(data, status, headers, config) {
                    $scope.movibus = data;
                }).
                error(function(data, status, headers, config) {
                    // log error
                });
            $http.get('http://localhost:9000/pedidoMovibus').
                success(function(data, status, headers, config) {
                    $scope.pedidos = data;
                }).
                error(function(data, status, headers, config) {
                    // log error
                });
    });
    
    mainApp.directive("estadisticas",function(){
        return{
            restrict:'E',
            templateUrl:'partials/estadisticas.html',
            controller: 'estadisticasController'
        };
    });
    mainApp.controller("estadisticasController", function($http, $scope) {
        $http.get('http://localhost:9000/conductor/get/mejorDesempenio').
        success(function(data, status, headers, config) {
            $scope.conductores = data;
        }).
        error(function(data, status, headers, config) {
            // log error
        });
        $http.get('http://localhost:9000/tranvia/get/accidenteMasComun').
        success(function(data, status, headers, config) {
            $scope.tranvias = data;
        }).
        error(function(data, status, headers, config) {
            // log error
        });
        $http.get('http://localhost:9000/tranvia/get/accidenteMasComun').
        success(function(data, status, headers, config) {
            $scope.movibus = data;
        }).
        error(function(data, status, headers, config) {
            // log error
        });
    });
    mainApp.controller("pedidoMovibusController", function($http, $scope) {
        this.crearUsuario = function () {
             $http.defaults.headers.post = { 'Content-Type':'application/json' };
             
var aEncriptar = JSON.stringify($scope.currentRecord);
var hash = CryptoJS.MD5(aEncriptar);
             
var envio3 = $.concat('{"hashContent":"'+hash+'"}', JSON.stringify($scope.currentRecord));
var envio3 = '{"envio1":"{\"hashContent\":\"'+hash+'\"}","envio2":"'+JSON.stringify($scope.currentRecord)+'"}';
             
             $http.post('http://localhost:9000/usuario', JSON.stringify($scope.currentRecord),({headers:{'Content-Type':'application/json'}})).success(function(data,headers){		              
                 usuario=data.id;
                 alert("El usuario a sido creado");
             }).error(function(data, status, headers, config) {
                 document.write(usuario+"  "+ data);
            });
        };
        this.hacerPedido = function () {
            $http.defaults.headers.post = { 'Content-Type':'application/json' };
            $http.post('http://localhost:9000/usuario/'+usuario+'/solicitarMovibus', JSON.stringify($scope.pedidoRecord),({headers:{'Content-Type':'application/json'}})).success(function(data,headers){
                alert("La solicitud del movibus se ha enviado");
            }).error(function(data, status, headers, config) {
                 document.write(usuario+"  "+data);
            });
        };
    });
    
})();
