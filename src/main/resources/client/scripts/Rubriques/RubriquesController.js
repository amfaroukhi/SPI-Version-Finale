'use strict';



angular.module('app')


	.service('rubriqueSvc', ['$http', function ($http) {

		this.fetchPopular = function (callback) {
			var url = "http://localhost:8090/rubrique/";
			$http.get(url).then(function (response) {
				callback(response.data);

			});
		};



		this.cancel = function () {

			window.location.href = "http://localhost:8090/index.html#/admin/rubriques";
		};

		this.supprimerRubrique = function (idRubrique) {

			return $http.delete("http://localhost:8090/rubrique/" + idRubrique);


		};


		this.afficherDetails = function (callback, idRubrique) {
			var url = "http://localhost:8090/rubrique/" + idRubrique;
			$http.get(url).then(function (response) {
				callback(response.data);

			});
		};

		this.ajouterRubrique = function (rubrique) {
			rubrique["Content-Type"] = "application/json";
			$http.post("http://localhost:8090/rubrique", rubrique).then(function (reponse) {
				window.location.href = "http://localhost:8090/index.html#/admin/rubriques";
			}, function (erreur) {
				alert("il ya une erreur");
			});
		};
	}]);


angular.module('app')
	  	.controller('rubriqueCtrl', ['$scope', 'rubriqueSvc', '$routeParams', '$filter', '$rootScope', '$modal',
		function ($scope, rubriqueSvc, $routeParams, $filter, $rootScope, $modal) {

			$scope.rubriques = {};
			$scope.rubrique = {
				type: "RBS"
				
			
			};

			$scope.data = {
				idRubrique: "",
				ordre: "",
				type: "",
				designantion: ""
			};

			this.afficherDetails = function (codeF) {
				rubriqueSvc.afficherDetails(function (data) {
					$scope.rubrique = data;
				}
					, codeF)
			};

			// $scope.supprimerRubrique = function (idRubrique, indextableau) {
			// 	console.log("index tableau " + indextableau);
			// 	var r = confirm("Voulez vous vraiment supprimer ? ");

			// 	if (r == true) {
			// 		rubriqueSvc.supprimerRubrique(idRubrique).then(function (res) {
			// 			$scope.rubriques.splice(indextableau, 1);
			// 		}, function (err) {
			// 			alert("Impossible de supprimer cette rubrique ");
			// 			console.log("Erreur suppression serveur")
			// 		});
			// 	}
			// };

			// ******** supprimer une rubrique (Modal)********
			$scope.supprimerRubrique = function (idRubrique, indextableau) {
				$rootScope.rubriques = $scope.rubriques;
				$modal.open({
					templateUrl: 'supprimerRubrique',
					backdrop: true,
					windowClass: 'modal',
					controller: function ($scope, $modalInstance, $log, questionsFactory) {
						$scope.confirmer = function () {
							rubriqueSvc.supprimerRubrique(idRubrique)
								.success(function () {
									$scope.rubriques.splice(indextableau, 1);
								})
								.error(function () {
									supprimerRubriqueError();
								});
							$modalInstance.dismiss('cancel');
						}
						$scope.annuler = function () {
							$modalInstance.dismiss('cancel');
						};
					}
				});
			}

			function supprimerRubriqueError() {
				$modal.open({
					templateUrl: 'supprimerRubriqueError',
					backdrop: true,
					windowClass: 'modal',
					controller: function ($scope, $modalInstance, $log) {
						$scope.annuler = function () {
							$modalInstance.dismiss('cancel');
						};
					}
				});
			};





			$scope.ajouterRubrique = function (rubrique) {
				
				rubrique.ordre= $scope.rubriques.length + 1;
				rubriqueSvc.ajouterRubrique(rubrique);
				
			};
			
			$scope.modifierOrdreUp = function (rubrique,rubrique1) {
				rubrique1.ordre = rubrique.ordre;
				rubrique.ordre --;
				rubriqueSvc.ajouterRubrique(rubrique);
				rubriqueSvc.ajouterRubrique(rubrique1);
				$scope.sortType='ordre';
				retrierTableau();
				
			
			};
			
			$scope.modifierOrdreDown = function (rubrique,rubrique1) {
				rubrique1.ordre = rubrique.ordre;
				rubrique.ordre ++;
				rubriqueSvc.ajouterRubrique(rubrique);
				rubriqueSvc.ajouterRubrique(rubrique1);
				$scope.sortType='ordre';
				retrierTableau();
			};

			$scope.cancel = function () {

				rubriqueSvc.cancel();
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
			
			
			//$scope.$watch('sortReverse', function (r) {
				//ajouterRubrique(r);
			//});
			
			function retrierTableau() {
				$scope.rubriques = $filter('orderBy')($scope.rubriques, $scope.sortType, $scope.sortReverse); console.log("retrier");
			}

			if ($routeParams.idRubrique) {
				var idRubrique = $routeParams.idRubrique;
				this.afficherDetails(idRubrique);
			}


			rubriqueSvc.fetchPopular(function (data) {
				$scope.rubriques = $filter('orderBy')(data, "'ordre'", $scope.sortReverse);
			})
		}]);



