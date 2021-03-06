package fr.univbrest.dosi.spi.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.univbrest.dosi.spi.bean.Rubrique;


/**
 * @author DOSI
 *
 */
@RepositoryRestResource(collectionResourceRel = "rubrique", path = "rubrique")
public interface RubriqueRepository extends PagingAndSortingRepository<Rubrique, Long> {

}
