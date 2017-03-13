package fr.univbrest.dosi.spi.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import fr.univbrest.dosi.spi.bean.Qualificatif;

/**
 * @author DOSI
 *
 */
@RestResource(exported = false)
@RepositoryRestResource(collectionResourceRel = "qualificatif", path = "qualificatif")
public interface QualificatifRepository extends PagingAndSortingRepository<Qualificatif, Long> {

}
