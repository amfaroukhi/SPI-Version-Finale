'use strict';
angular.module('app')
  .service('PromotionsService', ['$http', function ($http) {
   this.listFormations = function(){
	   var url = "http://localhost:8090/formations";
	   $http.get(url).then(function(response){
		   callback(response.data);
	   });
   };
   
   this.listPromotions = function(codeFormation,callback) {
   		var url = "http://localhost:8090/promotion/"+codeFormation;
   		$http.get(url).then(function(response){
   			callback(response.data);
   		});
   };
   
   this.listEtudiants = function(code,anneeUniversitaire,callback){
	   var url = "http://localhost:8090/etudiant/"+code+"/"+anneeUniversitaire;
	   $http.get(url).then(function(response){
		   callback(response.data);
		   
	   });
	   
   };
  }]);
  
  
angular.module('app')
	  	.controller('PromotionsController', ['$scope','PromotionsService','$routeParams',function ($scope,PromotionsService,$routeParams) {
	    
	  	$scope.formations=[];
		$scope.promotions=[];
		$scope.toto = false;
		$scope.etudiants=[];
		var codeFormation = $routeParams.codeFormation;
		
		$scope.afficherEtudiant = false;
		
		console.log($routeParams.codeFormation);
    	PromotionsService.listPromotions(codeFormation,function(data){
    			$scope.promotions=data;
    	});
    	
    	$scope.showToto = function(){
    		console.log("showTOTO()");
    		$scope.toto = true;
    	};
    	
    	$scope.showPromotions = function(codeF){
    		
    	};
    	
    	$scope.showEtudiants = function(code,anneeUiversitaire){
    		$scope.afficherEtudiant = true; 
    		PromotionsService.listEtudiants(code,anneeUiversitaire,function(data){
        		$scope.etudiants=data;
        		console.log(data);
        		console.log("afficher");
        	});
    		
    		
    	};
    
    	
    	
 	  }]);