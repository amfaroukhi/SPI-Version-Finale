package fr.univbrest.dosi.spi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import junit.framework.Assert;
import fr.univbrest.dosi.spi.bean.Authentification;
import fr.univbrest.dosi.spi.bean.User;
import fr.univbrest.dosi.spi.exception.SPIException;

public class AuthentificationControllerTest {
	@Test
	 public void Authentifier() throws ClientProtocolException, IOException {

	 List<String> roles = new ArrayList<String>();
	 roles.add("ADM");
	 User user = new User("adm","dosi",roles);
	 Authentification authent= new Authentification();
	 	authent.setLoginConnection("philippe.saliou@univ-brest.fr");
	 	authent.setMotPasse("dosi");
	 
	 // CrÃ©aton du client et Ã©xÃ©cution d'une requete POST
	 final HttpClient client = HttpClientBuilder.create().build();
	 final HttpPost mockRequestPost = new HttpPost("http://localhost:8090/auth");
	 
	 final ObjectMapper mapper = new ObjectMapper();
	 final ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	 final String jsonInString = ow.writeValueAsString(authent);
	 
	 mockRequestPost.addHeader("content-type", "application/json");
	 mockRequestPost.addHeader("Accept", "application/json");
	 mockRequestPost.setEntity(new StringEntity(jsonInString));
	
	 final HttpResponse mockResponse = client.execute(mockRequestPost);
	 Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());
	
	}
}
