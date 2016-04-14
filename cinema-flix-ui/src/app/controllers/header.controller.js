/**
 * Created by Satish on 14-03-2016.
 */
(function() {
    'use strict';

    angular
        .module('cinema-flix')
        .controller('HeaderController', HeaderController);

    HeaderController.$inject = ['$rootScope','$sessionStorage','$state'];
    function HeaderController($rootScope,$sessionStorage,$state) {

        var headerVm = this;

        $rootScope.signOutFlag = false;
        headerVm.signOut = signOut;
        headerVm.home = home;

        function home() {
            $state.go("movies");
        }

        function signOut(){
            delete $sessionStorage.user;
            $state.go("signin");
        }
    }
})();