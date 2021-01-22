'use strict';

angular.module('bienvenida')
        .controller('transactionController', function (transactionServiceResource, $scope, DTColumnDefBuilder, $rootScope, $uibModal) {
            $scope.formatoTablaMeeting = angular.copy($rootScope.dtOptionsMeetings);
            $scope.formatoTablaMeeting
                    .withOption("hasBootstrap", !1)
                    .withOption('ordering', true)
                    .withOption("order", [1, "asc"]);
            $scope.dcType = [DTColumnDefBuilder.newColumnDef([2, 3]).notSortable()];
            
            $scope.types = ["credit", "debit"];
            $scope.typeTransaction;

            $scope.getAllTransactions = function () {
                transactionServiceResource.getMeetings({}, function (data) {
                    $scope.listTransactions = data;
                });
            };
            $scope.getAllTransactions();

        });
