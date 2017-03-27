package fr.univbrest.dosi.spi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

 

import fr.univbrest.dosi.spi.bean.Evaluation;
import fr.univbrest.dosi.spi.bean.RubriqueEvaluation;
import fr.univbrest.dosi.spi.service.RubriqueEvaluationService;
 





/**
 * @author DOSI
 *
 */
@RestController
@RequestMapping("RubriqueEvaluation")
public class RubriqueEvaluationController {
	
	
	/**
	 *
	 */
	@Autowired
	private RubriqueEvaluationService rubriqueevaluationService;

	/**
	 *
	 * @param rubrique evaluation
	 *            l'entité de rubrique evaluation
	 * @return une rubrique
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final RubriqueEvaluation ajouterRubrique(@RequestBody final RubriqueEvaluation qual) {
		
		return rubriqueevaluationService.addRubriqueEvaluation(qual);
	}

	/**
	 *
	 * @param rubrique evaluation
	 *            l'entité de rubrique evaluation
	 * @return une rubrique evaluation
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, headers = "Accept=application/json")
	public final RubriqueEvaluation editRubriqueevaluation(@RequestBody final RubriqueEvaluation qual) {
		return rubriqueevaluationService.updateRubriqueEvaluation(qual);
	}

	/**
	 *
	 * @param idRubrique evaliation
	 *            l'id de rubrique evaluation
	 * @return une rubrique evaluation
	 */
	@RequestMapping(value = "/{idRubriqueEvaluation}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final RubriqueEvaluation getrubriqueevaluation(@PathVariable(value = "idRubriqueEvaluation") final long idRubriqueEvaluation) {
		return rubriqueevaluationService.getRubriqueEvaluation(idRubriqueEvaluation);

	}

	
	/**
	 *
	 * @return list de rubrique evaluation
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public final Iterable<RubriqueEvaluation> AllRubriqueevaluation() {

		return rubriqueevaluationService.listRubriqueevaluation();

	}

	/**
	 *
	 * @param  idRubriqueEvaluation
	 *            l'id de rubrique
	 */
	@RequestMapping(value = "/{idRubriqueEvaluation}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public final void removeRubriqueEvaluation(@PathVariable("idRubriqueEvaluation") final long idRubriqueEvaluation) {
		rubriqueevaluationService.deleteRubriqueEvaluation(idRubriqueEvaluation);
	}
	
	@RequestMapping(value = "/getbyEval/{eval}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final List<RubriqueEvaluation> getRubriquesByEval(@PathVariable("eval") final long eval) {
		return (List<RubriqueEvaluation>) rubriqueevaluationService.getbyeval(eval);
	}

	@RequestMapping(value = "/getbyEvalDeux/{eval}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final List<RubriqueEvaluation> getRubriquesByEvalDeux(@PathVariable("eval") final long eval) {
		return (List<RubriqueEvaluation>) rubriqueevaluationService.getbyevalDeux(eval);
	}
}
