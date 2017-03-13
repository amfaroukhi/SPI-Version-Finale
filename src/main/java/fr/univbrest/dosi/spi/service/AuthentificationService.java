package fr.univbrest.dosi.spi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.Authentification;
import fr.univbrest.dosi.spi.bean.LoginPwd;
import fr.univbrest.dosi.spi.dao.AuthentificationRepository;
import fr.univbrest.dosi.spi.dao.PromotionRepository;

@Service
public class AuthentificationService {

	@Autowired
	AuthentificationRepository authentificationRepository;
	
	@Autowired
	PromotionRepository promotionRepository;
	
	public Authentification getConnection(Long idConnection){
		return authentificationRepository.findOne(idConnection);
	}
	
	public Authentification logIn(String loginConnection, String motPasse){
		Authentification auth = authentificationRepository.findByLoginAndPwd(loginConnection,motPasse);
		if(auth != null)
			return auth;
		else
			return null;
	}

}
