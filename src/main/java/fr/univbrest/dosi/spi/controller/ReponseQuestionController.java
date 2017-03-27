package fr.univbrest.dosi.spi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.ReponseQuestion;
import fr.univbrest.dosi.spi.bean.ReponseQuestionPK;
import fr.univbrest.dosi.spi.service.ReponseEvaluationService;
import fr.univbrest.dosi.spi.service.ReponseQuestionService;

@RestController
@RequestMapping("reponseQuestion")
public class ReponseQuestionController {
	
	@Autowired
	private ReponseQuestionService reponseQuestionService;
	
	@Autowired
	private ReponseEvaluationService reponseEvaluationService;
	
	@RequestMapping(value = "/", produces="application/json", method = RequestMethod.GET)
	public final Iterable<ReponseQuestion> reponsesQuestion(){
		return reponseQuestionService.getReponsesQuestion();
	}
	
	@RequestMapping(value = "/",method = RequestMethod.DELETE)
	public final void supprimer(
			@PathVariable(value="reponseQuestionPK") final ReponseQuestionPK reponseQuestionPK
			){
		reponseQuestionService.delete(reponseQuestionPK);
		
	}
	
	@RequestMapping(value="/",method = RequestMethod.POST, consumes ="application/json")
	public final ReponseQuestion ajouter(
			@RequestBody final ReponseQuestion reponseQuestion
			){
		reponseQuestionService.ajouterReponseQuestion(reponseQuestion);
		return reponseQuestion;
	}
	
	@RequestMapping(value="/",method = RequestMethod.PUT, consumes ="application/json")
	public final ReponseQuestion modifier(
			@RequestBody final ReponseQuestion reponseQuestion
			){
		reponseQuestionService.ajouterReponseQuestion(reponseQuestion);
		return reponseQuestion;
	}

}
