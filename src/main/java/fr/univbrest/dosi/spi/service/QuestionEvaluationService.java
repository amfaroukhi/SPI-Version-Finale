package fr.univbrest.dosi.spi.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.Etudiant;
import fr.univbrest.dosi.spi.bean.Promotion;
import fr.univbrest.dosi.spi.bean.QuestionEvaluation;
import fr.univbrest.dosi.spi.bean.RubriqueEvaluation;
import fr.univbrest.dosi.spi.dao.QuestionEvaluationRepository;

@Service
public class QuestionEvaluationService {

	@Autowired
	QuestionEvaluationRepository questionEvaluationRepo;
	
	
	@Autowired
	RubriqueEvaluationService rubriqueEvaluationSer;
	
	
	
	
	public QuestionEvaluation addQuestionEvaluation(QuestionEvaluation questionEvaluation){
			return questionEvaluationRepo.save(questionEvaluation);
	}
	
	public void updateQuestionEvaluation(QuestionEvaluation questionEvaluation){
		questionEvaluationRepo.save(questionEvaluation);
	}
	
	public void deleteQuestionEvaluation(Long idQuestionEvaluation){
		questionEvaluationRepo.delete(idQuestionEvaluation);
	}
	
	public final Iterable<QuestionEvaluation> listQuestionsEvaluation() {
		final Iterable<QuestionEvaluation> questionsEvaluation = questionEvaluationRepo.findAll();
		return questionsEvaluation;
	}
	
	public QuestionEvaluation getQuestionEvaluation(Long idQuestionEvaluation){
		return questionEvaluationRepo.findOne(idQuestionEvaluation);
	}
	
	public final Boolean existeQuestionEvaluation(final long idQuestionEvaluation) {
		return questionEvaluationRepo.exists(idQuestionEvaluation);
	}
	
	
	
	public final List<QuestionEvaluation> findByRubriqueEvaluation(final Long idRubriqueEvaluation){
		return questionEvaluationRepo.findByRubriqueEvaluation(rubriqueEvaluationSer.getRubriqueEvaluation(idRubriqueEvaluation));
	}
	
	
}