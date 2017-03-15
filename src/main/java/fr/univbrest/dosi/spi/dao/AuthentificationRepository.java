package fr.univbrest.dosi.spi.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.univbrest.dosi.spi.bean.Authentification;

@RepositoryRestResource(collectionResourceRel = "authentification", path = "authentification")
public interface AuthentificationRepository extends
		PagingAndSortingRepository<Authentification, Long> {

	public Authentification findByLoginAndPwd(
			@Param("loginConnection") String loginConnection,
			@Param("motPasse") String motPasse);

}
