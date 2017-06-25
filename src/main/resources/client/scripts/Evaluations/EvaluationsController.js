'use strict';


// ================================== cgrc Service ==================================
angular.module('app').service('cgrcSvc', ['$http', '$modal', function ($http, $modal) {

	this.getDomainEtatEvaluation = function (callback) {
		var url = "http://localhost:8090/domain/getBydomain/ETAT-EVALUATION";
		$http.get(url).then(function (response) {
			callback(response.data);
		});
	};

}]);

// ================================== evaluation Service ==================================
angular.module('app').service('evaluationSvc', ['$http', '$modal', '$rootScope', function ($http, $modal, $rootScope) {


	this.fetchRubriques = function (callback) {
		var url = "http://localhost:8090/rubrique/";
		$http.get(url).then(function (response) {
			callback(response.data);

		});
	};


	this.fetchQuestions = function (callback) {
		var url = "http://localhost:8090/question/";
		$http.get(url).then(function (response) {
			callback(response.data);

		});
	};

	this.fetchQuestionsRubrique = function (callback, idRubriqueEvaluation) {

		var url = "http://localhost:8090/questionEvaluation/questionsEvaByRubrique/" + idRubriqueEvaluation;
		return $http.get(url).then(function (response) {
			callback(response.data);

		});
	};

	this.fetchRubriquesEvaluation = function (callback, idEvaluation) {
		var url = "http://localhost:8090/RubriqueEvaluation/getbyEval/" + idEvaluation;
		return $http.get(url).then(function (response) {
			callback(response.data);


		});
	};

	this.supprimerRubEval = function (idRubriqueEvaluation) {


		return $http.delete("http://localhost:8090/RubriqueEvaluation/" + idRubriqueEvaluation);


	};




	this.supprimerQuestionsRubrique = function (idquestionRub) {


		return $http.delete("http://localhost:8090/questionEvaluation/" + idquestionRub);


	};

	this.ajouterRubEval = function (rubriqueEval) {
		rubriqueEval["Content-Type"] = "application/json";
		return $http.post("http://localhost:8090/RubriqueEvaluation/", rubriqueEval).then(function (reponse) {
			//confirmerAjout();

		}, function (erreur) {
			console.log(erreur);
			//erreurAjout()
		}, function (exception) {
			console.log(exception);
		}
		);
	};


	this.ajouterQuestionsRubrique = function (questionRub) {
		questionRub["Content-Type"] = "application/json";
		return $http.post("http://localhost:8090/questionEvaluation/", questionRub).then(function (reponse) {
			//confirmerAjout();

		}, function (erreur) {
			console.log(erreur);
			//erreurAjout()
		}, function (exception) {
			console.log(exception);
		}
		);
	};




	this.fetchPopular = function (callback, a) {
		var url = "http://localhost:8090/evaluation/getbyens/" + a;
		$http.get(url).then(function (response) {
			callback(response.data);
		});
	};


	this.fetchEtats = function (callback) {
		var url = "http://localhost:8090/domain/getBydomain/ETAT-EVALUATION";
		$http.get(url).then(function (response) {
			callback(response.data);
		});
	};

	this.getuebyEnseignantandFormation = function (callback, a, b) {
		var url = "http://localhost:8090/ue/getbyensandFormation/" + a + "/" + b;
		$http.get(url).then(function (response) {
			callback(response.data);

		});
	};

	this.getuebyEnseignant = function (callback, noEns) {
		var url = "http://localhost:8090/ue/getbyens/" + noEns;
		$http.get(url).then(function (response) {
			callback(response.data);
		});
	};

	this.getformations = function (callback, a) {
		var url = "http://localhost:8090/formation/";
		$http.get(url).then(function (response) {
			callback(response.data);

		});
	};

	this.getecbyue = function (callback, a, b) {
		var url = "http://localhost:8090/ec/" + a + "/" + b;
		$http.get(url).then(function (response) {
			callback(response.data);
		});
	};

	this.getecs = function (callback, a) {
		var url = "http://localhost:8090/ec/" + a;
		$http.get(url).then(function (response) {
			callback(response.data);
		});
	};


	this.afficherDetails = function (callback, idEvaluation) {
		var url = "http://localhost:8090/evaluation/" + idEvaluation;
		$http.get(url).then(function (response) {
			callback(response.data);
		});
	};

	this.fetchpromotions = function (callback, a) {
		var url = "http://localhost:8090/promotion/" + a;
		$http.get(url).then(function (response) {
			callback(response.data);
		});
	};


	function confirmerModif() {
		$modal.open({
			templateUrl: 'confirmerModif',
			backdrop: true,
			windowClass: 'modal',
			controller: function ($scope, $modalInstance, $log) {
				$scope.annuler = function () {
					window.location.href = "http://localhost:8090/index.html#/enseignant/evaluations";
					$modalInstance.dismiss('cancel');
				};
			}
		});
	};

	function erreurModif() {
		$modal.open({
			templateUrl: 'erreurModif',
			backdrop: true,
			windowClass: 'modal',
			controller: function ($scope, $modalInstance, $log) {
				$scope.annuler = function () {
					$modalInstance.dismiss('cancel');
				};
			}
		});
	};

	function confirmerAjout() {
		$modal.open({
			templateUrl: 'confirmerAjout',
			backdrop: true,
			windowClass: 'modal',
			controller: function ($scope, $modalInstance, $log) {
				$scope.annuler = function () {
					window.location.href = "http://localhost:8090/index.html#/enseignant/evaluations";
					$modalInstance.dismiss('cancel');
				};
			}
		});
	};

	function erreurAjout() {
		$modal.open({
			templateUrl: 'erreurAjout',
			backdrop: true,
			windowClass: 'modal',
			controller: function ($scope, $modalInstance, $log) {
				$scope.annuler = function () {
					$modalInstance.dismiss('cancel');
				};
			}
		});
	};

	this.modifierEvaluation = function (evaluation) {
		evaluation["Content-Type"] = "application/json";

		return $http.put("http://localhost:8090/evaluation/", evaluation)
			.error(function () {
				$rootScope.error = "Erreur lors de la modification de l\'évaluation !"
				// erreurAjout();
			});
		// .then(function (reponse) {
		// 	confirmerModif();
		// }, function (erreur) {
		// 	erreurModif();
		// });
	};


	this.fetchUser = function (callback) {

		var url = "http://localhost:8090/user";
		$http.get(url).then(function (response) {
			callback(response.data);
		});

	}
	this.supprimerEvaluation = function (idEvaluation) {


		return $http.delete("http://localhost:8090/evaluation/" + idEvaluation);


	};

	this.cancel = function () {

		window.location.href = "http://localhost:8090/index.html#/enseignant/evaluations";

	};


	this.ajouterEvaluation = function (evaluation) {
		evaluation["Content-Type"] = "application/json";
		return $http.post("http://localhost:8090/evaluation/", evaluation)
			.error(function () {
				$rootScope.error = "Une évaluation avec les mêmes informations existe déjà !"
				// erreurAjout();
			});
		// .then(function (reponse) {
		// 	// confirmerAjout();

		// }, function (erreur) {
		// 	erreurAjout()
		// });
	};
}]);


