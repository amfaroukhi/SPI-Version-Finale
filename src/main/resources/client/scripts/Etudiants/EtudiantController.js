angular.module('app').controller('EtudiantController', ['$scope', '$routeParams', 'dataFactory','$location',
    function ($scope, $routeParams, dataFactory, $location) {
        $scope.status;
        $scope.etudiants;
        $scope.DomainUniv;
        $scope.etudiantPromotion ={};
        $scope.DomainPays;

        $scope.noEtudiant = $routeParams.noEtudiant;
        $scope.codeFormation = $routeParams.codeFormation;
        $scope.anneeUniversitaire = $routeParams.anneeUniversitaire;

        $scope.formations=[];
        $scope.promotions=[];

        $scope.etudiant = {
        		sexe : "M"
        };

        $scope.sortType     = 'nom'; // set the default sort type
        $scope.sortReverse  = false;  // set the default sort order
        $scope.search   = '';

        $scope.flag = false;
        // pour le tri des promotions

        $scope.sortType1     = 'promotionPK.anneeUniversitaire'; // set the default sort type
        $scope.sortReverse1  = false;  // set the default sort order

// pour le tri des Formations

        $scope.sortType2    = 'codeFormation'; // set the default sort type
        $scope.sortReverse1  = false;  // set the default sort order


        getEtudiant();
        getDomainUniv();
        getDomainPays();
        listFormations();
        // affichage des formation et promotions après l'ajout ou la modification d'un étudiant
        if($scope.codeFormation){
            showPromotionsAfterAdd($scope.codeFormation);
            showEtudiantsAfterAdd($scope.codeFormation,$scope.anneeUniversitaire);
        }
        if($scope.codeFormation && $scope.anneeUniversitaire){

            showEtudiantsAfterAdd($scope.codeFormation,$scope.anneeUniversitaire);

        }



// pour rediriger l'edit du MAJ etudiant
        $scope.edit = function (noEtudiant,codeFormation,anneeUniversitaire){

            $location.path("/admin/etudiant/edit/"+ noEtudiant+"/"+codeFormation+"/"+anneeUniversitaire);

        }




// ce qui concerne le listing

        //pour afficher les listes formation puis etudiants
        $scope.afficherPromotion = false;
        $scope.afficherEtudiant = false;

        function listFormations() {
            dataFactory.listFormations()
                .then(function (response) {
                    $scope.formations = response.data;
                    $scope.error = false;
                    console.log($scope.formations);
                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    $scope.status = 'Erreur lors de la récupération de la liste des formations: ' + error.message;
                });
        }

        $scope.showPromotions = function(codeF) {
            dataFactory.listPromotions(codeF)
                .then(function (response) {
                    $scope.afficherPromotion = true;
                    $scope.promotions=response.data;
                    console.log(response.data);
                    $scope.error = false;
                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    $scope.status = 'Erreur lors de la récupération de la liste des formations: ' + error.message;
                });
        }

        function showPromotionsAfterAdd(codeF) {
            dataFactory.listPromotions(codeF)
                .then(function (response) {
                    $scope.afficherPromotion = true;
                    $scope.promotions=response.data;
                    console.log(response.data);
                    $scope.error = false;
                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    $scope.status = 'Erreur lors de la récupération de la liste des formations: ' + error.message;
                });
        }
        $scope.showEtudiants = function(code,anneeUiversitaire) {
            dataFactory.listEtudiants(code,anneeUiversitaire)
                .then(function (response) {
                    $scope.afficherEtudiant = true;
                    $scope.etudiants=response.data;
                    $scope.code = code;
                    $scope.annee = anneeUiversitaire;
                    $scope.error = false;
                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    $scope.status = 'Erreur lors de la récupération de la liste des formations: ' + error.message;
                });
        }

        function showEtudiantsAfterAdd(code,anneeUiversitaire) {
            dataFactory.listEtudiants(code,anneeUiversitaire)
                .then(function (response) {
                    $scope.afficherEtudiant = true;
                    $scope.etudiants=response.data;
                    $scope.code = code;
                    $scope.annee = anneeUiversitaire;
                    $scope.error = false;
                    $scope.showEtudiants(code,anneeUiversitaire);

                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    $scope.status = 'Erreur lors de la récupération de la liste des formations: ' + error.message;
                });
        }

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

/***********************************************************************************/


//CE qui concerne l'étudiant
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

        function getDomainUniv() {
            dataFactory.getDomainUniv()
                .then(function (response) {
                    $scope.DomainUniv = response.data;
                    $scope.error = false;
                    console.log($scope.DomainUniv);
                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    $scope.status = 'Erreur lors de la récupération des domaines ' + error.message;
                });
        }

        function getDomainPays() {
            dataFactory.getDomainPays()
                .then(function (response) {
                    $scope.DomainPays = response.data;
                    $scope.error = false;
                    console.log($scope.DomainPays);
                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    $scope.status = 'Erreur lors de la récupération des domaines ' + error.message;
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
            $location.path('/admin/formationPromo/'+$scope.codeFormation+'/'+$scope.anneeUniversitaire);
            console.log($scope.afficherPromotion);

        };

        $scope.updateEtudiant = function () {

            getEtudiantMaj();
            $location.path('/admin/formationPromo/'+$scope.codeFormation+'/'+$scope.anneeUniversitaire);

        };

        var getEtudiantMaj = function (){
            dataFactory.getPromotion($scope.codeFormation, $scope.anneeUniversitaire)
                .then(function (response) {
                    $scope.etudiantPromotion.promotion = response.data;
                    $scope.error = false;
                    InsererEtudiantRequetteMaj();
                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    $scope.status = 'Erreur lors de la récupération de la liste des etudiants: ' + error.message;
                });
        }

        function InsererEtudiantRequetteMaj(){
            $scope.etudiantPromotion.etudiant = $scope.etudiant;
            console.log("---");
            console.log($scope.etudiantPromotion);
            console.log("---");
            dataFactory.updateEtudiant($scope.etudiantPromotion)
                .then(function (response) {
                    $scope.status = 'MAJ étudiant effectuée!';

                    $scope.error = false;
                    $scope.success = true;

                }, function (error) {
                    $scope.success = false;
                    $scope.error = true;
                    $scope.status = 'Erreur lors de la MAJ de l\'étudiant: ' + error.message;
                });
        }

        $scope.remove = function (noEtudiant, index){
            var supp = confirm("voulez-vous bien supprimer cet étudiant ?");
            if(supp == true){
                dataFactory.deleteEtudiant(noEtudiant)
                    .then(function (response) {
                        $scope.showEtudiants($scope.code,$scope.annee);
                        $scope.status = 'Suppression effectuée!';
                        $scope.error = false;
                        $scope.success = true;
                        // getFormations();
                    }, function (error) {
                        $scope.success = false;
                        $scope.error = true;
                        $scope.status = 'Erreur lors de la suppression' + error.message;
                    });
            }
        }

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

        dataFactory.getDomainUniv = function () {
            return $http.get("http://localhost:8090/domain/getBydomain/UNIVERSITE");
        };

        dataFactory.getDomainPays = function () {
            return $http.get("http://localhost:8090/domain/getBydomain/PAYS");
        };

        dataFactory.listFormations = function () {
            return   $http.get("http://localhost:8090/formations");
        };

        dataFactory.listPromotions = function (codeFormation) {
          return   $http.get("http://localhost:8090/promotion/"+codeFormation);
        };

        dataFactory.listEtudiants = function (code,anneeUniversitaire) {
            return   $http.get("http://localhost:8090/etudiant/"+code+"/"+anneeUniversitaire);
        };

        return dataFactory;
    }]);



