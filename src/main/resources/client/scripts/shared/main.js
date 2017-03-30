(function() {
  'use strict';
  angular.module('app.controllers', ['app.auth']).controller('AppCtrl', [
    '$scope', '$location', 'AuthService', function($scope, $location, AuthService) {
      $scope.isSpecificPage = function() {
        var path;
        path = $location.path();
        return _.contains(['/404', '/pages/500', '/pages/login', '/pages/signin', '/pages/signin1', '/pages/signin2', '/pages/signup', '/pages/signup1', '/pages/signup2', '/pages/forgot', '/pages/lock-screen'], path);
      };
      
     
		
      $scope.deconnexion = function() {
    	  
    	  AuthService.deconnexion().success(function() {
    		  $location.path('/pages/signin');
    	  });
    	  
      };
      
      $scope.login = function() {
    	  $location.path('/pages/signin');
      };
      
      return $scope.main = {
        brand: 'Square',
        name: 'Lisa Doe'
      };
    }
  ]).controller('NavCtrl', [
    '$scope', 'taskStorage', 'filterFilter','$injector', '$routeParams', function($scope, taskStorage, filterFilter, $injector, $routeParams) {
    		
    	function refaire(){
    		var AuthService = $injector.get('AuthService');
        	AuthService.getUser().success(function(data) {
    			if (data) {
    				$scope.role = data.role;
    				console.log($scope.role);
    				if(data.role == 'ETU'){
    					AuthService.getInfoEtudiant(data.noEtudiant)
    					.success(function (data){
    						$scope.adm = "";
    						$scope.ens = {};
    						$scope.etu = data;
    					})
    					.error(function(){
    						console.log("ca ne marche absolument pas");								
    					});
    				}
    				else if (data.role == 'ENS'){
    					AuthService.getInfoEnseignant(data.noEnseignant)
    					.success(function (data){
    						$scope.adm = "";
    						$scope.etu = {};
    						$scope.ens = data;
    					})
    					.error(function(){
    						console.log("ca ne marche absolument pas");								
    					});
    				}
    				else{
    					$scope.etu = {};
    					$scope.ens = {};
    					$scope.adm = "Administrateur";
    				}
    			}
    		});
    	}
    	refaire();
    	
  	  $scope.$on('$routeChangeStart', function(event, next, current) { 
  		  if (next.$$route.originalPath == "/dashboard" || next.$$route.originalPath == "/etudiant/:noEtudiant") {
  			  refaire();
  		  }
  	  });
    	
      var tasks;
      tasks = $scope.tasks = taskStorage.get();
      $scope.taskRemainingCount = filterFilter(tasks, {
        completed: false
      }).length;
      return $scope.$on('taskRemaining:changed', function(event, count) {
        return $scope.taskRemainingCount = count;
      });
    }
  ]).controller('DashboardCtrl', ['$scope','$http', function($scope,$http) {
	  $http.get("http://localhost:8090/enseignant/countENSChercheur")
	  .then(function(reponse){
		  $scope.nbrEnsCh = reponse.data;
	  });
	  
	  $http.get("http://localhost:8090/enseignant/countENSIntervenantExt")
	  .then(function(reponse){
		  $scope.nbrEnsInt = reponse.data;
	  });
	  
	  $http.get("http://localhost:8090/etudiant/countEtudiant")
	  .then(function(response){
		  $scope.nbrEtu = response.data;
	  });
	  
	  $http.get("http://localhost:8090/formation/count")
	  .then(function(reponse){
		  $scope.nbrFrm = reponse.data;
	  });
	  
	  $http.get("http://localhost:8090/ue/countUniteEnseignement")
	  .then(function(reponse){
		  $scope.nbrUE = reponse.data;
	  });
	  
  }]);

}).call(this);
