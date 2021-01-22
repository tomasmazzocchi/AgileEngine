'use strict';
/**
 * @ngdoc service
 * @name problemaService.Resource
 * @description problemaServiceResource Factory
 */
angular.module('bienvenida')
        .factory('AccountServiceResource', function ($resource, ENV) {

            var urlGet = ENV.endpoint.url + '/account';

            return $resource(urlGet, {}, {

                getAccount: {
                    url: urlGet + '/',
                    method: 'GET'
                }

            });
        });


