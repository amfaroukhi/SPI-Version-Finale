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
   
   this.supprimer = function(noEtudiant){
		 var url = "http://localhost:8090/etudiant/"+noEtudiant;
		 $http.delete(url).then(function(response){
			 
		 });
	 };
  }]);
  
  
angular.module('app')
	  	.controller('listController', ['$scope','listService','$location','$routeParams','$modal',function ($scope,listService,$location,$routeParams,$modal) {
	    
	  	$scope.formations=[];
		$scope.promotions=[];
		$scope.etudiants=[];
		
		$scope.sortType     = 'nom'; // set the default sort type
	    $scope.sortReverse  = false;  // set the default sort order
	    $scope.search   = '';   
	    
	    // pour le tri des promotions 
	    
		$scope.sortType1     = 'promotionPK.anneeUniversitaire'; // set the default sort type
	    $scope.sortReverse1  = false;  // set the default sort order
	    
// pour le tri des Formations
	    
		$scope.sortType2    = 'codeFormation'; // set the default sort type
	    $scope.sortReverse1  = false;  // set the default sort order
	    
	    
// pour rediriger l'edit du MAJ etudiant
	    $scope.edit = function (noEtudiant,codeFormation,anneeUniversitaire){
          
            $location.path("/admin/etudiant/edit/"+ noEtudiant+"/"+codeFormation+"/"+anneeUniversitaire);

        }
	    
	    $scope.remove = function (noEtudiant, index ,etudiants){
	          /*var supp = confirm("voulez-vous bien supprimer cet étudiant ?");
	          if(supp == true){
	        	  listService.supprimer(noEtudiant);
		    	$scope.etudiants.splice(index,1);
		    	}*/
	    	
	    	 $modal.open({
                 templateUrl: 'supprimerEtudiant',
                 backdrop: true,
                 windowClass: 'modal',
                 controller: function ($scope,$modalInstance, $log) {
                     $scope.confirmer = function () {
                         listService.supprimer(noEtudiant);
                         $modalInstance.dismiss('cancel');
                         etudiants.splice(index,1);
                         
                         //location.reload('/admin/formationPromo');
                 		//$scope.showPromotions($scope.code);
                 		//$scope.showEtudiants($scope.code,$scope.annee);
                         //location.reload('/admin/formationPromo');
                     }
                     $scope.annuler = function () {
                         $modalInstance.dismiss('cancel');
                     };
                 }
             });
	    	

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
    	
    	$scope.raffrichir = function(){
    		
    		
    		
    	};
    
    	
    	
 	  }]);