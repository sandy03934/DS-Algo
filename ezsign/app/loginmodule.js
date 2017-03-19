define(function() {
   var loginmodule = angular.module('loginmodule', []);

   loginmodule.controller('loginController', function($scope){
     $scope.title = 'Hello World';
   })
})
