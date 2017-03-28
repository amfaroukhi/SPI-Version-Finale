package fr.univbrest.dosi.spi.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Etudiant;
import fr.univbrest.dosi.spi.bean.Promotion;
import fr.univbrest.dosi.spi.bean.PromotionPK;
import fr.univbrest.dosi.spi.bean.Qualificatif;
import fr.univbrest.dosi.spi.bean.Question;
import fr.univbrest.dosi.spi.bean.QuestionEvaluation;
import fr.univbrest.dosi.spi.bean.RubriqueEvaluation;
import fr.univbrest.dosi.spi.service.QualificatifService;
import fr.univbrest.dosi.spi.service.QuestionEvaluationService;
import fr.univbrest.dosi.spi.service.QuestionService;
import fr.univbrest.dosi.spi.service.RubriqueEvaluationService;

@RestController
@RequestMapping("questionEvaluation")
public class QuestionEvaluationController {

	@Autowired
	QuestionEvaluationService questionEvaluationServ;
	
	@Autowired
	QuestionService questionServ;
	
	@Autowired
	QualificatifService qualificatifServ;
	
	@Autowired
	RubriqueEvaluationService rubriqueEvaluationSer;
	
	@RequestMapping(value="/" , method = RequestMethod.POST, headers= "Accept=application/json")
	public QuestionEvaluation addQuestionEvaluation(@RequestBody final QuestionEvaluation questionEvaluation){

		return questionEvaluationServ.addQuestionEvaluation(questionEvaluation);

	}
	
	@RequestMapping(value = "/questionsEvaByRubrique/{idRubriqueEvaluation}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Iterable<QuestionEvaluation> questionsEvaByRubrique(@PathVariable(value = "idRubriqueEvaluation") 
	final long idRubriqueEvaluation) {
		
		return questionEvaluationServ.findByRubriqueEvaluation(idRubriqueEvaluation);
	}
	
	@RequestMapping(value="/" , method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String updateQuestionEvaluation(@RequestBody final QuestionEvaluation questionEvaluation){
		questionEvaluationServ.updateQuestionEvaluation(questionEvaluation);
		return "succes";
	}
	
	@RequestMapping(value="/{idQuestionEvaluation}" , method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public void deleteQuestionEvaluation(@PathVariable("idQuestionEvaluation")Long idQuestionEvaluation){
		questionEvaluationServ.deleteQuestionEvaluation(idQuestionEvaluation);
	}
	
	@RequestMapping(value="/", headers = "Accept=application/json")
	public Iterable<QuestionEvaluation> listQuestionsEvaluation(){
		return questionEvaluationServ.listQuestionsEvaluation();
	}

	@RequestMapping(value="/questionEval/{idQuestionEvaluation}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public QuestionEvaluation getQeustionEval(@PathVariable("idQuestionEvaluation")Long idQuestionEvaluation){
		return questionEvaluationServ.getQuestionEvaluation(idQuestionEvaluation);
	}
	
}
