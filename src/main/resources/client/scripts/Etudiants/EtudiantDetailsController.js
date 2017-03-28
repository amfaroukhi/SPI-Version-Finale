'use strict';
angular.module('app')
  .service('EtudiantDetailsSvc', ['$http', function ($http) {
	  
   this.etudiant = function(noEtudiant,callback){
	   var url = "http://localhost:8090/etudiant/"+noEtudiant;
	   $http.get(url).then(function(response){
		   callback(response.data);
	   });
   };

  }]);
  
angular.module('app')
  .controller('EtudiantDetailsCtrl', [
    '$scope', 'EtudiantDetailsSvc', '$http', '$location', '$routeParams', function($scope, $location, EtudiantDetailsSvc, $routeParams, $http) {
    	EtudiantDetailsSvc.getInfoEtudiant($routeParams.noEtudiant).success(function (data){
    		$scope.etudiant = data;
    	});
    	
    	EtudiantDetailsSvc.etudiant(codeFormation,function(data){
			$scope.promotions=data;
	});
    	
    }
  ]);
