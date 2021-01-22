'use strict';

/**
 * @ngdoc object
 * @name core.Controllers.HomeController
 * @description Home controller
 * @requires ng.$scope
 */
angular.module('login')
        .controller('LoginController', function ($state) {

                $state.go('app.main.agileEngine.bienvenida');

        });
