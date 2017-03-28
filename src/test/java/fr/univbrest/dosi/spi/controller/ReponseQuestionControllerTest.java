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
import fr.univbrest.dosi.spi.bean.QuestionEvaluation;
import fr.univbrest.dosi.spi.bean.RepQstRepEvalQstEval;
import fr.univbrest.dosi.spi.bean.ReponseEvaluation;
import fr.univbrest.dosi.spi.bean.ReponseQuestion;
import fr.univbrest.dosi.spi.bean.ReponseQuestionPK;
import fr.univbrest.dosi.spi.service.QuestionEvaluationService;
import fr.univbrest.dosi.spi.service.ReponseEvaluationService;
import fr.univbrest.dosi.spi.service.ReponseQuestionService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ReponseQuestionControllerTest {
	
	@Autowired
	ReponseQuestionService business;
	
	@Autowired
	ReponseEvaluationService reponseEval;
	
	@Autowired
	QuestionEvaluationService questionEval;
	
	
	
	
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
	
	
	@Test
	public void addReponseQuestionTest() throws ClientProtocolException, IOException{
		
		ReponseQuestion rq = new ReponseQuestion();
		rq.setPositionnement(new BigDecimal(1));
		ReponseEvaluation rps=reponseEval.getReponseEvaluation(1);
		QuestionEvaluation qst=questionEval.getQuestionEvaluation(new Long(22));
		ReponseQuestionPK rpsQst= new ReponseQuestionPK(22,1);

		rq.setId(rpsQst);
		RepQstRepEvalQstEval Test = new RepQstRepEvalQstEval(1l,rq,1l);
		
		
		
		
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost mockPost = new HttpPost("http://localhost:8090/reponseQuestion/");
		ObjectMapper mapper = new ObjectMapper();
		com.fasterxml.jackson.databind.ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String jsonInString = ow.writeValueAsString(Test);
		
		mockPost.addHeader("content-type", "application/json");
		mockPost.setEntity(new StringEntity(jsonInString));
		
		mapper.readValue(jsonInString, RepQstRepEvalQstEval.class);
		
		HttpResponse mockResponse = client.execute(mockPost);

		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());	
		
		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper map = new ObjectMapper();
		final ReponseQuestion r = map.readValue(rd, ReponseQuestion.class);
		business.delete(r.getId());
		
		
	}
	
	

}
