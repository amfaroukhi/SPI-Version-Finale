package fr.univbrest.dosi.spi.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Date;

import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.univbrest.dosi.spi.Application;
import fr.univbrest.dosi.spi.bean.Etudiant;
import fr.univbrest.dosi.spi.bean.EtudiantPromotion;
import fr.univbrest.dosi.spi.bean.Promotion;
import fr.univbrest.dosi.spi.bean.PromotionPK;
import fr.univbrest.dosi.spi.bean.Rubrique;
import fr.univbrest.dosi.spi.service.EtudiantService;
import fr.univbrest.dosi.spi.service.QuestionService;

	
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EtudiantControllerTest {
	
	@Autowired
	EtudiantService business;

	
	@Test
	public final void listEtudiantnseignantTest() throws ClientProtocolException, IOException {

		// Création du client et éxécution d'une requete GET
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet mockRequest = new HttpGet("http://localhost:8090/etudiant/M2DOSI/2014-2015");
		final HttpResponse mockResponse = client.execute(mockRequest);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());

		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper mapper = new ObjectMapper();
		final Iterable<Etudiant> etu = mapper.readValue(rd, Iterable.class);

		Assert.assertNotNull(etu);

	}
	

	@Test
	public void addEtudiantTest() throws ClientProtocolException, IOException{
		Etudiant etudiant = new Etudiant();
		etudiant.setNoEtudiant("2140777");
		etudiant.setNom("ALAMI");
		etudiant.setPrenom("Ayman");
		etudiant.setSexe("M");
		etudiant.setDateNaissance(new Date(20/02/1996));
		etudiant.setLieuNaissance("Tanger");
		etudiant.setNationalite("Marocaine");
		etudiant.setEmail("alami.ayman@gmail.com");
		etudiant.setAdresse("2, rue des archives");
		etudiant.setVille("Brest");
		etudiant.setPaysOrigine("MA");
		etudiant.setUniversiteOrigine("UAE");
		PromotionPK promotionPK = new PromotionPK("M2DOSI","2013-2014");
		Promotion promotion = new Promotion(promotionPK);
		EtudiantPromotion etudiantPromotion = new EtudiantPromotion();
		etudiantPromotion.setEtudiant(etudiant);
		etudiantPromotion.setPromotion(promotion);
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost mockPost = new HttpPost("http://localhost:8090/etudiant/");
		ObjectMapper mapper = new ObjectMapper();
		com.fasterxml.jackson.databind.ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String jsonInString = ow.writeValueAsString(etudiantPromotion);
		// Ã©tablition de la requette (header+body)
		mockPost.addHeader("content-type", "application/json");
		mockPost.setEntity(new StringEntity(jsonInString));
		
		mapper.readValue(jsonInString, EtudiantPromotion.class);
		
		
		HttpResponse mockResponse = client.execute(mockPost);

		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());	
		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper map = new ObjectMapper();
		final Etudiant e = map.readValue(rd, Etudiant.class);
		business.deleteEtudiant(e.getNoEtudiant());
	}

	@Test
	public void updateEtudiantTest() throws ClientProtocolException, IOException{
		Etudiant etudiant = new Etudiant();
		etudiant.setNoEtudiant("2140777");
		etudiant.setNom("ALAMI");
		etudiant.setPrenom("Ayman");
		etudiant.setSexe("M");
		etudiant.setDateNaissance(new Date(20/02/1996));
		etudiant.setLieuNaissance("Tanger");
		etudiant.setNationalite("Marocaine");
		etudiant.setEmail("alami.ayman@gmail.com");
		etudiant.setAdresse("2, rue des archives");
		etudiant.setVille("Brest");
		etudiant.setPaysOrigine("MA");
		etudiant.setUniversiteOrigine("UAE");
		PromotionPK promotionPK = new PromotionPK("M2DOSI","2013-2014");
		Promotion promotion = new Promotion(promotionPK);
		etudiant.setPromotion(promotion);
		EtudiantPromotion etudiantPromotion = new EtudiantPromotion();
		etudiantPromotion.setEtudiant(etudiant);
		etudiantPromotion.setPromotion(promotion);
		Etudiant temp =  business.addEtudiant(etudiant);
		
		etudiantPromotion.getEtudiant().setVille("Tanger");
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPut mockPost = new HttpPut("http://localhost:8090/etudiant/");
		ObjectMapper mapper = new ObjectMapper();
		com.fasterxml.jackson.databind.ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String jsonInString = ow.writeValueAsString(etudiantPromotion);
		// Ã©tablition de la requette (header+body)
		mockPost.addHeader("content-type", "application/json");
		mockPost.setEntity(new StringEntity(jsonInString));
		HttpResponse mockResponse = client.execute(mockPost);
		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final Etudiant e = mapper.readValue(rd, Etudiant.class);
		
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());	
		
		business.deleteEtudiant(e.getNoEtudiant());
	}

	
	
		 
	
	@SuppressWarnings("unchecked")
	@Test
	public final void deleteEtudiantTest() throws ClientProtocolException, IOException {
		
		Etudiant etudiant = new Etudiant();
		etudiant.setNoEtudiant("2140777");
		etudiant.setNom("ALAMI");
		etudiant.setPrenom("Ayman");
		etudiant.setSexe("M");
		etudiant.setDateNaissance(new Date(20/02/1996));
		etudiant.setLieuNaissance("Tanger");
		etudiant.setNationalite("Marocaine");
		etudiant.setEmail("alami.ayman@gmail.com");
		etudiant.setAdresse("2, rue des archives");
		etudiant.setVille("Brest");
		etudiant.setPaysOrigine("MA");
		etudiant.setUniversiteOrigine("UAE");
		PromotionPK promotionPK = new PromotionPK("M2DOSI","2013-2014");
		Promotion promotion = new Promotion(promotionPK);
		etudiant.setPromotion(promotion);
		Etudiant temp =  business.addEtudiant(etudiant);
		
		// Création du client et éxécution d'une requete GET
				final HttpClient client = HttpClientBuilder.create().build();
				final HttpDelete mockRequest = new HttpDelete("http://localhost:8090/etudiant/"+temp.getNoEtudiant());
				final HttpResponse mockResponse = client.execute(mockRequest);
			
				// Le code retour HTTP doit être un succès (200)
				Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());
			
		
	}
}
