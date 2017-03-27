package fr.univbrest.dosi.spi.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.univbrest.dosi.spi.bean.Etudiant;
import fr.univbrest.dosi.spi.bean.Promotion;
import fr.univbrest.dosi.spi.bean.QuestionEvaluation;
import fr.univbrest.dosi.spi.bean.RubriqueEvaluation;


@RepositoryRestResource(collectionResourceRel = "questionEvaluation", path = "questionEvaluation")
public interface QuestionEvaluationRepository extends PagingAndSortingRepository<QuestionEvaluation, Long>{

	List<QuestionEvaluation> findByRubriqueEvaluation(@Param("rubriqueEvaluation") RubriqueEvaluation rubriqueEvaluation);
	
}
