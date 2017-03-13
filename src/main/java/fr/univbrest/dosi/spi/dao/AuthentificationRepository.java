package fr.univbrest.dosi.spi.dao;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import fr.univbrest.dosi.spi.bean.Authentification;

public interface AuthentificationRepository extends PagingAndSortingRepository<Authentification, Long>{

	public Authentification findByLoginAndPwd(@Param("loginConnection") String loginConnection, @Param("motPasse") String motPasse);

	
}
