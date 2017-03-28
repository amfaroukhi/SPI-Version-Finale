'use strict';
angular.module('app')
  .service('EtudiantDetailsSvc', ['$http', function ($http) {
	  
   this.etudiant = function(noEtudiant,callback){
	   var url = "http://localhost:8090/etudiant/"+noEtudiant;
	   $http.get(url).then(function(response){
		   callback(response.data);
	   });
   };
   
   this.promoEtudiant = function(noEtudiant,callback){
	   var url = "http://localhost:8090/etudiant/promo/"+noEtudiant;
	   $http.get(url).then(function(response){
		   callback(response.data);
	   });
   };
   
   this.getEvaluations = function(codeF,annee,callback){
	   var url = "http://localhost:8090/evaluation/getbypromotion/"+codeF+"/"+annee;
	   $http.get(url).then(function(response){
		   callback(response.data);
	   });
   };

  }]);
  
angular.module('app')
  .controller('EtudiantDetailsCtrl', [
    '$scope','$location', 'EtudiantDetailsSvc', '$routeParams','$http', function($scope, $location, EtudiantDetailsSvc, $routeParams, $http) {
    	
    	console.log($routeParams.noEtudiant);
    	$scope.noEtudiant = $routeParams.noEtudiant;
    	EtudiantDetailsSvc.etudiant($scope.noEtudiant,function(data){
    		console.log($scope.noEtudiant);
    		$scope.etudiant = data;
    	});
    	
    	
    	EtudiantDetailsSvc.promoEtudiant($scope.noEtudiant,function(data){
    		console.log($scope.noEtudiant);
    		$scope.promo = data;
    		
    		//$scope.promo.promotionPK.codeFormation,$scope.promo.promotionPK.anneeUniversitaire,
    		$scope.evaluations($scope.promo.promotionPK.codeFormation,$scope.promo.promotionPK.anneeUniversitaire);
    		
    	});
    	
    	$scope.evaluations = function(codeF,annee){
    		EtudiantDetailsSvc.getEvaluations(codeF,annee,function(data){
        	$scope.evaluations = data;
        	console.log(data);
        	});
    	};
    	
    }
  ]);
