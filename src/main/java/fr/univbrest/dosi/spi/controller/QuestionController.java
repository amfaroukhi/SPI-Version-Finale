package fr.univbrest.dosi.spi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Question;
import fr.univbrest.dosi.spi.service.QuestionService;



/**
 * @author DOSI
 *
 */
@RestController
@RequestMapping("question")
public class QuestionController {
	/**
	 *
	 */
	@Autowired
	private QuestionService questionService;

	/**
	 *
	 * @param question
	 *            l'entité de question
	 * @return une question
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Question ajouterQuestion(@RequestBody final Question qual) {
		return questionService.addQuestion(qual);
	}

	/**
	 *
	 * @param question
	 *            l'entité de question
	 * @return une question
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, headers = "Accept=application/json")
	public final Question editQuestion(@RequestBody final Question qual) {
		return questionService.updateQuestion(qual);
	}

	/**
	 *
	 * @param idQuestion
	 *            l'id de question
	 * @return une question
	 */
	@RequestMapping(value = "/{idQuestion}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Question getquestion(@PathVariable(value = "idQuestion") final long idQuestion) {
		return questionService.getQuestion(idQuestion);

	}

	
	/**
	 *
	 * @return list de question
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public final Iterable<Question> AllQuestions() {

		return questionService.listQuestions();

	}

	/**
	 *
	 * @param idQuestion
	 *            l'id de question
	 */
	@RequestMapping(value = "/{idQuestion}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public final void removeQuestion(@PathVariable("idQuestion") final long idQuestion) {
		questionService.deleteQuestion(idQuestion);
	}
}
