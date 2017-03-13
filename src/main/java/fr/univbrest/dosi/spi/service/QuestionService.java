package fr.univbrest.dosi.spi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.Question;
import fr.univbrest.dosi.spi.dao.QuestionRepository;

/**
 * @author DOSI
 *
 */
@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	public final Question addQuestion(final Question que) {
		return questionRepository.save(que);
	}

	public final void deleteQuestion(final long idQuestion) {
		questionRepository.delete(idQuestion);
	}

	public final Boolean existeQuestion(final long idQuestion) {
		return questionRepository.exists(idQuestion);
	}

	public final Question getQuestion(final long idQuestion) {
		return questionRepository.findOne(idQuestion);
	}

	public final Iterable<Question> listQuestions() {
		final Iterable<Question> questions = questionRepository.findAll();
		return questions;
	}

	public final Question updateQuestion(final Question que) {

		return questionRepository.save(que);

	}

}
