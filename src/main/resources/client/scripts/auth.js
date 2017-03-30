angular.module('app.auth', [])

	/**
	 * A simple example service that returns some data.
	 */
	.factory('AuthService', function ($http, $window) {

		return {
			authLocal: function (requestAuth) {
				config = {
					url: '/auth',
					method: "POST",
					data: requestAuth
				};
				return $http(config);
			},
			getUser: function () {
				config = {
					url: '/user',
					method: "GET"
				}
				return $http(config);
			},
			deconnexion: function () {
				config = {
					url: '/deconnexion',
					method: "GET"
				}
				return $http(config);
			},
			getInfoEtudiant: function (noEtudiant) {
				config = {
					url: '/etudiant/'+noEtudiant,
					method: "GET"
				}
				return $http(config);
			},
			getInfoEnseignant: function (noEnseignant) {
				config = {
						url: 'enseignant/getens/'+noEnseignant,
						method: "GET"
				}
				return $http(config);
			}
		}

	})

	/**
	 * Controleur pour la page d'authentification.
	 */
	.controller(
	'AuthenticationController',
	['$scope', '$location', '$animate', 'AuthService', 'dataFactory', '$routeParams',
		function ($scope, $location, $animate, AuthService, $routeParams, dataFactory) {
			this.login = {};
			$scope.etu = {};
			
			/*
			 * // Nom utilisateur et image (affichés dans le header)
			 * $scope.username = auth.username(); $scope.userimg = ""; //
			 * Réception de l'événement de login (voir le service
			 * 'auth') $scope.$on('login-success', function(){
			 * $scope.username = auth.username(); $scope.userimg =
			 * auth.userimg(); })
			 */

			// Executé lors du click sur le bouton de login
			this.submit = function () {
				var authuser = {
					"username": this.login.username,
					"pwd": this.login.password,
				};
				
				AuthService.authLocal(authuser).success(function () {
					AuthService.getUser().success(function (data){
						if(data.role == 'ETU'){
							AuthService.getInfoEtudiant(data.noEtudiant)
							.success(function (data){
								
								$scope.etu = data;
						        $location.path('/etudiant/'+$scope.etu.noEtudiant);
						       
							})
							.error(function(){
								console.log("ca ne marche absolument pas");								
							});
						}
						else if (data.role == 'ENS'){
							AuthService.getInfoEnseignant(data.noEnseignant)
							.success(function(data){
								$scope.ens = data;
								$location.path('/');
							});
						}
						else{
							$scope.adm = "Administrateur";
							$location.path('/');
							console.log("here admin");
						}
					})
					.error(function() {
						
					});
				})
					.error(function () {
						// si la connexion a échoué : "secoue" le formulaire
						// de connexion
						// TODO : afficher un message d'erreur de connexion
						var elt = angular.element('.form-container');
						$animate.addClass(elt, 'shake', function () {
							$animate.removeClass(elt, 'shake');
						});
					});
			}

			setUsername = function (username) {
				$scope.username = username;
			}
		}]);
;