'use strict';


// ================================== questionsFactory ==================================
angular.module('app').factory('questionsFactory', ['$http', function ($http) {

    var urlQst = 'http://localhost:8090/question/';
    var urlQlf = 'http://localhost:8090/qualificatif/';
    var questionsFactory = {};

    // ******** renvoi la liste de toutes les questions ********
    questionsFactory.getQuestions = function () {
        return $http.get(urlQst);
    };

    // ******** renvoi une question (idQuestion) ********
    questionsFactory.getQuestion = function (idQuestion) {
        return $http.get(urlQst + "" + idQuestion);
    };

    // ******** renvoi la liste de tous les qualificatifs ********
    questionsFactory.getQualificatifs = function () {
        return $http.get(urlQlf);
    };

    // ******** ajouter une nouvelle question ********
    questionsFactory.addQuestion = function (question) {
        return $http.post(urlQst, question);
    };

    // ******** modifier une question ********
    questionsFactory.editQuestion = function (question) {
        return $http.put(urlQst, question);
    };

    // ******** supprimer une question ********
    questionsFactory.deleteQuestion = function (idQuestion) {
        return $http.delete(urlQst + "" + idQuestion);
    };

    return questionsFactory;

    // ******** liste d'essais ********
    /*var listQuestions = [
        // TODO Constituer la liste des questions ici
        {
            id_question: "1", intitule: "Support de cours", noEnseignant: "1",
            type: "STD", qualificatif: "1"
        },
        {
            id_question: "2", intitule: "Impression générale", noEnseignant: "1",
            type: "STD", qualificatif: "2"
        },
        {
            id_question: "3", intitule: "Rythme", noEnseignant: "1",
            type: "STD", qualificatif: "6"
        }
    ];*/

    /*var listQualificatifs = [
        // TODO Constituer la liste des questions ici
        {
            id_question: "1", maximal: "Faible", minimal: "Fort"
        },
        {
            id_question: "2", maximal: "Facile", minimal: "Difficile"
        },
        {
            id_question: "3", maximal: "Lent", minimal: "Rapide"
        }
    ];*/

    /*var details = [
        // Constituer les délails de question ici
    ];*/
}]);


// ================================== QuestionsController ==================================
angular.module('app').controller('QuestionsController',
    ['$scope', '$filter', '$location', 'questionsFactory', '$modal',
        function ($scope, $filter, $location, questionsFactory, $modal) {

            var init;

            // ******** liste des questions a partir du service ********
            getQuestions(function () {
                // ******** Les différents filtres ********
                $scope.numPerPageOpt = [5, 10, 30, 50, 100];
                $scope.numPerPage = $scope.numPerPageOpt[2];
                $scope.currentPage = 1;
                $scope.currentPageQuestion = $filter('orderBy')($scope.questions, "intitule");
                $scope.filteredQuestion = $scope.questions;

                $scope.select = function (page) {
                    var end, start;
                    start = (page - 1) * $scope.numPerPage;
                    end = start + $scope.numPerPage;
                    if (isNaN(end)) $scope.currentPageQuestion = $scope.filteredQuestion;
                    else return $scope.currentPageQuestion = $scope.filteredQuestion.slice(start, end);
                };
                $scope.onFilterChange = function () {
                    $scope.select(1);
                    $scope.currentPage = 1;
                    return $scope.row = '';
                };
                $scope.onNumPerPageChange = function () {
                    $scope.select(1);
                    return $scope.currentPage = 1;
                };
                $scope.onOrderChange = function () {
                    $scope.select(1);
                    return $scope.currentPage = 1;
                };
                $scope.search = function () {
                    $scope.filteredQuestion = $filter('filter')($scope.questions, $scope.searchKeywords);
                    return $scope.onFilterChange();
                };
                $scope.order = function (rowName) {
                    if ($scope.row === rowName) {
                        return;
                    }
                    $scope.row = rowName;
                    console.log($scope.row);
                    $scope.filteredQuestion = $filter('orderBy')($scope.questions, rowName);
                    return $scope.onOrderChange();
                };
            });


            // ******** Crée la page permettant d'ajouter une question ********
            $scope.ajouterQuestion = function () {
                $location.path('/admin/question/nouveau');
            }

            // ******** Crée la page permettant de modifier une question ********
            $scope.modifierQuestion = function (idQuestion) {
                $location.path('/admin/question/' + idQuestion);
            }

            // ******** supprimer une question (Modal)********
            $scope.supprimerQuestion = function (idQuestion) {
                $modal.open({
                    templateUrl: 'supprimerQuestion',
                    backdrop: true,
                    windowClass: 'modal',
                    controller: function ($scope, $modalInstance, $log, questionsFactory) {
                        $scope.confirmer = function () {
                            questionsFactory.deleteQuestion(idQuestion);
                            $modalInstance.dismiss('cancel');
                            location.reload('/admin/questions');
                        }
                        $scope.annuler = function () {
                            $modalInstance.dismiss('cancel');
                        };
                    }
                });
            }

            // ******** fonction récuperation liste des questions a partir du service ********
            function getQuestions(initialisationCb) {
                questionsFactory.getQuestions()
                    .then(function (response) {
                        $scope.questions = response.data;
                        $scope.error = false;
                        initialisationCb();
                    }, function (error) {
                        $scope.success = false;
                        $scope.error = true;
                        $scope.status = 'Erreur lors de la récupération de la liste des questions: ' + error.message;
                    });
            }
        }]
);


// ================================== QstDetailsController ==================================
angular.module('app').controller('QstDetailsController',
    ['$scope', '$routeParams', '$location', 'questionsFactory',
        function ($scope, $routeParams, $location, questionsFactory) {

            $scope.edit = false;
            getQualificatifs();


            // ******** si creation d'une nouvelle question ********
            if ($routeParams.id == "nouveau") {
                $scope.question = {
                    type: "QUS"
                };
                $scope.edit = true;
            }
            else {//if ($routeParams.id == "modification") {
                $scope.question = {
                    type: "QUS"
                };
                var idQuestion = $routeParams.id;
                getQuestion(idQuestion);
                $scope.edit = true;
            }

            // ******** valide le formulaire d'ajout/modif d'une question ********
            $scope.submit = function () {
                if ($scope.question)
                if ($routeParams.id == "nouveau") {
                    questionsFactory.addQuestion($scope.question);
                }
                else { //if ($routeParams.id == "modification"){
                    questionsFactory.addQuestion($scope.question);
                }
                $scope.edit = false;
                 window.location.href ="http://localhost:8090/index.html#/admin/questions";
            }

            // ******** annule l'édition ********
            $scope.cancel = function () {
                $location.path('/admin/questions');
            }

            // ******** fonction récuperation liste des qualificatifs a partir du service ********
            function getQualificatifs() {
                questionsFactory.getQualificatifs()
                    .then(function (response) {
                        $scope.qualificatifs = response.data;
                        $scope.error = false;
                    }, function (error) {
                        $scope.success = false;
                        $scope.error = true;
                        $scope.status = 'Erreur lors de la récupération de la liste des questions: ' + error.message;
                    });
            }

            // ******** fonction récuperation liste des questions a partir du service ********
            function getQuestion(idQuestion) {
                questionsFactory.getQuestion(idQuestion)
                    .then(function (response) {
                        $scope.question = response.data;
                        $scope.error = false;
                    }, function (error) {
                        $scope.success = false;
                        $scope.error = true;
                        $scope.status = 'Erreur lors de la récupération de la liste des questions: ' + error.message;
                    });
            }
        }]
);
