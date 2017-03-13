package fr.univbrest.dosi.spi.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.univbrest.dosi.spi.bean.CGRC;
import fr.univbrest.dosi.spi.bean.Etudiant;


/**
 * @author DOSI
 *
 */
@RepositoryRestResource(collectionResourceRel = "cgrc", path = "cgrc")
public interface CGRCRepository extends PagingAndSortingRepository<CGRC, Long> {

	List<CGRC> findByrvDomain(@Param("rvDomain") String rvDomain);

}
