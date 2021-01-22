'use strict';

angular.module('bienvenida')
        .controller('transactionController', function (TransactionServiceResource, AccountServiceResource, $scope, DTColumnDefBuilder, $rootScope, $uibModal) {
            $scope.formatoTablaMeeting = angular.copy($rootScope.dtOptionsMeetings);
            $scope.formatoTablaMeeting
                    .withOption("hasBootstrap", !1)
                    .withOption('ordering', true)
                    .withOption("order", [1, "asc"]);
            $scope.dcType = [DTColumnDefBuilder.newColumnDef([2, 3]).notSortable()];
            
            $scope.types = ["credit", "debit"];
            $scope.typeTransaction = null;
            $scope.amountTransaction = null;


            $scope.getAccount = function () {
                AccountServiceResource.getAccount({}, function (account) {
                    $scope.account = account;
                    $scope.getAllTransactions();
                });
            };
            $scope.getAccount();
            
            $scope.getAllTransactions = function () {
                TransactionServiceResource.getTransactions({}, function (data) {
                    $scope.listTransactions = data;
                });
            };
            
            $scope.sendTransaction = function () {
                new TransactionServiceResource.postTransactioin({}, {accountId: '1', type: $scope.typeTransaction, amount: $scope.amountTransaction}, function (data) {
                    $scope.listTransactions.push(data);
                    $scope.account.balance = data.balance;
                });
            };

        });
