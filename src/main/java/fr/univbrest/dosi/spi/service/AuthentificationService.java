package fr.univbrest.dosi.spi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.Authentification;
import fr.univbrest.dosi.spi.dao.AuthentificationRepository;

@Service
public class AuthentificationService {

	@Autowired
	AuthentificationRepository authentificationRepository;

	public Authentification getConnection(Long idConnection) {
		return authentificationRepository.findOne(idConnection);
	}

	public Authentification logIn(String loginConnection, String motPasse) {
		Authentification auth = authentificationRepository.findByLoginAndPwd(
				loginConnection, motPasse);
		if (auth != null)
			return auth;
		else
			return null;
	}
}
