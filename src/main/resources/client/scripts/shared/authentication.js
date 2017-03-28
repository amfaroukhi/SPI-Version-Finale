(function() {
  'use strict';
  var app = angular.module('app.authentication', []);

  app.service('AuthentificationService', ['$http', function ($http) {
	   this.etudiantCourant = function(noEtudiant,callback){
		   var url = "http://localhost:8090/etudiant/"+noEtudiant;
		   $http.get(url).then(function(response){
			   callback(response.data);
		   });
	   };
	  }]);
  /**
   Controleur pour la page d'authentification.
   */
  /*
  app.controller('AuthenticationController',
    ['$scope', '$location', '$animate','$routeParams','AuthentificationService',
    function($scope, $location, $animate, $routeParams, AuthentificationService){
    	
      var noEtudiant = $routeParams.noEtudiant;
      
      AuthentificationService.etudiantCourant(noEtudiant,function(data){
    	  $scope.etudiant = data;
    	  console.log(data)
	});
      
      $scope.login = {};
      // Nom utilisateur et image (affichés dans le header)
      $scope.username = auth.username();
      $scope.userimg = "";    
      console.log(auth.username());

      // Réception de l'événement de login (voir le service 'auth')
      $scope.$on('login-success', function(){
        $scope.username = auth.username();
        $scope.userimg = auth.userimg();
      });
      
      // Executé lors du click sur le bouton de login
      this.submit = function(){
        if(auth.login($scope.login.email, $scope.login.password)){          
          // Redirection vers la page d'accueil
          // TODO : redirection vers la page à laquelle on souhaitait 
          // accéder avant ce login.
        $scope.username = auth.username();
          $location.path('/');
        } else {
          // si la connexion a échoué : "secoue" le formulaire de connexion
          // TODO : afficher un message d'erreur de connexion
          var elt = angular.element('.form-container');
          $animate.addClass(elt, 'shake', function(){
            $animate.removeClass(elt, 'shake');
          });
        }
      }
    }]
  );

*/
}).call(this);

