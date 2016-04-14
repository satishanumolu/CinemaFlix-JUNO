(function(){
    'use strict';

    angular
        .module('cinema-flix')
        .controller('UserController',UserController);

    UserController.$inject = ['$rootScope','userService','$state','$sessionStorage'];
    function UserController($rootScope,userService,$state,$sessionStorage){

        var userVm = this;

        $rootScope.signOutFlag = false;
        userVm.userNotFound = false;
        userVm.userAlreadyExists = false;
        userVm.signIn = signIn;
        userVm.signUp = signUp;

        delete $sessionStorage.user;

        function signIn(isValid){

            if(isValid) {
                userService
                    .signIn(userVm.email, userVm.password)
                    .then(function (data) {
                            userVm.$storage = $sessionStorage.$default({
                                user: data
                            });
                            $state.go("movies");
                        },
                        function (error) {
                            userVm.userNotFound = true;
                        })
            }
        }

        function signUp(isValid){

            if(isValid){
                userVm.newUser.role="USER";
                userService
                    .signUp(userVm.newUser)
                    .then(function successFn(data){
                            userVm.newUser = null;
                            userVm.$storage = $sessionStorage.$default({
                                user: data
                            });
                            $state.go("movies");
                        },
                        function errorFn(error){
                            userVm.userAlreadyExists = true;
                        });
            }
        }
    }

})();