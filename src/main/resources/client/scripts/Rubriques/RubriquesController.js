'use strict';



angular.module('app')


  .service('rubriqueSvc', ['$http', function ($http) {
	  
   this.fetchPopular = function(callback) {
   		var url = "http://localhost:8090/rubrique/";
   		$http.get(url).then(function(response){
   			callback(response.data);
   		   
   		});
   };
  
  
  
   this.cancel = function(){
	   
		   window.location.href ="http://localhost:8090/index.html#/admin/rubriques";
  };
  
   this.supprimerRubrique = function(idRubrique){
	 
		return $http.delete("http://localhost:8090/rubrique/" + idRubrique);
		 
	
   };
   

    this.afficherDetails = function(callback, idRubrique) {
  		var url = "http://localhost:8090/rubrique/" + idRubrique;
  		$http.get(url).then(function(response){
  			callback(response.data);
 
  		});
  };
   
   this.ajouterRubrique = function(rubrique){
	   rubrique["Content-Type"] = "application/json";
		 $http.post("http://localhost:8090/rubrique",rubrique).then(function(reponse){
		    window.location.href ="http://localhost:8090/index.html#/admin/rubriques";
		 },function(erreur){
			 alert("il ya une erreur");
		 });  
	   };
  
  
  
  
   
  }]);


  
  
  
  
  angular.module('app')
	  	.controller('rubriqueCtrl', ['$scope','rubriqueSvc','$routeParams','$filter',function ($scope,rubriqueSvc,$routeParams,$filter) {
	    
	    
	  		$scope.rubriques=[];
			$scope.rubrique ={
				type: "RBS"
			};
			
			$scope.data = {
				
				idRubrique : "",
				ordre : "",
				type : "",
				designantion :""
		
		};
			
			this.afficherDetails = function(codeF){
				rubriqueSvc.afficherDetails(function(data){
				$scope.rubrique= data;
			
			}
			,codeF)
			
		};
			
			$scope.supprimerRubrique = function(idRubrique,indextableau){
				console.log("index tableau " + indextableau);
				var r = confirm("Voulez vous vraiment supprimer ? ");
			
			    if (r == true){
			    	rubriqueSvc.supprimerRubrique(idRubrique).then(function(res){
					$scope.rubriques.splice(indextableau,1);
					},function(err){
						console.log("Erreur suppression serveur")
					});
					
				}
			};
			
			
			
			
			
			
			
			$scope.ajouterRubrique = function(rubrique){
		
				rubriqueSvc.ajouterRubrique(rubrique);
			};
			
			$scope.cancel = function(){
			
				rubriqueSvc.cancel();
			};
		
			$scope.sortType     = 'designation'; // set the default sort type
		    $scope.sortReverse  = false;  // set the default sort order
		    $scope.search   = '';    
		  
		    $scope.$watch('sortReverse',function(){
				retrierTableau();
			});
			
			$scope.$watch('sortType',function(){
				retrierTableau();
			});
			
			function retrierTableau(){
				$scope.qualificatifs= $filter('orderBy')( $scope.qualificatifs,$scope.sortType,$scope.sortReverse);
			}
			
			if($routeParams.idRubrique){
				var idRubrique = $routeParams.idRubrique;
				this.afficherDetails(idRubrique);
			}
			
			
	  		rubriqueSvc.fetchPopular(function(data){
	  			$scope.rubriques=$filter('orderBy')(data,"'designantion'",$scope.sortReverse);
	})
 	  }]);



