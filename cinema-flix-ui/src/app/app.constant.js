(function() {
  'use strict';

  angular
    .module('cinema-flix')
    .constant('CONFIG', {
      API_END_POINT: 'http://localhost:8080/cinema-flix/api'
    });
})();