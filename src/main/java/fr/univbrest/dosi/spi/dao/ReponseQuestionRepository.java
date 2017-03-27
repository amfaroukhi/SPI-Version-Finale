package fr.univbrest.dosi.spi.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.univbrest.dosi.spi.bean.ReponseEvaluation;
import fr.univbrest.dosi.spi.bean.ReponseQuestion;
import fr.univbrest.dosi.spi.bean.ReponseQuestionPK;

@RepositoryRestResource(collectionResourceRel = "reponseQuestion", path="reponseQuestion" )
public interface ReponseQuestionRepository extends PagingAndSortingRepository<ReponseQuestion,ReponseQuestionPK>{
	

	List<ReponseQuestion> findByIdReponseEvaluation(@Param("idReponseEvaluation") Long idReponseEvaluation);
	
}
