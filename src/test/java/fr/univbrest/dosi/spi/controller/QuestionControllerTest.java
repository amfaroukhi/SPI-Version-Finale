package fr.univbrest.dosi.spi.controller;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.univbrest.dosi.spi.Application;
import fr.univbrest.dosi.spi.bean.Evaluation;
import fr.univbrest.dosi.spi.bean.Qualificatif;
import fr.univbrest.dosi.spi.bean.Question;
import fr.univbrest.dosi.spi.dao.QualificatifRepository;
import fr.univbrest.dosi.spi.dao.QuestionRepository;
import fr.univbrest.dosi.spi.service.QualificatifService;
import fr.univbrest.dosi.spi.service.QuestionService;

/**
 * @author DOSI
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class QuestionControllerTest {

	@Autowired
	QuestionService business;
	
	@Autowired
	QualificatifService qualbusiness;

	@Test
	public final void addQuestionTest() throws ClientProtocolException, IOException {

		Qualificatif qual = new Qualificatif();
		qual = qualbusiness.getQualificatif(1L);
				
		final Question question = new Question();
		question.setIdQuestion(23);
		question.setIntitule("Facile?");
		question.setNoEnseignant(new BigDecimal("2"));
		question.setType("QUS");
		question.setQualificatif(qual);
		
		// Création du client et éxécution d'une requete POST
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost mockRequestPost = new HttpPost("http://localhost:8090/question/");
		final ObjectMapper mapper = new ObjectMapper();
		final com.fasterxml.jackson.databind.ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		final String jsonInString = ow.writeValueAsString(question);
		mockRequestPost.addHeader("content-type", "application/json");
		mockRequestPost.setEntity(new StringEntity(jsonInString));

		final HttpResponse mockResponse = client.execute(mockRequestPost);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());
		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper map = new ObjectMapper();
		final Question q = map.readValue(rd, Question.class);
		
		business.deleteQuestion(q.getIdQuestion());
	}

	@Test
	public final void deleteQuestionTest() throws ClientProtocolException, IOException {
		
		Qualificatif qual = new Qualificatif();
		qual = qualbusiness.getQualificatif(1L);
		
		final Question question = new Question();
		question.setIdQuestion(23);
		question.setIntitule("Facile?");
		question.setNoEnseignant(new BigDecimal("2"));
		question.setType("QUS");
		question.setQualificatif(qual);
		
		Question temp= business.addQuestion(question);
		
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpDelete mockRequest = new HttpDelete("http://localhost:8090/question/"+temp.getIdQuestion());
		final HttpResponse mockResponse = client.execute(mockRequest);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());

	
	}

	@Test
	public final void listQuestionTest() throws ClientProtocolException, IOException {

		// Création du client et éxécution d'une requete GET
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet mockRequest = new HttpGet("http://localhost:8090/question/1");
		final HttpResponse mockResponse = client.execute(mockRequest);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());

		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper mapper = new ObjectMapper();
		final Question que = mapper.readValue(rd, Question.class);

        assertNotNull(que);


	}
	
}
