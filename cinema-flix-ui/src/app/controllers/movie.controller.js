/**
 * Created by Satish on 06-03-2016.
 */

(function(){
    'use strict';

    angular.module('cinema-flix')
        .controller('MovieController',MovieController);

    MovieController.$inject = ['movieService','$state','$sessionStorage','$uibModal'];
    function MovieController(movieService,$state,$sessionStorage,$uibModal){

        var movieVm = this;

        movieVm.sort = sort;
        movieVm.sortOrderChange = sortOrderChange;

        movieVm.user = $sessionStorage.user;
        movieVm.loginCheck = loginCheck;
        movieVm.itemsPerPage = 8;
        movieVm.pageChanged = pageChanged;
        movieVm.deleteFlag = false;
        movieVm.updateFlag = false;
        movieVm.addFlag = false;
        movieVm.showModal = false;
        movieVm.showAdminPriveleges = showAdminPriveleges;
        movieVm.deleteMovie = deleteMovie;
        movieVm.updateMovie = updateMovie;
        movieVm.addMovie = addMovie;

        loginCheck();

        function loginCheck() {
            if(movieVm.user == null) {
                $state.go("signin");
            }
            else {
                init();
            }
        }

        function init(){

            movieService
                .getMovies()
                .then(function (data){
                        movieVm.movies = data;
                        movieVm.length = data.length;
                        movieVm.currentPage = 1;
                        showAdminPriveleges();
                },function(errorData) {
                   console.log(errorData);
                });
        }

        function showAdminPriveleges(){
            if(movieVm.user.role === "ADMIN"){
                movieVm.deleteFlag = true;
                movieVm.addFlag = true;
                movieVm.updateFlag = true;
                movieVm.itemsPerPage = 7;
            }
        }

        function pageChanged() {
            if(movieVm.currentPage == 1){
                if(movieVm.user.role === "ADMIN"){
                    movieVm.addFlag = true;
                    movieVm.itemsPerPage = 7;
                }
            }
            else {
                movieVm.itemsPerPage = 8;
                movieVm.addFlag = false;
            }
        }

        function sort(keyname){
            movieVm.sortKey=keyname;
            movieVm.reverse = false;
        }

        function sortOrderChange(){
            movieVm.reverse=!movieVm.reverse;
        }

        function deleteMovie(movie){

            var modalInstance = $uibModal.open({
                templateUrl: 'app/views/delete-movie.tmpl.html',
                controller: 'ModalController as modalVm',
                resolve: {
                    movie: function(){
                        return movie;
                    }
                }
            });

            modalInstance.result.then(function(){
                init();
            });
        }

        function updateMovie(movie){

            var modalInstance = $uibModal.open({
                templateUrl: 'app/views/update-movie.tmpl.html',
                controller: 'ModalController as modalVm',
                resolve: {
                    movie: function(){
                        return movie;
                    }
                }
            });

            modalInstance.result.then(function(){
                init();
            });
        }

        function addMovie(movie){

            var modalInstance = $uibModal.open({
                templateUrl: 'app/views/add-movie.tmpl.html',
                controller: 'ModalController as modalVm',
                resolve: {
                    movie: function(){
                        return movie;
                    }
                }
            });

            modalInstance.result.then(function(){
                init();
            });
        }
    }

})();