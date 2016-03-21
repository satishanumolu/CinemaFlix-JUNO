/**
 * Created by Satish on 14-03-2016.
 */
(function() {
    'use strict';

    angular
        .module('cinema-flix')
        .controller('HeaderController', HeaderController);

    HeaderController.$inject = ['$sessionStorage','$state'];
    function HeaderController($sessionStorage,$state) {

        var headerVm = this;

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