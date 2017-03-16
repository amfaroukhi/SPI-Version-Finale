package fr.univbrest.dosi.spi.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.univbrest.dosi.spi.bean.Etudiant;
import fr.univbrest.dosi.spi.bean.Evaluation;


/**
 * @author DOSI
 *
 */
@RepositoryRestResource(collectionResourceRel = "evaluation", path = "evaluation")
public interface EvaluationRepository extends PagingAndSortingRepository<Evaluation, Long> {
	
	List<Evaluation> findBynoEnseignant(@Param("noEnseignant") BigDecimal noEnseignant);

}
