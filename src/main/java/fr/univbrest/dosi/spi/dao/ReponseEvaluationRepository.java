package fr.univbrest.dosi.spi.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.univbrest.dosi.spi.bean.ReponseEvaluation;

@RepositoryRestResource(collectionResourceRel = "reponseEvaluation" , path = "reponseEvaluation" ) 
public interface ReponseEvaluationRepository extends PagingAndSortingRepository< ReponseEvaluation ,Long>  {
	 
	List<ReponseEvaluation> findByIdReponseEvaluation(@Param("idReponseEvaluation") long idReponseEvaluation);
	List<ReponseEvaluation> findByNoEtudiant(@Param("noEtudiant") String noEtudiant);
	
}
