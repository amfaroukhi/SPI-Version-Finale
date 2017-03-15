'use strict';



angular.module('app')




  .service('qualificatifSvc', ['$http', function ($http) {
	  
   this.fetchPopular = function(callback) {
   		var url = "http://localhost:8090/qualificatif/";
   		$http.get(url).then(function(response){
   			callback(response.data);
   		   
   		});
   };


   this.afficherDetails = function(callback, idQualificatif) {
  		var url = "http://localhost:8090/qualificatif/" + idQualificatif;
  		$http.get(url).then(function(response){
  			callback(response.data);
 
  		});
  };
   

  
  this.modifierQualificatif = function(qualificatif){
	   qualificatif["Content-Type"] = "application/json"; 
	   
	   $http.put("http://localhost:8090/qualificatif/",qualificatif).then(function(reponse){
		   window.location.href ="http://localhost:8090/index.html#/accueil";
			 
			 
		 },function(erreur){
			 alert("il y a une erreur");
		 });  
  };
  
    this.cancel = function(){
	   
		   window.location.href ="http://localhost:8090/index.html#/admin/qualificatifs";
	
  };
  
  this.supprimerQualificatif = function(idQualificatif){
		 
		return $http.delete("http://localhost:8090/qualificatif/" + idQualificatif);    
  };

   
   
   this.ajouterQualificatif = function(qualificatif){
	   qualificatif["Content-Type"] = "application/json";
		 $http.post("http://localhost:8090/qualificatif",qualificatif).then(function(reponse){
		    window.location.href ="http://localhost:8090/index.html#/admin/qualificatifs";
		 },function(erreur){
			 alert("il ya une erreur");
		 });  
	   };
  }]);



angular.module('app')
	  	.controller('qualificatifCtrl', ['$scope','qualificatifSvc', '$routeParams','$filter',function ($scope,qualificatifSvc,$routeParams,$filter) {
	    
	  	
		$scope.qualificatifs=[];
		$scope.qualificatif =null;
		

		
		
		
		$scope.supprimerQualificatif = function(idQualificatif,indextableau){
			console.log("index tableau " + indextableau);
			var r = confirm("Voulez vous vraiment supprimer ? ");
		
		    if (r == true){
			    qualificatifSvc.supprimerQualificatif(idQualificatif).then(function(res){
					console.log("Suppriméavec succès");
					$scope.qualificatifs.splice(indextableau,1);
				},function(err){
					
					alert("Impossible de supprimer ce qualificatif ");
					console.log("Erreur suppression serveur")
				});
				
			}
		};
		
		
		$scope.data = {
				
				idQualificatif : "",
				maximal : "",
				minimal : ""
		
		};
		
		this.afficherDetails = function(codeF){
			qualificatifSvc.afficherDetails(function(data){
				$scope.qualificatif= data;
			
			}
			,codeF)
			
		};
		
		$scope.ajouterQualificatif = function(qualificatif){
		
			qualificatifSvc.ajouterQualificatif(qualificatif);
		};
		

		
		$scope.modifierQualificatif = function(){
			
			qualificatifSvc.modifierQualificatif($scope.qualificatif);
		};
		
		$scope.sortType     = 'minimal'; // set the default sort type
		$scope.sortReverse  = false;  // set the default sort order
		$scope.search   = '';    
		
		
		$scope.cancel = function(){
			
			qualificatifSvc.cancel();
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
		
		if($routeParams.idQualificatif){
			var idQualificatif = $routeParams.idQualificatif;
			this.afficherDetails(idQualificatif);
		}

		
    	qualificatifSvc.fetchPopular(function(data){
    			$scope.qualificatifs=$filter('orderBy')(data,"'minimal'",$scope.sortReverse);
                
    	})
 	  }]);


