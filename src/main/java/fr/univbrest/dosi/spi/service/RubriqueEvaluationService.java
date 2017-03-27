package fr.univbrest.dosi.spi.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 


import fr.univbrest.dosi.spi.bean.Evaluation;
import fr.univbrest.dosi.spi.bean.RubriqueEvaluation;
import fr.univbrest.dosi.spi.dao.RubriqueEvaluationRepository;
 




/**
 * @author DOSI
 *
 */
@Service
public class RubriqueEvaluationService {
	
	@Autowired
	private  RubriqueEvaluationRepository rubriqueevaeRepository;
	@Autowired
	private EvaluationService evaluationService;
	public final RubriqueEvaluation addRubriqueEvaluation(final RubriqueEvaluation rub) {
		return  rubriqueevaeRepository.save(rub);
	}

	public final void deleteRubriqueEvaluation(final long idRubriqueEvaluation) {
		rubriqueevaeRepository.delete(idRubriqueEvaluation);
	}

	public final Boolean existeRubriqueEvaluation(final long idRubriqueEvaluation) {
		return rubriqueevaeRepository.exists(idRubriqueEvaluation);
	}

	public final RubriqueEvaluation getRubriqueEvaluation(final long idRubriqueEvaluation) {
		return rubriqueevaeRepository.findOne(idRubriqueEvaluation);
	}

	public final Iterable<RubriqueEvaluation> listRubriqueevaluation() {
		final Iterable<RubriqueEvaluation> rubriqueevae = rubriqueevaeRepository.findAll();
		return rubriqueevae;
	}

	public final RubriqueEvaluation updateRubriqueEvaluation(final RubriqueEvaluation rub) {

		return rubriqueevaeRepository.save(rub);

	}
	
	
	public final Iterable<RubriqueEvaluation> getbyeval(long ideval) {
		
		final Iterable<RubriqueEvaluation> rubriques = rubriqueevaeRepository.findByevaluation(evaluationService.getEvaluation(ideval));
		return rubriques;
	}
	
public final Iterable<RubriqueEvaluation> getbyevalDeux(long ideval) {
		
		final Iterable<RubriqueEvaluation> rubriques = rubriqueevaeRepository.chercherLesEvaluation(ideval);
		return rubriques;
	}

}
