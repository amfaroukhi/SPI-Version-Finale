package fr.univbrest.dosi.spi.controller;

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
import fr.univbrest.dosi.spi.bean.Etudiant;
import fr.univbrest.dosi.spi.bean.EtudiantPromotion;
import fr.univbrest.dosi.spi.bean.Evaluation;
import fr.univbrest.dosi.spi.bean.ReponseEvaluation;
import fr.univbrest.dosi.spi.service.EvaluationService;
import fr.univbrest.dosi.spi.service.ReponseEvaluationService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ReponseEvaluationControllerTest {
	
	@Autowired
	ReponseEvaluationService business;
	
	@Autowired
	EvaluationService evalBusiness;
	
	@Test
	public final void listReponseEvaluationTest() throws ClientProtocolException, IOException{
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet mockRequest = new HttpGet("http://localhost:8090/reponseEvaluation/");
		final HttpResponse mockResponse = client.execute(mockRequest);
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());
		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper mapper = new ObjectMapper();
		final Iterable<ReponseEvaluation> reponses = mapper.readValue(rd, Iterable.class);
		
		Assert.assertNotNull(reponses);
	}
	
	@Test
	public void addReponseEvaluationTest() throws ClientProtocolException, IOException{

		Evaluation eval = evalBusiness.getEvaluation(1);
		ReponseEvaluation repEval = new ReponseEvaluation();
		repEval.setNoEtudiant("21406961");
		repEval.setCommentaire("un commentaire");
		
		repEval.setEvaluation(eval);
		
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost mockPost = new HttpPost("http://localhost:8090/reponseEvaluation/");
		ObjectMapper mapper = new ObjectMapper();
		com.fasterxml.jackson.databind.ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String jsonInString = ow.writeValueAsString(repEval);
		
		mockPost.addHeader("content-type", "application/json");
		mockPost.setEntity(new StringEntity(jsonInString));
		
		mapper.readValue(jsonInString, ReponseEvaluation.class);
		
		HttpResponse mockResponse = client.execute(mockPost);

		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());	
		
		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper map = new ObjectMapper();
		final ReponseEvaluation r = map.readValue(rd, ReponseEvaluation.class);
		business.delete(r.getIdReponseEvaluation());
	}
	
	@Test
	public final void deleteReponseEvaluationTest() throws ClientProtocolException, IOException {
		
		Evaluation eval = evalBusiness.getEvaluation(1);
		ReponseEvaluation repEval = new ReponseEvaluation();
		repEval.setNoEtudiant("21406961");
		repEval.setCommentaire("un commentaire");
		
		repEval.setEvaluation(eval);
		
		ReponseEvaluation rep = business.ajouterReponseEvaluation(repEval);
		
		final HttpClient client =  HttpClientBuilder.create().build();
		final HttpDelete mockRequest = new HttpDelete("http://localhost:8090/reponseEvaluation/supp/"+rep.getIdReponseEvaluation());
		final HttpResponse mockResponse = client.execute(mockRequest);
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());
		
		
	}

}
