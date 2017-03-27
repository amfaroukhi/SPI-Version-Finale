package fr.univbrest.dosi.spi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.ReponseQuestion;
import fr.univbrest.dosi.spi.bean.ReponseQuestionPK;
import fr.univbrest.dosi.spi.dao.ReponseQuestionRepository;

@Service
public class ReponseQuestionService {
	
	@Autowired
	private ReponseQuestionRepository reponseQuestionRepository;
	
	public final Iterable<ReponseQuestion> getReponsesQuestion(){
		return reponseQuestionRepository.findAll();
	}
	
	public void delete(ReponseQuestionPK pk){
		reponseQuestionRepository.delete(pk);
	}
	
	public void ajouterReponseQuestion(ReponseQuestion reponse){
		reponseQuestionRepository.save(reponse);
	}

}
