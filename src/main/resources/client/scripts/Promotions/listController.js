'use strict';
angular.module('app')
  .service('listService', ['$http', function ($http) {
   this.listFormations = function(callback){
	   var url = "http://localhost:8090/formation/";
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
   
   this.supprimer = function(noEtudiant){
		 var url = "http://localhost:8090/etudiant/"+noEtudiant;
		
			return $http.delete(url);    	 
		
	 };
  }]);
  
  
angular.module('app')
	  	.controller('listController', ['$scope','listService','$location','$routeParams','$filter',function ($scope,listService,$location,$routeParams,$filter) {
	    
	  	$scope.formations=[];
		$scope.promotions=[];
		$scope.etudiants=[];
		
		$scope.sortType     = 'nom'; // set the default sort type
	    $scope.sortReverse  = false;  // set the default sort order
	    $scope.search   = '';   
	    
// pour le tri des promotions
	    
		$scope.sortType1     = 'promotionPK.anneeUniversitaire'; // set the
																	// default
																	// sort type
	    $scope.sortReverse1  = false;  // set the default sort order
	    
// pour le tri des Formations
	    
		$scope.sortType2    = 'codeFormation'; // set the default sort type
	    $scope.sortReverse1  = false;  // set the default sort order
	    
// pour rediriger l'edit du MAJ etudiant
	    $scope.edit = function (noEtudiant,codeFormation,anneeUniversitaire){
          
            $location.path("/admin/etudiant/edit/"+ noEtudiant+"/"+codeFormation+"/"+anneeUniversitaire);

        }
	    
	    $scope.remove = function (noEtudiant, index){
	          var supp = confirm("voulez-vous bien supprimer cet étudiant ?");
	          if(supp == true){
	        	  listService.supprimer(noEtudiant);
		    	$scope.etudiants.splice(index,1);
		    	}
	    	

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
    	
    	
    	$scope.$watch('sortReverse',function(){
			retrierTableau();
		});
		
		$scope.$watch('sortType',function(){
			retrierTableau();
		});
		
		function retrierTableau(){
			$scope.qualificatifs= $filter('orderBy')( $scope.qualificatifs,$scope.sortType,$scope.sortReverse);
		}
    	
    	$scope.showEtudiants = function(code,anneeUiversitaire){
    		$scope.afficherEtudiant = true; 
    		listService.listEtudiants(code,anneeUiversitaire,function(data){
        		
        		$scope.etudiants=$filter('orderBy')(data,"'nom'",$scope.sortReverse);
        		console.log(data);
        		console.log("afficher");
        		$scope.code = code;
        		$scope.annee = anneeUiversitaire;
        	});
    		
    		
    	};
    
    	
    	
 	  }]);