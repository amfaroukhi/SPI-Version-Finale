'use strict';



angular.module('app')




	.service('qualificatifSvc', ['$http', function ($http) {

		this.fetchPopular = function (callback) {
			var url = "http://localhost:8090/qualificatif/";
			$http.get(url).then(function (response) {
				callback(response.data);

			});
		};


		this.afficherDetails = function (callback, idQualificatif) {
			var url = "http://localhost:8090/qualificatif/" + idQualificatif;
			return $http.get(url).then(function (response) {
				callback(response.data);

			});
		};



		this.modifierQualificatif = function (qualificatif) {
			qualificatif["Content-Type"] = "application/json";
			return $http.put("http://localhost:8090/qualificatif/", qualificatif);
		};

		this.cancel = function () {

			window.location.href = "http://localhost:8090/index.html#/admin/qualificatifs";

		};

		this.supprimerQualificatif = function (idQualificatif) {

			return $http.delete("http://localhost:8090/qualificatif/" + idQualificatif);
		};



		this.ajouterQualificatif = function (qualificatif) {
			qualificatif["Content-Type"] = "application/json";
			return $http.post("http://localhost:8090/qualificatif", qualificatif);
		};
	}]);



angular.module('app')
	  	.controller('qualificatifCtrl', ['$scope', 'qualificatifSvc', '$routeParams', '$filter', '$rootScope','$modal',
		  function ($scope, qualificatifSvc, $routeParams, $filter, $rootScope, $modal) {


		$scope.qualificatifs = [];
		$scope.qualificatif = null;

		hideStatus();
			function hideStatus() {
				setTimeout(function () {
					$scope.$apply(function () {
						$rootScope.status = "";
					});
				}, 5000);
			}

			$scope.fermerMsg = function () {
				$rootScope.status = "";
			}

			// $scope.fermerMsg();
		

		// ******** supprimer un qualificatif (Modal)********
		$scope.supprimerQualificatif = function (idQualificatif, indextableau, min, max) {
			$rootScope.qualificatifs = $scope.qualificatifs;
			qualificatifSvc.afficherDetails(function (data) {
					$rootScope.qualificatif = data;
				}, idQualificatif);
			$modal.open({
				templateUrl: 'supprimerQualificatif',
				backdrop: true,
				windowClass: 'modal',
				controller: function ($scope, $modalInstance, $log, questionsFactory) {
				    $scope.min = min;
                    $scope.max = max;
					$scope.confirmer = function () {
						qualificatifSvc.supprimerQualificatif(idQualificatif)
							.success(function () {
								$rootScope.status = "Le couple de qualificatifs \"" + $scope.qualificatif.minimal + " - " + $scope.qualificatif.maximal + "\" a été supprimé avec succès !";
									hideStatus();
								$scope.qualificatifs.splice(indextableau, 1);
							})
							.error(function () {
								supprimerQualificatifError($scope.min,$scope.max);
							});
						$modalInstance.dismiss('cancel');
					}
					$scope.annuler = function () {
						$modalInstance.dismiss('cancel');
					};
				}
			});
		}

		function supprimerQualificatifError(min,max) {
			$modal.open({
				templateUrl: 'supprimerQualificatifError',
				backdrop: true,
				windowClass: 'modal',
				controller: function ($scope, $modalInstance, $log) {
                    $scope.min = min;
                    $scope.max = max;
					$scope.annuler = function () {
						$modalInstance.dismiss('cancel');
					};
				}
			});
		};


		$scope.data = {
			idQualificatif: "",
			maximal: "",
			minimal: ""

		};

		this.afficherDetails = function (codeF) {
			qualificatifSvc.afficherDetails(function (data) {
				$scope.qualificatif = data;

			}
				, codeF)

		};

		$scope.ajouterQualificatif = function (qualificatif) {
			qualificatifSvc.ajouterQualificatif(qualificatif).success(function (response) {
					if (qualificatif.idQualificatif == undefined) {
						$rootScope.status = "Le couple de qualificatifs \"" + qualificatif.minimal + " - " + qualificatif.maximal + "\" a été ajouté avec succès !";
						hideStatus();
					}
					else {
						$rootScope.status = "Le couple de qualificatifs \"" + qualificatif.minimal + " - " + qualificatif.maximal + "\" a été modifié avec succès !";
						hideStatus();
					}

					window.location.href = "http://localhost:8090/index.html#/admin/qualificatifs";

				});
		};



		$scope.modifierQualificatif = function () {
			qualificatifSvc.modifierQualificatif($scope.qualificatif).then(function () {
					$scope.status = " le qualif a été modifié avec succès !";
					window.location.href = "http://localhost:8090/index.html#/accueil";
					hideStatus();
				});
		};

		$scope.sortType = 'minimal'; // set the default sort type
		$scope.sortReverse = false;  // set the default sort order
		$scope.search = '';


		$scope.cancel = function () {

			qualificatifSvc.cancel();
		};

		$scope.$watch('sortReverse', function () {
			retrierTableau();
		});

		$scope.$watch('sortType', function () {
			retrierTableau();
		});

		function retrierTableau() {
			$scope.qualificatifs = $filter('orderBy')($scope.qualificatifs, $scope.sortType, $scope.sortReverse);
		}

		if ($routeParams.idQualificatif) {
			var idQualificatif = $routeParams.idQualificatif;
			this.afficherDetails(idQualificatif);
		}


		qualificatifSvc.fetchPopular(function (data) {
			$scope.qualificatifs = $filter('orderBy')(data, "'minimal'", $scope.sortReverse);

		})
	}]);


