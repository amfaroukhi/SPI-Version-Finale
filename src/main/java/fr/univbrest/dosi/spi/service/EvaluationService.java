package fr.univbrest.dosi.spi.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.Etudiant;
import fr.univbrest.dosi.spi.bean.Evaluation;
import fr.univbrest.dosi.spi.bean.Promotion;
import fr.univbrest.dosi.spi.dao.EvaluationRepository;

/**
 * @author DOSI
 *
 */
@Service
public class EvaluationService {

	@Autowired
	private EvaluationRepository evaluationRepository;

	public final Evaluation addEvaluation(final Evaluation eva) {
		return evaluationRepository.save(eva);
	}

	public final void deleteEvaluation(final long idEvaluation) {
		evaluationRepository.delete(idEvaluation);
	}

	public final Boolean existeEvaluation(final long idEvaluation) {
		return evaluationRepository.exists(idEvaluation);
	}

	public final Evaluation getEvaluation(final long idEvaluation) {
		return evaluationRepository.findOne(idEvaluation);
	}

	public final Iterable<Evaluation> listEvaluations() {
		final Iterable<Evaluation> evaluations = evaluationRepository.findAll();
		return evaluations;
	}
	
	public final Iterable<Evaluation> getbyens(int ens) {
		final Iterable<Evaluation> evaluations = evaluationRepository.findBynoEnseignant(new BigDecimal(ens));
		return evaluations;
	}

	public final Evaluation updateEvaluation(final Evaluation eva) {

		return evaluationRepository.save(eva);

	}
	
	public final long countEvaluations() {

		return evaluationRepository.count();

	}
	
	public final List<Evaluation> getEvaluationByPromotion(final String codeFormation, final String anneeUniversitaire){
		return evaluationRepository.findByPromotion(codeFormation,anneeUniversitaire);
	}

}
