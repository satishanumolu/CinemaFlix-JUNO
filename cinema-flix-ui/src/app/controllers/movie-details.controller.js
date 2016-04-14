/**
 * Created by Satish on 09-03-2016.
 */
(function(){
  'use strict';

  angular
      .module('cinema-flix')
      .controller('MovieDetailController',MovieDetailController);

    MovieDetailController.$inject = ['$rootScope','$state','$stateParams','movieService','commentService','$sessionStorage']
    function MovieDetailController($rootScope,$state,$stateParams,movieService,commentService,$sessionStorage){
        var movieDetailVm = this;
        $rootScope.signOutFlag = true;
        movieDetailVm.title = $stateParams.title;
        movieDetailVm.user = $sessionStorage.user;
        movieDetailVm.updateRating = updateRating;
        movieDetailVm.commentsFlag = true;
        movieDetailVm.showComments = showComments;
        movieDetailVm.addComment = addComment;
        loginCheck();

        function loginCheck() {
            if(movieDetailVm.user == null) {
                $state.go("signin");
            }
            else {
                init();
            }
        }

        function init() {
            movieService
                .getMovie(movieDetailVm.title)
                .then(function (data) {
                    movieDetailVm.movie = data;
                    displayComments();
                }, function (errorData) {
                    console.log(errorData);
                    $state.go("movies");
                    alert("Movie Not Found");
                });
        }

        function displayComments() {
            commentService
                .getComments()
                .then(function (data) {
                    movieDetailVm.comments = data;
                }, function (errorData) {
                    console.log(errorData);
                });
        }

        function addComment(){
            movieDetailVm.newComment.movie = movieDetailVm.movie;
            movieDetailVm.newComment.user = movieDetailVm.user;
            movieDetailVm.newComment.timestamp = new Date();
            commentService
                .addComment(movieDetailVm.newComment)
                .then(function successFn(data){
                        movieDetailVm.newComment = null;
                        displayComments()
                    },
                    function errorFn(error){
                        console.log(error);
                    });
        }

        function updateRating(){
             movieService
                .updateRating(movieDetailVm.movie.movieID,movieDetailVm.movie.rating)
                .then(function (data) {
                },
                function (errorData) {
                    console.log(errorData);
                });
        }

         function showComments(){
             movieDetailVm.commentsFlag =! movieDetailVm.commentsFlag;
         }
  }
})();
