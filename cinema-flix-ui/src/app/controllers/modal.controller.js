/**
 * Created by Satish on 11-03-2016.
 */
(function() {
    'use strict';

    angular.module('cinema-flix')
        .controller('ModalController', ModalController);

    ModalController.$inject=['$uibModalInstance','movie','movieService','$filter']
    function ModalController($uibModalInstance,movie,movieService,$filter){

        var modalVm = this;

        modalVm.movie = movie;
        modalVm.close = close;
        modalVm.movieAlreadyExists = false;
        modalVm.movieNotFound = false;
        modalVm.format = 'dd MMM yyyy';
        modalVm.altInputFormats = ['M!/d!/yyyy'];
        modalVm.deleteMovie = deleteMovie;
        modalVm.updateMovie = updateMovie;
        modalVm.addMovie = addMovie;
        modalVm.clear = clear;
        modalVm.open = open;
        modalVm.popup = {
            opened: false
        };
        modalVm.dateOptions = {
                                dateDisabled: false,
                                formatYear: 'yyyy',
                                maxDate: new Date(2020, 5, 22),
                                minDate: new Date(1500, 5,22),
                                startingDay: 1,
                                showWeeks:false
                              };

        released();
        function released() {
            if(modalVm.movie != null){
                modalVm.movie.released = new Date(modalVm.movie.released);
            }
        };

        function clear() {
            modalVm.movie.released = null;
        };

        function open() {
            modalVm.popup.opened = true;
        };

        function deleteMovie() {
            movieService
                .deleteMovie(modalVm.movie.movieID)
                .then(function (data){
                    close();
                },function(errorData) {
                    modalVm.movieNotFound = true;
                });
        }

        function updateMovie(isValid) {

            if(isValid) {
                modalVm.movie.released = $filter('date')(modalVm.movie.released,'dd MMM yyyy');
                movieService
                    .updateMovie(modalVm.movie.movieID, modalVm.movie)
                    .then(function (data) {
                        close();
                    }, function (errorData) {
                        console.log(errorData);
                        if(errorData == 404) {
                            modalVm.movieNotFound = true;
                        }
                        else {
                            modalVm.movieAlreadyExists = true;
                        }
                    });
            }
        }

        function addMovie(isValid) {

            if(isValid) {
                modalVm.movie.released = $filter('date')(modalVm.movie.released, 'dd MMM yyyy');
                movieService
                    .addMovie(modalVm.movie)
                    .then(function (data) {
                        close();
                    }, function (errorData) {
                        modalVm.movieAlreadyExists = true;
                    });
            }
        }

        function close(){
            $uibModalInstance.close();
        }
    }
})();