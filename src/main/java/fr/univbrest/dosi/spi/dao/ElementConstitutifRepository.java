package fr.univbrest.dosi.spi.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.univbrest.dosi.spi.bean.ElementConstitutif;
import fr.univbrest.dosi.spi.bean.ElementConstitutifPK;
import fr.univbrest.dosi.spi.bean.UniteEnseignement;

/**
 * @author DOSI
 *
 */
@RepositoryRestResource(collectionResourceRel = "elementConstitutif", path = "elementConstitutif")
public interface ElementConstitutifRepository extends PagingAndSortingRepository<ElementConstitutif, ElementConstitutifPK> {

	
	List<ElementConstitutif> findByUniteEnseignement(@Param("uniteEnseignement") UniteEnseignement uniteEnseignement);

}
