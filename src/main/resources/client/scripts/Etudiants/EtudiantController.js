angular.module('app').controller('EtudiantController', ['$scope', '$rootScope', '$routeParams', 'dataFactory', '$location', '$modal',
    function ($scope, $rootScope, $routeParams, dataFactory, $location, $modal) {
        $scope.status;
        $scope.etudiants;
        $scope.DomainUniv;
        $scope.etudiantPromotion = {};
        $scope.DomainPays;
        // $scope.etudiant={};
        $scope.noEtudiant = $routeParams.noEtudiant;
        $scope.codeFormation = $routeParams.codeFormation;
        $scope.anneeUniversitaire = $routeParams.anneeUniversitaire;
        
        
        
        $scope.formations = [];
        $scope.promotions = [];

        $scope.sortType = 'nom'; // set the default sort type
        $scope.sortReverse = false;  // set the default sort order
        $scope.search = '';

        $scope.flag = false;
        // pour le tri des promotions

        $scope.sortType1 = 'promotionPK.anneeUniversitaire'; // set the default sort type
        $scope.sortReverse1 = false;  // set the default sort order

        // pour le tri des Formations

        $scope.sortType2 = 'codeFormation'; // set the default sort type
        $scope.sortReverse1 = false;  // set the default sort order

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

        getEtudiant();
        getDomainUniv();
        getDomainPays();
        listFormations();
        getDomainOuiNon();

        $scope.designOuiNon = function (abv) {
            var meaning = "yes";
            angular.forEach($scope.DomainOuiNon, function (value, key) {
                if (value.rvAbbreviation == abv) {
                    meaning = value.rvMeaning;
                }
            });
            return meaning;
        }

        $scope.etudiant = {
            sexe: "M"
        }

        $scope.open1 = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();
            return $scope.opened1 = true;
        };
        // affichage des formation et promotions après l'ajout ou la modification d'un étudiant
        if ($scope.codeFormation) {
            showPromotionsAfterAdd($scope.codeFormation);
            showEtudiantsAfterAdd($scope.codeFormation, $scope.anneeUniversitaire);
        }
        if ($scope.codeFormation && $scope.anneeUniversitaire) {

            showEtudiantsAfterAdd($scope.codeFormation, $scope.anneeUniversitaire);

        }




        // pour rediriger l'edit du MAJ etudiant
        $scope.edit = function (noEtudiant, codeFormation, anneeUniversitaire) {

            $location.path("/admin/etudiant/edit/" + noEtudiant + "/" + codeFormation + "/" + anneeUniversitaire);

        }




        // ce qui concerne le listing

        //pour afficher les listes formation puis etudiants
        $scope.afficherPromotion = false;
        $scope.afficherEtudiant = false;

        function listFormations() {
            dataFactory.listFormations()
                .then(function (response) {
                    $scope.etudiants = [];
                    $scope.afficherPromotion = false;
                    $scope.afficherEtudiant = false;
                    $scope.formations = response.data;
                    $scope.error = false;
                    console.log($scope.formations);
                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    // $scope.status = 'Erreur lors de la récupération de la liste des formations: ' + error.message;
                });
        }

        $scope.showPromotions = function (codeF) {
            dataFactory.listPromotions(codeF)
                .then(function (response) {
                    $scope.etudiants = [];
                    $scope.annee = '';
                    $scope.code = codeF;
                    $scope.afficherEtudiant = false;
                    $scope.afficherPromotion = true;
                    $scope.promotions = response.data;
                    console.log(response.data);
                    $scope.error = false;
                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    // $scope.status = 'Erreur lors de la récupération de la liste des formations: ' + error.message;
                });
        }

        function showPromotionsAfterAdd(codeF) {
            dataFactory.listPromotions(codeF)
                .then(function (response) {
                    $scope.afficherPromotion = true;
                    $scope.promotions = response.data;
                    $scope.annee = '';
                    $scope.code = codeF;
                    console.log(response.data);
                    $scope.error = false;
                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    // $scope.status = 'Erreur lors de la récupération de la liste des formations: ' + error.message;
                });
        }
        $scope.showEtudiants = function (code, anneeUiversitaire) {
            dataFactory.listEtudiants(code, anneeUiversitaire)
                .then(function (response) {
                    $scope.afficherEtudiant = true;
                    $scope.etudiants = response.data;
                    $scope.code = code;
                    $scope.annee = anneeUiversitaire;
                    nbrEtudiants($scope.code, $scope.annee);
                    $scope.error = false;
                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    // $scope.status = 'Erreur lors de la récupération de la liste des formations: ' + error.message;
                });
        }
        showEtudiants = function (code, anneeUiversitaire) {
            $scope.showEtudiants(code, anneeUiversitaire)
        }

        function showEtudiantsAfterAdd(code, anneeUiversitaire) {
            dataFactory.listEtudiants(code, anneeUiversitaire)
                .then(function (response) {
                    $scope.afficherEtudiant = true;
                    $scope.etudiants = response.data;
                    $scope.code = code;
                    $scope.annee = anneeUiversitaire;
                    $scope.error = false;
                    nbrEtudiants($scope.code, $scope.annee);
                    $scope.showEtudiants(code, anneeUiversitaire);

                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    // $scope.status = 'Erreur lors de la récupération de la liste des formations: ' + error.message;
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
                    // $scope.status = 'Erreur lors de la récupération de la liste des etudiants: ' + error.message;
                });
        }

        function nbrEtudiants(code, annee)
             {
            dataFactory.nbrEtudiantsInPromotion(code,annee)
                .then(function (response) {
                    $scope.nbrEtud = response.data;
                    $scope.error = false;
                }, function (error) {
                    $scope.success = false;
                    $scope.error = true;
                    // $scope.status = 'Erreur lors de la récupération du nombre des etudiants: ' + error.message;
                });

        }

        /***********************************************************************************/


        //CE qui concerne l'étudiant
        function getEtudiant() {
            dataFactory.getEtudiant($scope.noEtudiant)
                .then(function (response) {
                    $scope.etudiant = response.data;
                    if ($scope.etudiant == "") {
                        $scope.etudiant = {
                            sexe: "M"
                        };
                    }
                    $scope.error = false;
                    console.log(response.data);
                    console.log($scope.etudiant);
                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    // $scope.status = 'Erreur lors de la récupération de la liste des etudiants: ' + error.message;
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
                    // $scope.status = 'Erreur lors de la récupération des domaines ' + error.message;
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
                    // $scope.status = 'Erreur lors de la récupération des domaines ' + error.message;
                });
        }

         function getDomainOuiNon() {
            dataFactory.getDomainOuiNon()
                .then(function (response) {
                    $scope.DomainOuiNon = response.data;
                    $scope.error = false;
                }, function (error) {
                    $scope.success = false;
                    $scope.error = true;
                    // $scope.status = 'Erreur lors de la récupération des domaines ' + error.message;
                });
        }

        var getPromotion = function (callback) {
            dataFactory.getPromotion($scope.codeFormation, $scope.anneeUniversitaire)
                .then(function (response) {
                    $scope.etudiantPromotion.promotion = response.data;
                    $scope.error = false;
                    InsererFormationRequette();
                    callback();
                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    // $scope.status = 'Erreur lors de la récupération de la liste des etudiants: ' + error.message;
                });
        }

        function InsererFormationRequette() {
            $scope.etudiantPromotion.etudiant = $scope.etudiant;
            console.log("---");
            console.log($scope.etudiantPromotion);
            console.log("---");
            dataFactory.insertEtudiant($scope.etudiantPromotion)
                .then(function (response) {
                    $rootScope.status = "L'étudiant " + $scope.etudiant.nom + " " + $scope.etudiant.prenom + " a été ajouté avec succès !";
                    hideStatus();
                    $scope.error = false;
                    $scope.success = true;

                }, function (error) {
                    $scope.success = false;
                    $scope.error = true;
                    // $scope.status = 'Erreur lors de l\'insertion de l\'étudiant: ' + error.message;
                });
        }

        $scope.insertEtudiant = function () {

            getPromotion(function () {
                $location.path('/admin/formationsPromo/' + $scope.codeFormation + '/' + $scope.anneeUniversitaire);
            });

        };

        $scope.updateEtudiant = function () {

            getEtudiantMaj();
            $location.path('/admin/formationsPromo/' + $scope.codeFormation + '/' + $scope.anneeUniversitaire);

        };

        var getEtudiantMaj = function () {
            dataFactory.getPromotion($scope.codeFormation, $scope.anneeUniversitaire)
                .then(function (response) {
                    $scope.etudiantPromotion.promotion = response.data;
                    $scope.error = false;
                    InsererEtudiantRequetteMaj();
                }, function (error) {
                    console.log("err");
                    $scope.success = false;
                    $scope.error = true;
                    // $scope.status = 'Erreur lors de la récupération de la liste des etudiants: ' + error.message;
                });
        }

        function InsererEtudiantRequetteMaj() {
            $scope.etudiantPromotion.etudiant = $scope.etudiant;
            console.log("---");
            console.log($scope.etudiantPromotion);
            console.log("---");
            dataFactory.updateEtudiant($scope.etudiantPromotion)
                .then(function (response) {
                    $rootScope.status = "L'étudiant " + $scope.etudiant.nom + " " + $scope.etudiant.prenom + " a été mis à jour avec succès !";
                    hideStatus();
                    $scope.error = false;
                    $scope.success = true;

                }, function (error) {
                    $scope.success = false;
                    $scope.error = true;
                    // $scope.status = 'Erreur lors de la MAJ de l\'étudiant: ' + error.message;
                });
        }

        $scope.remove = function (noEtudiant,nom,prenom) {
            $rootScope.code = $scope.code;
            $rootScope.annee = $scope.annee;
            dataFactory.getEtudiant(noEtudiant)
                .then(function (response) {
                    $rootScope.etudiant = response.data;
                });

            $modal.open({
                templateUrl: 'supprimerEtudiant',
                backdrop: true,
                windowClass: 'modal',
                controller: function ($scope, $modalInstance, $log, questionsFactory) {
                    $scope.etudToSupp = nom + " "+ prenom;

                    $scope.confirmer = function () {
                        dataFactory.deleteEtudiant(noEtudiant)
                            .success(function (response) {
                                showEtudiants($scope.code, $scope.annee);
                                $rootScope.status = "L'étudiant " + $scope.etudiant.nom + " " + $scope.etudiant.prenom + " a été supprimé avec succès !";
                                hideStatus();
                                $scope.error = false;
                                $scope.success = true;
                                // getFormations();
                            })
                            .error(function () {
                                // alert("Impossible de supprimer cet étudiant");
                                supprimerEtudiantError($scope.etudToSupp);
                                $scope.success = false;
                                $scope.error = true;
                                // $scope.status = 'Erreur lors de la suppression' ;
                            });
                        $modalInstance.dismiss('cancel');
                    }
                    $scope.annuler = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }
            });
        }

        function supprimerEtudiantError(etudiant) {
            $rootScope.currentPageQuestion = $scope.currentPageQuestion;
            $modal.open({
                templateUrl: 'supprimerEtudiantError',
                backdrop: true,
                windowClass: 'modal',
                controller: function ($scope, $modalInstance, $log) {
                    $scope.etudToSupp = etudiant;
                    $scope.annuler = function () {
                        $modalInstance.dismiss('cancel');
                    };
                }
            });
        };


        $scope.ajoutEtudiant = function () {
            $scope.$watch($scope.etudiant, function () {
                $location.path("/admin/etudiant/new/" + $scope.code + "/" + $scope.annee);
            });
        }

        $scope.cancel = function () {
            $scope.etudiant = {
                sexe: "M"
            }
            $location.path('/admin/formationsPromo/' + $scope.codeFormation + '/' + $scope.anneeUniversitaire);
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

        dataFactory.getPromotion = function (codeFormation, anneeUniversitaire) {
            return $http.get('http://localhost:8090/promotion/' + codeFormation + '/' + anneeUniversitaire);
        };

        dataFactory.insertEtudiant = function (etudiantPromotion) {
            return $http.post(urlBase, etudiantPromotion);
        };

        dataFactory.updateEtudiant = function (noEtudiant) {
            return $http.put(urlBase, noEtudiant)
        };

        dataFactory.deleteEtudiant = function (noEtudiant) {
            return $http.delete(urlBase + "" + noEtudiant);
        };

        dataFactory.getDomainUniv = function () {
            return $http.get("http://localhost:8090/domain/getBydomain/UNIVERSITE");
        };

        dataFactory.getDomainPays = function () {
            return $http.get("http://localhost:8090/domain/getBydomain/PAYS");
        };

        dataFactory.getDomainOuiNon = function () {
            return $http.get("http://localhost:8090/domain/getBydomain/OUI_NON");
        };

        dataFactory.listFormations = function () {
            return $http.get("http://localhost:8090/formation/");
        };

        dataFactory.listPromotions = function (codeFormation) {
            return $http.get("http://localhost:8090/promotion/" + codeFormation);
        };

        dataFactory.listEtudiants = function (code, anneeUniversitaire) {
            return $http.get("http://localhost:8090/etudiant/" + code + "/" + anneeUniversitaire);
        };

        dataFactory.nbrEtudiantsInPromotion = function (code, anneeUniversitaire) {
            return $http.get("http://localhost:8090/etudiant/countEtudiants/" + code + "/" + anneeUniversitaire);
        };

        return dataFactory;
    }]);



