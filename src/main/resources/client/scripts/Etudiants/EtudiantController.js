angular.module('app').controller('EtudiantController', ['$scope', '$routeParams', 'dataFactory','$location',
    function ($scope, $routeParams, dataFactory, $location) {
        $scope.status;
        $scope.etudiants;

        $scope.etudiantPromotion ={};


        $scope.codeFormation = $routeParams.codeFormation;
        $scope.anneeUniversitaire = $routeParams.anneeUniversitaire;
        console.log($scope.codeFormation);
        console.log($scope.anneeUniversitaire);
        
        $scope.etudiant = {
        		sexe : "M"
        };
        // $scope.noEtudiant = $routeParams.noEtudiant;
        // $scope.error = false;
        // $scope.success = false;

      //  getEtudiant();


        function getEtudiants() {
            dataFactory.getEtudiants()
                .then(function (response) {
                    $scope.etudiants = response.data;
                    $scope.error = false;
                }, function (error) {
                    $scope.success = false;
                    $scope.error = true;
                    $scope.status = 'Erreur lors de la récupération de la liste des etudiants: ' + error.message;
                });
        }



        function getEtudiant() {
            dataFactory.getEtudiant($scope.noEtudiant)
                .then(function (response) {
                    $scope.etudiant = response.data;
                    $scope.error = false;
                    console.log($scope.etudiant);
                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    $scope.status = 'Erreur lors de la récupération de la liste des etudiants: ' + error.message;
                });
        }

        var getPromotion = function (){
            dataFactory.getPromotion($scope.codeFormation, $scope.anneeUniversitaire)
                .then(function (response) {
                    $scope.etudiantPromotion.promotion = response.data;
                    $scope.error = false;
                    InsererFormationRequette();
                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    $scope.status = 'Erreur lors de la récupération de la liste des etudiants: ' + error.message;
                });
        }

        function InsererFormationRequette(){
            $scope.etudiantPromotion.etudiant = $scope.etudiant;
            console.log("---");
            console.log($scope.etudiantPromotion);
            console.log("---");
            dataFactory.insertEtudiant($scope.etudiantPromotion)
                .then(function (response) {
                    $scope.status = 'Insertion étudiant effectuée!';

                    $scope.error = false;
                    $scope.success = true;

                }, function (error) {
                    $scope.success = false;
                    $scope.error = true;
                    $scope.status = 'Erreur lors de l\'insertion de l\'étudiant: ' + error.message;
                });
        }
        $scope.insertEtudiant = function () {

            getPromotion();
            $location.path('/admin/formationsPromo');
            
        };

        $scope.updateEtudiant = function () {
            dataFactory.updateEtudiant($scope.etudiant)
                .then(function (response) {

                    $scope.status = 'Mise à jour de l\'étudiant effectuée!';
                    $scope.error = false;
                    $scope.success = true;
                    getEtudiants();
                   // $location.path("/formation/"+$scope.formation.codeFormation);
                    // console.log($location.path);
                }, function (error) {
                    $scope.success = false;
                    $scope.error = true;
                    $scope.status = 'Erreur lors de la mise à jour de l\'étudiant: ' + error.message;
                });
        };

        $scope.ajoutEtudiant = function(){
            $location.path('/admin/etudiant/new');
        }


        $scope.closeAlert = function () {
            $scope.error = false;
            $scope.success = false;
        };

      /*  if($scope.noEtudiant)
            $scope.etudiant = $scope.getEtudiant($scope.noEtudiant);
        else
            getEtudiants();*/
    }
]);

angular.module('app')
    .factory('dataFactory', ['$http', function ($http) {

        var urlBase = 'http://localhost:8090/etudiant/';
        var dataFactory = {};

        dataFactory.getEtudiants = function () {
            return $http.get(urlBase);
        };

        dataFactory.getEtudiant = function (noEtudiant) {
            return $http.get(urlBase + noEtudiant);
        };

        dataFactory.getPromotion = function (codeFormation,anneeUniversitaire) {
            return $http.get('http://localhost:8090/promotion/' + codeFormation +'/'+anneeUniversitaire);
        };

        dataFactory.insertEtudiant = function (etudiantPromotion) {
            return $http.post(urlBase, etudiantPromotion);
        };

        dataFactory.updateEtudiant = function (noEtudiant) {
            return $http.put(urlBase, noEtudiant)
        };

        dataFactory.deleteEtudiant = function (noEtudiant) {
            return $http.delete(urlBase + noEtudiant);
        };

        return dataFactory;
    }]);



