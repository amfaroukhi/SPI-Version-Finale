package fr.univbrest.dosi.spi.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestBody;

import fr.univbrest.dosi.spi.bean.Etudiant;
import fr.univbrest.dosi.spi.bean.Promotion;

/**
 * @author DOSI
 *
 */
@RepositoryRestResource(collectionResourceRel = "etudiant", path = "etudiant")
public interface EtudiantRepository extends
		PagingAndSortingRepository<Etudiant, String> {

	List<Etudiant> findByNom(@Param("nom") String nom);

	@Override
	Etudiant save(@RequestBody Etudiant etu);

	List<Etudiant> findByPromotion(@Param("promotion") Promotion promotion);

	Promotion findPromotionByNoEtudiant(@Param("noEtudiant") String noEtudiant);

}
