(function (ng) {
    var mod = ng.module('estacionVcubModule');

    mod.service('estacionVcubService', ['$http', 'testacionVcubContext', function ($http, context) {
            this.fetchRecords = function () {
                return $http.get(context);
            };

            this.fetchRecord = function (id) {
                return $http.get( + "/" + id);
            };
            this.saveRecord = function (currentRecord) {
                if (currentRecord.id) {
                    return $http.put(context + "/" + currentRecord.id, currentRecord);
                } else {
                    return $http.post('http://localhost:9000/competitors/', currentRecord);
                }
            };
            this.deleteRecord = function (id) {
                return $http.delete(context + "/" + id);
            };
        }]);
})(window.angular);
