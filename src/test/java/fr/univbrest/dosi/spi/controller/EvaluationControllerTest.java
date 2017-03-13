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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.univbrest.dosi.spi.Application;
import fr.univbrest.dosi.spi.bean.Evaluation;
import fr.univbrest.dosi.spi.service.EvaluationService;

/**
 * @author DOSI
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EvaluationControllerTest {

	@Autowired
	EvaluationService business;

	@Test
	public final void addEvaluationTest() throws ClientProtocolException, IOException {

		Evaluation eval = new Evaluation();
		eval.setIdEvaluation(3);
		eval.setAnneeUniversitaire("2014-2015");
		eval.setCodeEc(null);
		eval.setCodeFormation("M2DOSI");
		eval.setCodeUe("ISI");
		eval.setEtat("CLO");
		eval.setDebutReponse(new Date(2014-11-01));
		eval.setDesignation("Evaluation");
		eval.setFinReponse(new Date(2014-11-12));
		eval.setNoEnseignant(new BigDecimal("2"));
		eval.setNoEvaluation(new BigDecimal("3"));
		eval.setPeriode("Du 22 septembre au 24 octobre");
		
		
		// Création du client et éxécution d'une requete GET
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost mockRequestPost = new HttpPost("http://localhost:8090/evaluation/");
		final ObjectMapper mapper = new ObjectMapper();
		final com.fasterxml.jackson.databind.ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		final String jsonInString = ow.writeValueAsString(eval);
		mockRequestPost.addHeader("Content-type", "application/json");
		mockRequestPost.setEntity(new StringEntity(jsonInString));

		final HttpResponse mockResponse = client.execute(mockRequestPost);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());
		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper map = new ObjectMapper();
		final Evaluation e = map.readValue(rd, Evaluation.class);
		
		business.deleteEvaluation(e.getIdEvaluation());
	}

	@Test
	public final void deleteEvaluationTest() throws ClientProtocolException, IOException {

		Evaluation eval = new Evaluation();
		eval.setIdEvaluation(3);
		eval.setAnneeUniversitaire("2014-2015");
		eval.setCodeEc(null);
		eval.setCodeFormation("M2DOSI");
		eval.setCodeUe("ISI");
		eval.setEtat("CLO");
		eval.setDebutReponse(new Date(2014-11-01));
		eval.setDesignation("Evaluation");
		eval.setFinReponse(new Date(2014-11-12));
		eval.setNoEnseignant(new BigDecimal("2"));
		eval.setNoEvaluation(new BigDecimal("3"));
		eval.setPeriode("Du 22 septembre au 24 octobre");
		Evaluation temp = business.addEvaluation(eval);
		
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpDelete mockRequest = new HttpDelete("http://localhost:8090/evaluation/"+temp.getIdEvaluation());
		final HttpResponse mockResponse = client.execute(mockRequest);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());

	
	}

	@Test
	public final void listEvaluationTest() throws ClientProtocolException, IOException {

		// Création du client et éxécution d'une requete GET
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet mockRequest = new HttpGet("http://localhost:8090/evaluation/1");
		final HttpResponse mockResponse = client.execute(mockRequest);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());

		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper mapper = new ObjectMapper();
		final Evaluation eval = mapper.readValue(rd, Evaluation.class);

        assertNotNull(eval);


	}
	
}
