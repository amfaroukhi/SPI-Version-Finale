'use strict';



angular.module('app')




	.service('evaluationSvc', ['$http', '$modal', function ($http, $modal) {

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

		this.getuebyEnseignant = function (callback, a) {
			var url = "http://localhost:8090/ue/getbyens/" + a;
			$http.get(url).then(function (response) {
				callback(response.data);

			});
		};

		this.getecbyue = function (callback, a) {
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

			$http.put("http://localhost:8090/evaluation/", evaluation).then(function (reponse) {
				confirmerModif();
			}, function (erreur) {
				erreurModif();
			});
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
			$http.post("http://localhost:8090/evaluation/", evaluation).then(function (reponse) {
				confirmerAjout();
			}, function (erreur) {
				erreurAjout()
			});
		};
	}]);



angular.module('app')
	  	.controller('evaluationCtrl', ['$scope', 'evaluationSvc', '$routeParams', '$filter', '$rootScope', '$modal',
		function ($scope, evaluationSvc, $routeParams, $filter, $rootScope, $modal) {

			$scope.filteredevaluations = [];
			$scope.evaluations = [];
			$scope.evaluation = {};
			$scope.evaluation.uniteEnseignementPK = {};
			$scope.Unites = [];
			$scope.Elements = [];
			$scope.Etats = [];
			$scope.Promotions = [];
			$scope.outoforder = 0;
			$scope.noEnseignant = 0;
			$scope.dateError = false;

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

			// $scope.supprimerEvaluation = function (idEvaluation, index) {

			// 	var r = confirm("Voulez vous vraiment supprimer ? ");

			// 	if (r == true) {
			// 		evaluationSvc.supprimerEvaluation(idEvaluation).then(function (res) {
			// 			$scope.evaluations.splice(index, 1);
			// 		}, function (err) {

			// 			alert("Impossible de supprimer cette évaluation ");
			// 		});

			// 	}
			// };

			// ******** supprimer une evaluation (Modal)********
			$scope.supprimerEvaluation = function (idEvaluation, index) {
				$rootScope.evaluations = $scope.evaluations;
				$modal.open({
					templateUrl: 'supprimerEvaluation',
					backdrop: true,
					windowClass: 'modal',
					controller: function ($scope, $modalInstance, $log, questionsFactory) {
						$scope.confirmer = function () {
							evaluationSvc.supprimerEvaluation(idEvaluation)
								.success(function () {
									$scope.evaluations.splice(index, 1);
								})
								.error(function () {
									supprimerEvaluationError();
								});
							$modalInstance.dismiss('cancel');
						}
						$scope.annuler = function () {
							$modalInstance.dismiss('cancel');
						};
					}
				});
			}

			function supprimerEvaluationError() {
				$modal.open({
					templateUrl: 'supprimerEvaluationError',
					backdrop: true,
					windowClass: 'modal',
					controller: function ($scope, $modalInstance, $log) {
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

				var str = $scope.evaluation.codeUe;
				var res = str.split("/");
				$scope.evaluation.codeUe = res[1];
				$scope.evaluation.noEnseignant = $scope.noEnseignant;

				$scope.evaluation.codeFormation = res[0];
				evaluationSvc.ajouterEvaluation(evaluation);
			};

			$scope.modifierEvaluation = function (evaluation) {


				$scope.evaluation.noEnseignant = $scope.noEnseignant;
				evaluationSvc.ajouterEvaluation(evaluation);
			};

			$scope.cancel = function () {

				evaluationSvc.cancel();
			};

			$scope.fetchec = function (a) {
				if ($scope.evaluation.codeUe != undefined) {
					evaluationSvc.getecbyue(function (data) {
						$scope.Elements = data;

						var str = $scope.evaluation.codeUe;
						var res = str.split("/");
						$scope.evaluation.codeFormation = res[0];

						evaluationSvc.fetchpromotions(function (data) {
							$scope.Promotions = data;
						}, $scope.evaluation.codeFormation)

					}, $scope.evaluation.codeUe)
				}
				else {
					$scope.Elements = null;
					$scope.evaluation.codeFormation = null;
					$scope.Promotions = null;
					return;
				}

			};

			$scope.modifierEvaluation = function () {

				evaluationSvc.modifierEvaluation($scope.evaluation);
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
			}


			if ($routeParams.idEvaluation) {
				var idEvaluation = $routeParams.idEvaluation;
				$scope.idEvaluation = idEvaluation;
				$scope.outoforder = 1;
				this.afficherDetails(idEvaluation);
			}

			evaluationSvc.fetchUser(function (data) {
				$scope.noEnseignant = data.noEnseignant;

				evaluationSvc.fetchPopular(function (data) {
					$scope.evaluations = $filter('orderBy')(data, "'designation'", $scope.sortReverse);
				}, $scope.noEnseignant);

				evaluationSvc.getuebyEnseignant(function (data) {
					$scope.Unites = data;
				}, $scope.noEnseignant)

			})





			evaluationSvc.fetchEtats(function (data) {
				$scope.Etats = data;

			})


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