// ================================== EvaluationController ==================================
angular.module('app').controller('evaluationCtrl', ['$scope', 'evaluationSvc', 'cgrcSvc', '$routeParams', '$filter', '$rootScope', '$modal',
	function ($scope, evaluationSvc, cgrcSvc, $routeParams, $filter, $rootScope, $modal) {

		$scope.filteredevaluations = [];
		$scope.evaluations = [];
		$scope.evaluation = {};
		$scope.evaluation.uniteEnseignementPK = {};
		$scope.Unites = [];
		$scope.Elements = [];
		$scope.Etats = [];
		$scope.Promotions = [];
		$scope.Formations = [];
		$scope.rubriques = [];
		$scope.questions = [];
		$scope.rubriquesEval = [];
		$scope.rubriqueEval = {};
		$scope.listElements = [];
		$scope.listUnites = [];
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


		getQuestions();
		getRubriquesEvaluation();

		hideStatus();
        function hideStatus() {
            setTimeout(function () {
                $scope.$apply(function () {
                    $rootScope.status = "";
                    $rootScope.error = "";
                });
            }, 5000);
        }

        $scope.fermerMsg = function () {
            $rootScope.status = "";
        }

		evaluationSvc.fetchUser(function (data) {
			$scope.noEnseignant = data.noEnseignant;

			evaluationSvc.fetchPopular(function (data) {
				$scope.evaluations = $filter('orderBy')(data, ['codeFormation', '-anneeUniversitaire', '-etat']);
				getECs();
				getUEs();
				console.log($scope.noEnseignant)
			}, $scope.noEnseignant);



		})

		evaluationSvc.fetchEtats(function (data) {
			$scope.Etats = data;

		})

		evaluationSvc.getformations(function (data) {
			$scope.Formations = data;

		})

		cgrcSvc.getDomainEtatEvaluation(function (data) {
			$scope.DomainEtatEvaluation = data;
		});

		$scope.designEtatEvaluation = function (abv) {
			var meaning;
			angular.forEach($scope.DomainEtatEvaluation, function (value, key) {
				if (value.rvAbbreviation == abv) {
					meaning = value.rvMeaning;
				}
			});
			return meaning;
		}




		function getECs() {
			evaluationSvc.getecs(function (data) {
				$scope.listElements = data;
			}, "");
		}

		$scope.designEC = function (abv) {
			var meaning;
			angular.forEach($scope.listElements, function (value, key) {
				if (value.elementConstitutifPK.codeEc == abv) {
					meaning = value.designation;
				}
			});
			return meaning;
		}

		function getUEs() {
			evaluationSvc.getuebyEnseignant(function (data) {
				$scope.listUnites = data;
			}, $scope.noEnseignant);
		}

		$scope.designUE = function (abv) {
			var meaning;
			angular.forEach($scope.listUnites, function (value, key) {
				if (value.uniteEnseignementPK.codeUe == abv) {
					meaning = value.designation;
				}
			});
			return meaning;
		}


		$scope.open1 = function ($event) {
			$event.preventDefault();
			$event.stopPropagation();
			$scope.opened = false;
			return $scope.opened1 = true;
		};

		$scope.open = function ($event) {
			$event.preventDefault();
			$event.stopPropagation();
			$scope.opened1 = false;
			return $scope.opened = true;
		};



		// ******** supprimer une evaluation (Modal)********
		$scope.supprimerEvaluation = function (idEvaluation, designation, index) {
			$rootScope.evaluations = $scope.evaluations;
			$modal.open({
				templateUrl: 'supprimerEvaluation',
				backdrop: true,
				windowClass: 'modal',
				controller: function ($scope, $modalInstance, $log, questionsFactory) {
					$scope.designEval = designation;
					$scope.confirmer = function () {
						evaluationSvc.supprimerEvaluation(idEvaluation)
							.success(function () {
								$scope.evaluations.splice(index, 1);
								$rootScope.status = "L'évaluation " + designation + " a été supprimée avec succès !";
                   				hideStatus();
							})
							.error(function () {
								supprimerEvaluationError(designation);
							});
						$modalInstance.dismiss('cancel');
					}
					$scope.annuler = function () {
						$modalInstance.dismiss('cancel');
					};
				}
			});
		}

		function supprimerEvaluationError(designation) {
			$modal.open({
				templateUrl: 'supprimerEvaluationError',
				backdrop: true,
				windowClass: 'modal',
				controller: function ($scope, $modalInstance, $log) {
					$scope.designEval = designation;
					$scope.annuler = function () {
						$modalInstance.dismiss('cancel');
					};
				}
			});
		};



		this.afficherDetails = function (codeE) {
			evaluationSvc.afficherDetails(function (data) {
				$scope.evaluation = data;
			}
				, codeE)

		};

		$scope.execute = function (evaluation, outoforder) {

			var debut = $filter('date')($scope.evaluation.debutReponse, "yyyy-MM-dd");
			var fin = $filter('date')($scope.evaluation.finReponse, "yyyy-MM-dd");

			if (debut >= fin) {
				$scope.dateError = true;
				return;
			}
			else {
				if (outoforder == 0) {
					$scope.ajouterEvaluation(evaluation);
				}
				else if (outoforder == 1) {
					$scope.modifierEvaluation(evaluation);
				}
			}

		};

		$scope.ajouterEvaluation = function (evaluation) {

			$scope.evaluation.noEnseignant = $scope.noEnseignant;
			evaluationSvc.ajouterEvaluation(evaluation)
				.then(function (response) {
					$rootScope.error = "";
					$rootScope.status = "L'évaluation " + evaluation.designation + " a été bien ajoutée ";
					window.location.href = "http://localhost:8090/index.html#/enseignant/evaluation/" + response.data.idEvaluation;
					
				});
		};

		
		// $scope.modifierEvaluation = function () {

		// 	evaluationSvc.modifierEvaluation($scope.evaluation);

			
		// };

		$scope.modifierEvaluation = function (evaluation) {


			$scope.evaluation.noEnseignant = $scope.noEnseignant;
			evaluationSvc.modifierEvaluation(evaluation)
				.then(function (response) {
					$rootScope.error = "";
					$rootScope.status = "L'évaluation " + evaluation.designation + " a été mise à jour.";
					window.location.href = "http://localhost:8090/index.html#/enseignant/evaluations";
					
				});

			
		};

		$scope.cancel = function () {

			evaluationSvc.cancel();
		};

		$scope.fetchproms = function (a) {
			evaluationSvc.fetchpromotions(function (data) {
				$scope.Promotions = data;
			}, $scope.evaluation.codeFormation)
		}

		$scope.fetchue = function (a, b) {
			evaluationSvc.getuebyEnseignantandFormation(function (data) {
				$scope.Unites = data;
				$scope.evaluation.codeUe = "";
				$scope.evaluation.codeEc = "";
				$scope.evaluation.anneeUniversitaire = "";
				$scope.Elements = [];
			}, $scope.noEnseignant, $scope.evaluation.codeFormation)
		}

		$scope.fetchec = function (a) {

			$scope.evaluation.codeEc = "";
			if ($scope.evaluation.codeUe != undefined) {
				evaluationSvc.getecbyue(function (data) {
					$scope.Elements = data;
				}, $scope.evaluation.codeFormation, $scope.evaluation.codeUe)
			}
			else {
				$scope.Elements = [];
				$scope.Promotions = [];
				return;
			}

		};







		$scope.sortType = 'etat'; // set the default sort type
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



		$scope.modifierOrdreUp = function (rubrique, rubrique1) {
			rubrique1.ordre = rubrique.ordre;
			rubrique.ordre--;
			evaluationSvc.ajouterRubEval(rubrique);
			evaluationSvc.ajouterRubEval(rubrique1);
			$scope.sortType = 'ordre';
			retrierTableau();


		};

		$scope.modifierOrdreDown = function (rubrique, rubrique1) {
			rubrique1.ordre = rubrique.ordre;
			rubrique.ordre++;
			evaluationSvc.ajouterRubEval(rubrique);
			evaluationSvc.ajouterRubEval(rubrique1);
			$scope.sortType = 'ordre';
			retrierTableau();
		};


		$scope.modifierOrdreUpQ = function (rubrique, rubrique1) {
			rubrique1.ordre = rubrique.ordre;
			rubrique.ordre--;
			evaluationSvc.ajouterQuestionsRubrique(rubrique);
			evaluationSvc.ajouterQuestionsRubrique(rubrique1);
			$scope.sortType = 'ordre';
			getRubriquesEvaluation();
			//retrierTableau();


		};

		$scope.modifierOrdreDownQ = function (rubrique, rubrique1) {
			rubrique1.ordre = rubrique.ordre;
			rubrique.ordre++;
			evaluationSvc.ajouterQuestionsRubrique(rubrique);
			evaluationSvc.ajouterQuestionsRubrique(rubrique1);
			$scope.sortType = 'ordre';
			getRubriquesEvaluation();
			//retrierTableau();
		};

		$scope.ajouterRubEval = function (r) {


			//getRubriquesEvaluation(function(){
			$scope.rubriqueEval.evaluation = $scope.evaluation;
			$scope.rubriqueEval.rubrique = angular.fromJson(r);
			$scope.rubriqueEval.ordre = $scope.rubriquesEval.length + 1;
			console.log($scope.rubriqueEval)
			evaluationSvc.ajouterRubEval($scope.rubriqueEval)
				.then(function () {
					getRubriquesEvaluation();
					$scope.rubriqueEval = "";
				});
			//});

		};


		$scope.ajouterQuestionsRubrique = function (q, r) {



			//getQuestionsRubrique(function(){

			$scope.questionsEval = [];
			$scope.questionEval = {};
			$scope.questionsEval.question = {};
			$scope.questionsEval.rubriqueEvaluation = {};
			$scope.questionEval.question = angular.fromJson(q);

			$scope.questionEval.rubriqueEvaluation = angular.fromJson(r);

			$scope.questionEval.ordre = $scope.questionsEval.length + 1;
			evaluationSvc.ajouterQuestionsRubrique($scope.questionEval)
				.then(function () {
					getRubriquesEvaluation();
					getQuestions();
					$scope.selectedQuestion = ""
				});
			//});

		};



		$scope.supprimerRubEval = function (id, index) {

			var r = confirm("Voulez vous vraiment supprimer ? ");

			if (r == true) {
				evaluationSvc.supprimerRubEval(id).then(function (res) {
					$scope.rubriquesEval.splice(index, 1);
				}, function (err) {

					alert("Impossible de supprimer cette rubrique de cette eval ");
				});

			}
		};






		$scope.supprimerQuestionsRubrique = function (id, index) {

			var r = confirm("Voulez vous vraiment supprimer cette question ? ");

			if (r == true) {
				evaluationSvc.supprimerQuestionsRubrique(id).then(function (res) {
					getRubriquesEvaluation();
				}, function (err) {

					alert("Impossible de supprimer cette rubrique de cette eval ");
				});

			}
		};


		evaluationSvc.fetchRubriques(function (data) {
			$scope.rubriques = $filter('orderBy')(data, "'ordre'", $scope.sortReverse);
		})

		function getQuestions() {
			evaluationSvc.fetchQuestions(function (data) {
				$scope.questions = $filter('orderBy')(data, "'intitule'", $scope.sortReverse);
			});
		}


		/*function getRubriquesEvaluation(callback){	
		callback();
			//evaluationSvc.fetchRubriquesEvaluation(function (data) {
			//$scope.rubriquesEval = $filter('orderBy')(data, "'ordre'", $scope.sortReverse);
		//}, $scope.idEvaluation);
		
		
		
		evaluationSvc.fetchRubriquesEvaluation(function (data) {
			
			$scope.rubriquesEval = $filter('orderBy')(data, "'ordre'", $scope.sortReverse);
			
		}, $scope.idEvaluation)
		.then(function(res) {
			
			angular.forEach($scope.rubriquesEval, function(value , key){
				
				evaluationSvc.fetchQuestionsRubrique(function(data) {
					
					$scope.questionsEval = $filter('orderBy')(data, "'ordre'", $scope.sortReverse);
			
				}, value.idRubriqueEvaluation);
		
			});
		});
		
		
		
		
		}
		
		
		
		function getQuestionsRubrique(callback){	
			callback();
			evaluationSvc.fetchQuestionsRubrique(function (data) {
			$scope.questionsEval = $filter('orderBy')(data, "'ordre'", $scope.sortReverse);
		}, $scope.rubriquesEval.idRubriqueEvaluation);
		}*/



		//evaluationSvc.fetchQuestionsRubrique(function(data) {

		//	$scope.questionsEval = $filter('orderBy')(data, "'ordre'", $scope.sortReverse);

		//}, $scope.rubriquesEval.idRubriqueEvaluation);


		function getRubriquesEvaluation() {
			evaluationSvc.fetchRubriquesEvaluation(function (data) {


				$scope.rubriquesEval = $filter('orderBy')(data, "'ordre'", $scope.sortReverse);

			}, $scope.idEvaluation)
				.then(function (res) {

					angular.forEach($scope.rubriquesEval, function (value, key) {

						//$scope.questionsEval = [];


						//console.log("avant : "+$scope.questionsEval);
						evaluationSvc.fetchQuestionsRubrique(function (data) {


							$scope.rubriquesEval[key].questionsEval = $filter('orderBy')(data, "'ordre'", $scope.sortReverse);

						}, value.idRubriqueEvaluation);


					});
				});
		}



	}]);


angular.module('app').directive('chars', function () {
	'use strict';
	return {
		require: 'ngModel',
		restrict: 'A',
		link: function ($scope, $elem, attrs, ctrl) {
			var regReplace,
				preset = {
					'only-numbers': '0-9',
					'numbers': '0-9\\s',
					'only-letters': 'A-Za-z',
					'letters': 'A-Za-z\\s',
					'email': '\\wÑñ@._\\-',
					'alpha-numeric': '\\w\\s',
					'latin-alpha-numeric': '\\w\\sÑñáéíóúüÁÉÍÓÚÜ'
				},
				filter = preset[attrs.chars] || attrs.chars;
			$elem.on('input', function () {
				regReplace = new RegExp('[^' + filter + ']', 'ig');
				ctrl.$setViewValue($elem.val().replace(regReplace, ''));
				ctrl.$render();
			});

		}
	};

});
