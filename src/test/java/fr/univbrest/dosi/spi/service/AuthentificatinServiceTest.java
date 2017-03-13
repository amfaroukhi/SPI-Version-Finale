package fr.univbrest.dosi.spi.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.univbrest.dosi.spi.Application;
import fr.univbrest.dosi.spi.bean.Authentification;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class AuthentificatinServiceTest {

	
	@Autowired
	AuthentificationService authentificationService;
	
	@Test
	public void loginSuccessLoginConnection(){
		Authentification user = authentificationService.logIn("philippe.saliou@univ-brest.fr","dosi");
		Assert.assertNotNull(user);
	}
	
	@Test
	public void loginWrongPwd(){
		Authentification user = authentificationService.logIn("psaliou","fail");
		Assert.assertNull(user);
	}
	
	
	@Test
	public void loginWrongLoginConnection(){
		Authentification user = authentificationService.logIn("login", "dosi");
		Assert.assertNull(user);
	}
	
	@Test
	public void loginWrongCredentials(){
		Authentification user = authentificationService.logIn("login", "pwd");
		Assert.assertNull(user);
	}

}
