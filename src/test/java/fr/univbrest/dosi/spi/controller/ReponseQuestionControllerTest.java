package fr.univbrest.dosi.spi.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.univbrest.dosi.spi.Application;
import fr.univbrest.dosi.spi.bean.QuestionEvaluation;
import fr.univbrest.dosi.spi.bean.ReponseEvaluation;
import fr.univbrest.dosi.spi.bean.ReponseQuestion;
import fr.univbrest.dosi.spi.service.ReponseEvaluationService;
import fr.univbrest.dosi.spi.service.ReponseQuestionService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ReponseQuestionControllerTest {
	
	@Autowired
	ReponseQuestionService business;
	
	@Autowired
	ReponseQuestionService repQuestion;
	
	@Autowired
	ReponseEvaluationService repEval;
	
	
	
	@Test
	public final void listReponseQuestionTest() throws ClientProtocolException, IOException{
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet mockRequest = new HttpGet("http://localhost:8090/reponseQuestion/");
		final HttpResponse mockReponse = client.execute(mockRequest);
		Assert.assertEquals(200, mockReponse.getStatusLine().getStatusCode());
		
		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockReponse.getEntity().getContent()));
		final ObjectMapper mapper = new ObjectMapper();
		final Iterable<ReponseEvaluation> reponses = mapper.readValue(rd, Iterable.class);
		
		Assert.assertNotNull(reponses);
	}
	
	
	/*@Test
	public void addReponseQuestionTest() throws ClientProtocolException, IOException{
		
		ReponseQuestion rq = new ReponseQuestion();
		rq.setPositionnement(new BigDecimal(1));
		//QuestionEvaluation QuestionEval = new QuestionEvaluation();
	
		rq.setQuestionEvaluation(QuestionEval);
		rq.setQuestionEvaluation();
		rq.setReponseEvaluation(repEval.getReponseEvaluation(1));
		
		
	}*/
	
	

}
