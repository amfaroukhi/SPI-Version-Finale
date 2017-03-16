'use strict';



angular.module('app')




  .service('evaluationSvc', ['$http', function ($http) {
	  
   this.fetchPopular = function(callback,a) {
	   				console.log(a);
   		var url = "http://localhost:8090/evaluation/getbyens/"+a;
   		$http.get(url).then(function(response){
   			callback(response.data);
   		   
   		});
   };


   this.fetchEtats = function(callback) {
   		var url = "http://localhost:8090/domain/getBydomain/ETAT-EVALUATION";
   		$http.get(url).then(function(response){
   			callback(response.data);
   		});
   };

   this.getuebyEnseignant = function(callback,a) {
   		var url = "http://localhost:8090/ue/getbyens/"+a;
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
  
	this.fetchpromotions = function(callback,a) {
  		var url = "http://localhost:8090/promotion/"+a;
  		$http.get(url).then(function(response){
  			callback(response.data);
  		});
  };
   

  
  this.modifierEvaluation = function(evaluation){
	   evaluation["Content-Type"] = "application/json"; 
	   
	   $http.put("http://localhost:8090/evaluation/",evaluation).then(function(reponse){
		  
			window.location.href ="http://localhost:8090/index.html#/enseignant/evaluations";		  
			 
		 },function(erreur){
			 alert("il y a une erreur");
		 });  
  };
  
  
  this.fetchUser = function(callback) {

		var url = "http://localhost:8090/user";
   		$http.get(url).then(function(response){
   			callback(response.data);
   		});
			
	}
   this.supprimerEvaluation = function(idEvaluation){
	

	return $http.delete("http://localhost:8090/evaluation/" + idEvaluation);
		 
	
   };
   
   this.cancel = function(){
	   
		   window.location.href ="http://localhost:8090/index.html#/enseignant/evaluations";
	
  };
   
   
   this.ajouterEvaluation = function(evaluation){
	   evaluation["Content-Type"] = "application/json";
		 $http.post("http://localhost:8090/evaluation/",evaluation).then(function(reponse){
		     
			 window.location.href ="http://localhost:8090/index.html#/enseignant/evaluations";
		 },function(erreur){
			 alert("il ya une erreur");
		 });  
	   };
  }]);



angular.module('app')
	  	.controller('evaluationCtrl', ['$scope','evaluationSvc', '$routeParams','$filter',function ($scope,evaluationSvc,$routeParams,$filter) {
	    
	  	$scope.filteredevaluations=[];
		$scope.evaluations=[];
		$scope.evaluation ={};
		$scope.evaluation.uniteEnseignementPK={};
		$scope.Unites=[];
		$scope.Elements=[];
		$scope.Etats=[];
		$scope.Promotions=[];
		$scope.outoforder=0;
		$scope.noEnseignant=0;

		$scope.open1 = function($event) {
        $event.preventDefault();
        $event.stopPropagation();
        return $scope.opened1 = true;
		};

		$scope.open = function($event) {
        $event.preventDefault();
        $event.stopPropagation();
        return $scope.opened = true;
      };
		
		
		
		
		/*
		$scope.order = function(rowName) {
		        if ($scope.row === rowName) {
		          return;
		        }
		        $scope.row = rowName;
		        $scope.filteredevaluations = $filter('orderBy')($scope.evaluations, rowName);
		        return $scope.onOrderChange();
		      };

		$scope.onOrderChange = function() {
		        $scope.select(1);
		        return $scope.currentPage = 1;
		      };
		
		      $scope.searchKeywords = '';
		      $scope.filteredevaluations = [];
		      $scope.row = '';
		      $scope.select = function(page) {
		        var end, start;
		        start = (page - 1) * $scope.numPerPage;
		        end = start + $scope.numPerPage;
		        return $scope.currentPageEvaluations = $scope.filteredevaluations.slice(start, end);
		      };
		      $scope.onFilterChange = function() {
		        $scope.select(1);
		        $scope.currentPage = 1;
		        return $scope.row = '';
		      };
		      $scope.onNumPerPageChange = function() {
		        $scope.select(1);
		        return $scope.currentPage = 1;
		      };
			  */
		$scope.supprimerEvaluation = function(idEvaluation,index){
			
			
			var r = confirm("Voulez vous vraiment supprimer ? ");
		
		    if (r == true){
			    evaluationSvc.supprimerEvaluation(idEvaluation).then(function(res){
			$scope.evaluations.splice(index,1);
				},function(err){
					
					alert("Impossible de supprimer cette évaluation ");
					console.log("Erreur suppression serveur")
				});
				
			}
		};
		
		
		
		this.afficherDetails = function(codeE){
			evaluationSvc.afficherDetails(function(data){
				$scope.evaluation= data;
			}
			,codeE)
			
		};

		$scope.execute = function(evaluation,outoforder){

			var debut = $filter('date')($scope.evaluation.debutReponse, "yyyy-MM-dd");
						var fin = $filter('date')($scope.evaluation.finReponse, "yyyy-MM-dd");


						if (debut >= fin) {
							alert("Date fin < date début ");
							return;
						}
			else
			{
				if(outoforder == 0)
				{
					$scope.ajouterEvaluation(evaluation);
				}
				else if(outoforder == 1)
				{
					$scope.modifierEvaluation(evaluation);
				}
			}

		};
		
		$scope.ajouterEvaluation = function(evaluation){
			
			var str = $scope.evaluation.codeUe;
			var res = str.split("/");
			$scope.evaluation.codeUe=res[1];
			$scope.evaluation.noEnseignant=1;
			$scope.evaluation.codeFormation=res[0];
			evaluationSvc.ajouterEvaluation(evaluation);
		};
		
		$scope.modifierEvaluation = function(evaluation){
			
			
			$scope.evaluation.noEnseignant=1;
			evaluationSvc.ajouterEvaluation(evaluation);
		};
		
		$scope.cancel = function(){
			
			evaluationSvc.cancel();
		};

		$scope.fetchec = function(a){
			if($scope.evaluation.codeUe != undefined)
			{
				evaluationSvc.getecbyue(function(data){
				$scope.Elements=data;

				var str = $scope.evaluation.codeUe;
				var res = str.split("/");
				$scope.evaluation.codeFormation=res[0];
				
				evaluationSvc.fetchpromotions(function(data){
						$scope.Promotions=data;
					},$scope.evaluation.codeFormation)
			
				},$scope.evaluation.codeUe)
			}
			else
			{
				$scope.Elements=null;
				$scope.evaluation.codeFormation=null;
				$scope.Promotions=null;
				return;
			}
		
			};
		
		$scope.modifierEvaluation = function(){
			
			evaluationSvc.modifierEvaluation($scope.evaluation);
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
			$scope.evaluations= $filter('orderBy')( $scope.evaluations,$scope.sortType,$scope.sortReverse);
		}
		
		
		if($routeParams.idEvaluation){
			var idEvaluation = $routeParams.idEvaluation;
			$scope.idEvaluation = idEvaluation;
			$scope.outoforder=1;
			this.afficherDetails(idEvaluation);
		}

		evaluationSvc.fetchUser(function(data){
			$scope.noEnseignant=data.noEnseignant;
			console.log($scope.noEnseignant);
			
			evaluationSvc.fetchPopular(function(data){
    			$scope.evaluations=$filter('orderBy')(data,"'designation'",$scope.sortReverse);
    	},$scope.noEnseignant);
		
		evaluationSvc.getuebyEnseignant(function(data){
			$scope.Unites=data;
		},$scope.noEnseignant)
		
		})
		
    	

		

		evaluationSvc.fetchEtats(function(data){
			$scope.Etats=data;
			
		})
		
		
 	  }]);



