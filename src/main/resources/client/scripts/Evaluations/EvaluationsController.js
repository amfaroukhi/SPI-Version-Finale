'use strict';



angular.module('app')




  .service('evaluationSvc', ['$http', function ($http) {
	  
   this.fetchPopular = function(callback) {
   		var url = "http://localhost:8090/evaluation/";
   		$http.get(url).then(function(response){
   			callback(response.data);
   		   
   		});
   };

   this.getuebyEnseignant = function(callback) {
   		var url = "http://localhost:8090/ue/getbyens/1";
   		$http.get(url).then(function(response){
   			callback(response.data);
   		   
   		});
   };

   this.getecbyue= function(callback,a) {
   		var url = "http://localhost:8090/ec/"+a;
   		$http.get(url).then(function(response){
   			callback(response.data);
   		});
   };

   


   this.afficherDetails = function(callback, idEvaluation) {
  		var url = "http://localhost:8090/evaluation/" + idEvaluation;
  		$http.get(url).then(function(response){
  			callback(response.data);
  		});
  };
   

  
  this.modifierEvaluation = function(evaluation){
	   evaluation["Content-Type"] = "application/json"; 
	   
	   $http.put("http://localhost:8090/evaluation/",evaluation).then(function(reponse){
		   window.location.href ="http://localhost:8090/index.html#/accueil";
			 alert("evaluation modifié");
			 
		 },function(erreur){
			 alert("il y a une erreur");
		 });  
  };
  
   this.supprimerEvaluation = function(idEvaluation){
	

	$http.delete("http://localhost:8090/evaluation/" + idEvaluation).then(function(reponse){
		 alert("evaluation supprimée");
		 window.location.href ="http://localhost:8090/index.html#/enseignant/evaluations";
		 
		 
	 },function(erreur){
		 alert("il ya une erreur");
	 });  
   };
   
   this.cancel = function(){
	   
		   window.location.href ="http://localhost:8090/index.html#/enseignant/evaluations";
	
  };
   
   
   this.ajouterEvaluation = function(evaluation){
	   evaluation["Content-Type"] = "application/json";
		 $http.post("http://localhost:8090/evaluation/",evaluation).then(function(reponse){
		     alert("evaluation ajoutée");
			 window.location.href ="http://localhost:8090/index.html#/admin/evaluations";
		 },function(erreur){
			 alert("il ya une erreur");
		 });  
	   };
  }]);



angular.module('app')
	  	.controller('evaluationCtrl', ['$scope','evaluationSvc', '$routeParams',function ($scope,evaluationSvc,$routeParams) {
	    
	  	
		$scope.evaluations=[];
		$scope.evaluation ={};
		$scope.evaluation.uniteEnseignementPK={};
		$scope.Unites=[];
		$scope.Elements=[];
		
		$scope.supprimerEvaluation = function(idEvaluation){
			evaluationSvc.supprimerEvaluation(idEvaluation);
		};
		
		
		this.afficherDetails = function(codeE){
			evaluationSvc.afficherDetails(function(data){
				$scope.evaluation= data;
			
			}
			,codeE)
			
		};
		
		$scope.ajouterEvaluation = function(evaluation){
			
			var str = $scope.evaluation.codeUe;
			var res = str.split("/");
			$scope.evaluation.codeUe=res[1];
			$scope.evaluation.noEnseignant=1;
			$scope.evaluation.codeFormation=res[0];
			evaluationSvc.ajouterEvaluation(evaluation);
		};
		
		$scope.cancel = function(){
			
			evaluationSvc.cancel();
		};

		$scope.fetchec = function(a){
			
			evaluationSvc.getecbyue(function(data){
			$scope.Elements=data;
			
			},$scope.evaluation.codeUe)
		};
		
		$scope.modifierEvaluation = function(){
			
			evaluationSvc.modifierEvaluation($scope.evaluation);
		};
		
		
		if($routeParams.idEvaluation){
			var idEvaluation = $routeParams.idEvaluation;
			$scope.idEvaluation = idEvaluation;
			this.afficherDetails(idEvaluation);
		}

		
    	evaluationSvc.fetchPopular(function(data){
    			$scope.evaluations=data;
    	})

		evaluationSvc.getuebyEnseignant(function(data){
			$scope.Unites=data;
		})

		

		
 	  }]);



