
package fr.univbrest.dosi.spi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.ReponseEvaluation;
import fr.univbrest.dosi.spi.dao.ReponseEvaluationRepository;

@Service
public class ReponseEvaluationService {
	
	@Autowired
	private ReponseEvaluationRepository reponseEvaluationRepository;
	
	public final List<ReponseEvaluation> getReponseEvaluationByIdEvaluation(Long id){
		return reponseEvaluationRepository.findByIdReponseEvaluation(id);
		
	}
	
	public final List<ReponseEvaluation> getReponseEvaluationByNoEtudiant(String id){
		return reponseEvaluationRepository.findByNoEtudiant(id);
		
	}
	
	public final Iterable<ReponseEvaluation> getReponses(){
		return reponseEvaluationRepository.findAll();
	}
	
	public void delete(Long id){
		reponseEvaluationRepository.delete(id);
	}
	
	public ReponseEvaluation ajouterReponseEvaluation(ReponseEvaluation reponse){
		reponseEvaluationRepository.save(reponse);
		
		return reponse;
	}
	
	public ReponseEvaluation getReponseEvaluation(long id){
		return reponseEvaluationRepository.findOne(id);
		
	}

}
