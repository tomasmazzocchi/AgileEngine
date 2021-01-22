'use strict';
/**
 * @ngdoc service
 * @name problemaService.Resource
 * @description problemaServiceResource Factory
 */
angular.module('bienvenida')
        .factory('TransactionServiceResource', function ($resource, ENV) {

            var urlGet = ENV.endpoint.url + '/transaction';

            return $resource(urlGet, {}, {

                getTransactions: {
                    url: urlGet,
                    method: 'GET',
                    isArray: true
                },
                postTransactioin: {
                    url: urlGet,
                    method: 'POST'
                }

            });
        });


