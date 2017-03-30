'use strict';



angular.module('app')




	.service('reponseEvaluationSvc', ['$http', '$modal', function ($http, $modal) {
		
		
		
		
		////////////////////////// *********** Réponse service
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
		
		
		this.ajouterReponseEval = function (reponseEval) {
			
			reponseEval["Content-Type"] = "application/json";
			return $http.post("http://localhost:8090/reponseEvaluation/", reponseEval);
			
		};
		
		
		this.modifierReponseEval = function (callback,reponseEval) {
			
			reponseEval["Content-Type"] = "application/json";
			return $http.put("http://localhost:8090/reponseEvaluation/", reponseEval).then(function (response) {
			
			callback(response.data);
			
				
			}, function (erreur) {
				
				//erreurAjout()
			}, function (exception){
				
			}
			);
		};
		
		
		this.ajouterReponseQuestion = function (reponse) {
			
			reponse["Content-Type"] = "application/json";
			return $http.post("http://localhost:8090/reponseQuestion/", reponse).then(function (response) {
			
			
				//confirmerAjout();
				
			}, function (erreur) {
				
				//erreurAjout()
			}, function (exception){
				
			}
			);
		};
		
		
		
		this.getReponseEval = function (callback,idEvaluation,noEtudiant) {

			var url = "http://localhost:8090/reponseEvaluation/" + idEvaluation + "/" + noEtudiant ;
			return $http.get(url).then(function (response) {
				callback(response.data);
				

			});
		};
		
		this.getReponseQuestion = function (callback,idReponseEvaluation) {
			
			var url = "http://localhost:8090/reponseQuestion/" + idReponseEvaluation ;
			return $http.get(url).then(function (response) {
				callback(response.data);
				

			});
		};

		this.getPosition = function (callback,idReponseEvaluation,idQuestionEvaluation) {
			
			var url = "http://localhost:8090/reponseQuestion/reponses/" + idReponseEvaluation +"/" + idQuestionEvaluation ;
			return $http.get(url).then(function (response) {
				callback(response.data);
				

			});
		};

		
		
		////////////////////////////////////////////////::::::::::::::::
		

		
		this.fetchQuestionsRubrique = function (callback, idRubriqueEvaluation) {
			
			var url = "http://localhost:8090/questionEvaluation/questionsEvaByRubrique/" + idRubriqueEvaluation;
			return $http.get(url).then(function (response) {
				callback(response.data);

			});
		};
		
		this.fetchRubriquesEvaluation = function (callback,idEvaluation) {
			
			var url = "http://localhost:8090/RubriqueEvaluation/getbyEval/" + idEvaluation;
			return $http.get(url).then(function (response) {
				callback(response.data);
				

			});
		};
		
		
		
		


		this.afficherDetails = function (callback, idEvaluation) {
			var url = "http://localhost:8090/evaluation/" + idEvaluation;
			$http.get(url).then(function (response) {
				callback(response.data);
			});
		};

		


	}]);



