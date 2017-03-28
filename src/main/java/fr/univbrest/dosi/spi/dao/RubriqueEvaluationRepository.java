package fr.univbrest.dosi.spi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.univbrest.dosi.spi.bean.Evaluation;
import fr.univbrest.dosi.spi.bean.RubriqueEvaluation;

 


/**
 * @author DOSI
 *
 */
@RepositoryRestResource(collectionResourceRel = "rubriqueevaluation", path = "rubriqueevaluation")
public interface RubriqueEvaluationRepository extends PagingAndSortingRepository<RubriqueEvaluation, Long>  {

	
	
	List<RubriqueEvaluation> findByevaluation(@Param("evaluation") Evaluation evaluation);
	
	@Query("SELECT r FROM RubriqueEvaluation r WHERE r.evaluation.idEvaluation = :idevaluation")
	List<RubriqueEvaluation> chercherLesEvaluation(@Param("idevaluation") Long idevaluation);


	

}
