(function(){
    'use strict';

    angular
        .module('cinema-flix',['ui.router','ui.bootstrap','angularMoment','ngStorage','ngMessages','ngAnimate'])
        .config(moduleConfig);

   moduleConfig.$inject = ['$stateProvider', '$urlRouterProvider'];
    function moduleConfig($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('');

        $stateProvider

            .state('signin', {
                url: '',
                templateUrl: 'app/views/signin.tmpl.html',
                controller: 'UserController',
                controllerAs: 'userVm'
            })

            .state('movies', {
                url: '/movies',
                templateUrl: 'app/views/movies.tmpl.html',
                controller:'MovieController',
                controllerAs: 'movieVm'
            })

            .state('movie-details', {
                url: '/movie-details/:title',
                templateUrl: 'app/views/movie-details.tmpl.html',
                controller:'MovieDetailController',
                controllerAs: 'movieDetailVm'
            });
    }
})();