angular.module('app')
	  	.controller('reponseEvaluationCtrl', ['$scope', 'reponseEvaluationSvc','AuthService', '$routeParams', '$filter', '$rootScope', '$modal',
		function ($scope, reponseEvaluationSvc, AuthService,$routeParams, $filter, $rootScope, $modal) {

			$scope.filteredevaluations = [];
			$scope.evaluations = [];
			$scope.evaluation = {};
			$scope.evaluation.uniteEnseignementPK = {};
			$scope.Unites = [];
			$scope.Elements = [];
			$scope.Etats = [];
			$scope.Promotions = [];
			$scope.rubriques = [];
			$scope.questions = [];
			$scope.rubriquesEval = [];
			$scope.rubriqueEval = {};
			//$scope.questionsEval = [];
			//$scope.questionEval = {};
			//$scope.questionsEval.question = {};
			//$scope.questionsEval.rubriqueEvaluation = {};
			$scope.rubriquesEval.rubrique = {};
			$scope.rubriquesEval.evaluation = {};
			$scope.question = {};
			$scope.rubrique = {};
			$scope.outoforder = 0;
			$scope.noEnseignant = 0;
			$scope.dateError = false;
			$scope.first = true; 
			///////////////////////:::: Objet Réponse 
			
			$scope.reponsesEvaluation = [];
			$scope.reponsesQuestion = [];
			//$scope.reponseEvaluation = "rrr";
			$scope.reponseQuestion = {};
			
			
			AuthService.getUser().success(function (d){
						if(d.role == 'ETU'){
							AuthService.getInfoEtudiant(d.noEtudiant)
							.success(function (d){
								
								$rootScope.etudiant = d;
						       console.log($rootScope.etudiant.noEtudiant)
							})
							.error(function(){
								console.log("ca ne marche absolument pas");								
							});
						}
				});
			
			
		getRubriquesEvaluation();
			
		$scope.noEtudiant = $routeParams.noEtudiant;
		
    	reponseEvaluationSvc.etudiant($scope.noEtudiant,function(data){
    		console.log($scope.noEtudiant);
    		$scope.etudiant = data;
    	});
    	
    	
    	reponseEvaluationSvc.promoEtudiant($scope.noEtudiant,function(data){
    		console.log($scope.noEtudiant);
		
    		$scope.promo = data;
		$scope.evaluations($scope.promo.promotionPK.codeFormation,$scope.promo.promotionPK.anneeUniversitaire);
    		
    	});
    	
    	$scope.evaluations = function(codeF,annee){
			
    		reponseEvaluationSvc.getEvaluations(codeF,annee,function(data){
        	$scope.evaluations = $filter('orderBy')(data,"'etat'", false );
        	console.log(data);
        	});
    	};
    	
			
			
			//getRubriquesEvaluation();
			





			this.afficherDetails = function (codeE) {
				reponseEvaluationSvc.afficherDetails(function (data) {
					$scope.evaluation = data;
				}
					, codeE)

			};

		
			
			
			
			$scope.sortType = 'designation'; // set the default sort type
			$scope.sortReverse = false;  // set the default sort order
			$scope.search = '';



			$scope.$watch('sortReverse', function () {
				retrierTableau();
			});

			$scope.$watch('sortType', function () {
				retrierTableau();
			});

			function retrierTableau() {
				$scope.evaluations = $filter('orderBy')($scope.evaluations, $scope.sortType, $scope.sortReverse);
				$scope.rubriquesEval = $filter('orderBy')($scope.rubriquesEval, $scope.sortType, $scope.sortReverse);
				$scope.rubriquesEval.questionsEval = $filter('orderBy')($scope.rubriquesEval.questionsEval, $scope.sortType, $scope.sortReverse);
			}


			if ($routeParams.idEvaluation) {
				var idEvaluation = $routeParams.idEvaluation;
				$scope.idEvaluation = idEvaluation;
				$scope.outoforder = 1;
				this.afficherDetails(idEvaluation);
			}


			$scope.modifierOrdreUp = function (rubrique,rubrique1) {
				rubrique1.ordre = rubrique.ordre;
				rubrique.ordre --;
				reponseEvaluationSvc.ajouterRubEval(rubrique);
				reponseEvaluationSvc.ajouterRubEval(rubrique1);
				$scope.sortType='ordre';
				retrierTableau();
				
			
			};
			
			$scope.modifierOrdreDown = function (rubrique,rubrique1) {
				rubrique1.ordre = rubrique.ordre;
				rubrique.ordre ++;
				reponseEvaluationSvc.ajouterRubEval(rubrique);
				reponseEvaluationSvc.ajouterRubEval(rubrique1);
				$scope.sortType='ordre';
				retrierTableau();
			};
			
			
			
			
			$scope.ajouterRubEval = function (r) {
				
				
				$scope.rubriqueEval.evaluation = $scope.evaluation;
				$scope.rubriqueEval.rubrique = angular.fromJson(r);
				$scope.rubriqueEval.ordre = $scope.rubriquesEval.length + 1;
				
				reponseEvaluationSvc.ajouterRubEval($scope.rubriqueEval)
				.then(function(){
					getRubriquesEvaluation();
				});
					
			};
			
			
			$scope.ajouterQuestionsRubrique = function (q,r) {
				
				
				
				
					
			$scope.questionsEval = [];
			$scope.questionEval = {};
			$scope.questionsEval.question = {};
			$scope.questionsEval.rubriqueEvaluation = {};
				$scope.questionEval.question  = angular.fromJson(q);
				
				$scope.questionEval.rubriqueEvaluation = angular.fromJson(r);
				
				$scope.questionEval.ordre = $scope.questionsEval.length + 1;
				
				reponseEvaluationSvc.ajouterQuestionsRubrique($scope.questionEval)
				.then(function(){
					getRubriquesEvaluation();
					getQuestions();
				});
					
				
			};
			
			
	
			
			function getRubriquesEvaluation(){	
			
			
			reponseEvaluationSvc.fetchRubriquesEvaluation(function (data) {
				
				
				$scope.rubriquesEval = $filter('orderBy')(data, "'ordre'", $scope.sortReverse);
				
			}, $scope.idEvaluation)
			.then(function(res) {
				
				angular.forEach($scope.rubriquesEval, function(value , key){
					
					reponseEvaluationSvc.fetchQuestionsRubrique(function(data) {
					
						
						
		
						value.questionsEval = $filter('orderBy')(data, "'ordre'", $scope.sortReverse);
						
						
					}, value.idRubriqueEvaluation)
					.then(function(res){
						
						
						angular.forEach(value.questionsEval, function(value , key){
							
							reponseEvaluationSvc.getPosition( function(data){
							
								if (data[0] != undefined) value.positionnement = data[0].positionnement;
								
								
							},$rootScope.reponseEvaluation, value.idQuestionEvaluation);
						})
					
					});
					
					
			
			
				});
				
				
			});
			}
			
			
			$scope.ajouterReponseEval = function (e) {
			
			
			
			$scope.reponseEvaluation = {};
			
				reponseEvaluationSvc.getReponseEval(function (data) {
					  
					
					  
					if( data.length == 0) {
						
						
						
						
						
						$scope.reponseEvaluation.evaluation = e;
						$scope.reponseEvaluation.noEtudiant = $rootScope.etudiant.noEtudiant;
						$scope.reponseEvaluation.nom = $rootScope.etudiant.nom;
						$scope.reponseEvaluation.prenom = $rootScope.etudiant.prenom;
						
						reponseEvaluationSvc.ajouterReponseEval ($scope.reponseEvaluation)
						.then(function (response) {
							
							$rootScope.reponseEvaluation = response.data.idReponseEvaluation;
							$rootScope.re = response.data;
							$scope.reponseEvaluation= response.data.idReponseEvaluation;
							window.location.href = "http://localhost:8090/index.html#/etudiant/evaluation/" +$rootScope.etudiant.noEtudiant+ "/" + e.idEvaluation + "/"+$scope.reponseEvaluation;
						});
						  
					} 
					else {
						
						
						$rootScope.reponseEvaluation = data[0].idReponseEvaluation;
						$rootScope.re = data[0];
						$scope.reponseEvaluation= data[0].idReponseEvaluation;
							
						window.location.href = "http://localhost:8090/index.html#/etudiant/evaluation/" +$rootScope.etudiant.noEtudiant+ "/"+ e.idEvaluation + "/"+$scope.reponseEvaluation ;
						  
						  
					}
					
				},e.idEvaluation,$rootScope.etudiant.noEtudiant);
				
				
				

			}
			
			$scope.ajouterModification = function (c) {
				
				$rootScope.re.commentaire = c;
				
				reponseEvaluationSvc.ajouterReponseEval($rootScope.re);
				window.location.href = "http://localhost:8090/index.html#/etudiant/"+$rootScope.etudiant.noEtudiant;
			}
			
			
			
			
						
			$scope.consulterReponseEval = function (e) {
				
			
		
			$scope.reponseEvaluation = {};
			
				reponseEvaluationSvc.getReponseEval(function (data) {
					  
					
					  
					if( data.length == 0) {
						
						
						
						
						$scope.reponseEvaluation.evaluation = e;
						$scope.reponseEvaluation.noEtudiant = $rootScope.etudiant.noEtudiant;
						$scope.reponseEvaluation.nom = $rootScope.etudiant.nom;
						$scope.reponseEvaluation.prenom = $rootScope.etudiant.prenom;
						
						reponseEvaluationSvc.ajouterReponseEval ($scope.reponseEvaluation)
						.then(function (response) {
							
							$rootScope.reponseEvaluation = response.data.idReponseEvaluation;
							$rootScope.re = response.data;
							$scope.reponseEvaluation= response.data.idReponseEvaluation;
							window.location.href = "http://localhost:8090/index.html#/etudiant/evaluation/reponse/" + $rootScope.etudiant.noEtudiant+"/"+ e.idEvaluation + "/"+$scope.reponseEvaluation;
						});
						  
					} 
					else {
						
						
						$rootScope.reponseEvaluation = data[0].idReponseEvaluation;
						$rootScope.re = data[0];
						$scope.reponseEvaluation= data[0].idReponseEvaluation;
							
						window.location.href = "http://localhost:8090/index.html#/etudiant/evaluation/reponse/"+ $rootScope.etudiant.noEtudiant+"/"+ e.idEvaluation + "/"+$scope.reponseEvaluation ;
						
						  
					}
					
				},e.idEvaluation,$rootScope.etudiant.noEtudiant);
				
				
				

			}
			
			$scope.ajouterModification = function (c) {

				$rootScope.re.commentaire = c;
				
				reponseEvaluationSvc.ajouterReponseEval($rootScope.re);
				window.location.href = "http://localhost:8090/index.html#/etudiant/"+$rootScope.etudiant.noEtudiant;
			}
			
			
			
			
			
			
			$scope.ajouterReponseQuestion = function (q,p) {
				
				
				$scope.objet = {
					idReponseEvaluation : "",
					idQuestionEvaluation : "",
					reponseQuestion: {
						    positionnement : "",
							id : {
								idReponseEvaluation :"",
								idQuestionEvaluation :""
							
							}
						
					}
					
				}
				
				$scope.objet.reponseQuestion.positionnement = p;
				q.positionnement = p;
				$scope.objet.idReponseEvaluation = $rootScope.reponseEvaluation; 
				$scope.objet.idQuestionEvaluation = q.idQuestionEvaluation; 
				$scope.objet.reponseQuestion.id.idReponseEvaluation = $rootScope.reponseEvaluation;
				$scope.objet.reponseQuestion.id.idQuestionEvaluation = q.idQuestionEvaluation; 
				reponseEvaluationSvc.ajouterReponseQuestion ($scope.objet);
				
				
			}
			
			
			
			$scope.cancel = function() {
				
				window.location.href = "http://localhost:8090/index.html#/etudiant/"+ $rootScope.etudiant.noEtudiant; 
			}
			
			
					
		
		}]);

