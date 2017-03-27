package fr.univbrest.dosi.spi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.ReponseEvaluation;
import fr.univbrest.dosi.spi.service.ReponseEvaluationService;

@RestController
@RequestMapping("reponseEvaluation")
public class ReponseEvaluationController {
	
	@Autowired
	private ReponseEvaluationService reponseEvaluationService;
	
	//Liste des réponses évaluation par id évaluation
	
	@RequestMapping(value = "/{idEvaluation}" ,produces="application/json",method = RequestMethod.GET)
	public final Iterable<ReponseEvaluation> reponsesByIdEvaluation(
			@PathVariable(value="idEvaluation") final Long idEvaluation
			){
		return reponseEvaluationService.getReponseEvaluationByIdEvaluation(idEvaluation);
	}
	
	@RequestMapping(value = "/etudiant/{noEtudiant}", produces="application/json",method = RequestMethod.GET)
	public final Iterable<ReponseEvaluation> reponsesByNoEtudiant(
			@PathVariable(value="noEtudiant") final String noEtudiant
			){
		return reponseEvaluationService.getReponseEvaluationByNoEtudiant(noEtudiant);
										
	}
	
	@RequestMapping(value = "/", produces="application/json",method = RequestMethod.GET)
	public final Iterable<ReponseEvaluation> reponses(){
		return reponseEvaluationService.getReponses();
										
	}
	
	@RequestMapping(value="supp/{idReponseEvaluation}",method= RequestMethod.DELETE)
	public final void supprimer(
			@PathVariable(value="idReponseEvaluation") final Long idReponseEvaluation
			){
		reponseEvaluationService.delete(idReponseEvaluation);
	}
	
	@RequestMapping(value="/",method = RequestMethod.POST, consumes = "application/json")
	public final ReponseEvaluation ajouter(
			@RequestBody final ReponseEvaluation reponseEvaluation
			){
		
		reponseEvaluationService.ajouterReponseEvaluation(reponseEvaluation);
		return reponseEvaluation;
	}
	
	@RequestMapping(value="/", method = RequestMethod.PUT, consumes ="application/json")
	public final ReponseEvaluation modifier(
			@RequestBody final ReponseEvaluation reponseEvaluation
			){
		reponseEvaluationService.ajouterReponseEvaluation(reponseEvaluation);
		return reponseEvaluation; 
	}
	

	
}


