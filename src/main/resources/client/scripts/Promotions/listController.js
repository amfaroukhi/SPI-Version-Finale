'use strict';
angular.module('app')
  .service('listService', ['$http', function ($http) {
   this.listFormations = function(callback){
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
	  	.controller('listController', ['$scope','listService','$location','$routeParams',function ($scope,listService,$location,$routeParams) {
	    
	  	$scope.formations=[];
		$scope.promotions=[];
		$scope.etudiants=[];
		
		$scope.sortType     = 'nom'; // set the default sort type
	    $scope.sortReverse  = false;  // set the default sort order
	    $scope.search   = '';   
	    
// pour rediriger l'edit du MAJ etudiant
	    $scope.edit = function (noEtudiant,codeFormation,anneeUniversitaire){
          
            $location.path("/admin/etudiant/edit/"+ noEtudiant+"/"+codeFormation+"/"+anneeUniversitaire);

        }
		
		//pour afficher les listes formation puis etudiants 
		$scope.afficherPromotion = false;
		$scope.afficherEtudiant = false;
	
		listService.listFormations(function(data){
    		$scope.formations = data;
    		console.log(data);
    	});
    	
    	$scope.showPromotions = function(codeF){
    		listService.listPromotions(codeF,function(data){
    			$scope.afficherPromotion = true;
    			$scope.promotions=data;
                console.log(data);
                
    	});
    		
    		
    	};
    	
    	$scope.showEtudiants = function(code,anneeUiversitaire){
    		$scope.afficherEtudiant = true; 
    		listService.listEtudiants(code,anneeUiversitaire,function(data){
        		$scope.etudiants=data;
        		console.log(data);
        		console.log("afficher");
        		$scope.code = code;
        		$scope.annee = anneeUiversitaire;
        	});
    		
    		
    	};
    
    	
    	
 	  }]);