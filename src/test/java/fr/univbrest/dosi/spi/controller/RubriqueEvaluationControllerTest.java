package fr.univbrest.dosi.spi.controller;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;

import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
 
import org.junit.Before;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.univbrest.dosi.spi.Application;
 
import fr.univbrest.dosi.spi.bean.Evaluation;
import fr.univbrest.dosi.spi.bean.RubriqueEvaluation;
import fr.univbrest.dosi.spi.dao.EvaluationRepository;
import fr.univbrest.dosi.spi.service.EvaluationService;
import fr.univbrest.dosi.spi.service.RubriqueEvaluationService;

 


/**
**
* @author DOSI
*
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RubriqueEvaluationControllerTest  {
	@Autowired
	RubriqueEvaluationService business;
	
	@Autowired
	EvaluationRepository repoEvaluation;
	
	private Evaluation evalutionPourTest;
	

	@Before
	public void init() {
		
		evalutionPourTest = new Evaluation();
		evalutionPourTest.setIdEvaluation(99);
		evalutionPourTest.setAnneeUniversitaire("2014-2015");
		evalutionPourTest.setCodeEc(null);
		evalutionPourTest.setCodeFormation("M2DOSI");
		evalutionPourTest.setCodeUe("ISI");
		evalutionPourTest.setEtat("CLO");
		evalutionPourTest.setDebutReponse(new Date(2014-11-01));
		evalutionPourTest.setDesignation("Evaluation");
		evalutionPourTest.setFinReponse(new Date(2014-11-12));
		evalutionPourTest.setNoEnseignant(new BigDecimal("2"));
		evalutionPourTest.setNoEvaluation(new BigDecimal("3"));
		evalutionPourTest.setPeriode("Du 22 septembre au 24 octobre");
		
		repoEvaluation.save(evalutionPourTest);
	}
	
	@After
	public void reset() {
		// suprime l'évluation en base
		repoEvaluation.delete(evalutionPourTest);
	}
	
	@Test
	public final void addRubriqueEvaluationTest() throws ClientProtocolException, IOException {

		RubriqueEvaluation rub = new RubriqueEvaluation();
		rub.setIdRubriqueEvaluation(99);
		
		evalutionPourTest = new Evaluation();
		evalutionPourTest.setIdEvaluation(99);
		evalutionPourTest.setAnneeUniversitaire("2014-2015");
		evalutionPourTest.setCodeEc(null);
		evalutionPourTest.setCodeFormation("M2DOSI");
		evalutionPourTest.setCodeUe("ISI");
		evalutionPourTest.setEtat("CLO");
		evalutionPourTest.setDebutReponse(new Date(2014-11-01));
		evalutionPourTest.setDesignation("Evaluation");
		evalutionPourTest.setFinReponse(new Date(2014-11-12));
		evalutionPourTest.setNoEnseignant(new BigDecimal("2"));
		evalutionPourTest.setNoEvaluation(new BigDecimal("3"));
		evalutionPourTest.setPeriode("Du 22 septembre au 24 octobre");
		
		rub.setEvaluation(evalutionPourTest);
		rub.setOrdre(new BigDecimal("7"));
		rub.setRubrique(null);
		rub.setDesignation("projet");
		
		
		// Création du client et éxécution d'une requete GET
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost mockRequestPost = new HttpPost("http://localhost:8090/RubriqueEvaluation/");
		final ObjectMapper mapper = new ObjectMapper();
		final com.fasterxml.jackson.databind.ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		final String jsonInString = ow.writeValueAsString(rub);
		mockRequestPost.addHeader("content-type", "application/json");
		mockRequestPost.setEntity(new StringEntity(jsonInString));

		final HttpResponse mockResponse = client.execute(mockRequestPost);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());
		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper map = new ObjectMapper();
		final RubriqueEvaluation r = map.readValue(rd, RubriqueEvaluation.class);
		business.deleteRubriqueEvaluation(r.getIdRubriqueEvaluation());
	}

	@Test
	public final void deleteRubriqueEvaluationTest() throws ClientProtocolException, IOException {

		RubriqueEvaluation rub = new RubriqueEvaluation();
		rub.setIdRubriqueEvaluation(9);
		rub.setEvaluation(evalutionPourTest);
		rub.setOrdre(new BigDecimal("9"));
		rub.setRubrique(null);
		rub.setDesignation("projet");
		RubriqueEvaluation temp= business.addRubriqueEvaluation(rub);
		
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpDelete mockRequest = new HttpDelete("http://localhost:8090/RubriqueEvaluation/"+temp.getIdRubriqueEvaluation());
		final HttpResponse mockResponse = client.execute(mockRequest);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());

	
	}

	@Test
	public final void listRubriqueEvaluationTest() throws ClientProtocolException, IOException {

		// Création du client et éxécution d'une requete GET
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet mockRequest = new HttpGet("http://localhost:8090/RubriqueEvaluation/1");
		final HttpResponse mockResponse = client.execute(mockRequest);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());

		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper mapper = new ObjectMapper();
		final RubriqueEvaluation rub = mapper.readValue(rd, RubriqueEvaluation.class);

        assertNotNull(rub);


	}
	
	
	
}
