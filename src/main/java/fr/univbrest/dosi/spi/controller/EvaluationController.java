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
import fr.univbrest.dosi.spi.service.EvaluationService;

/**
 * @author DOSI
 *
 */
@RestController
@RequestMapping("evaluation")
public class EvaluationController {
	/**
	 *
	 */
	@Autowired
	private EvaluationService evaluationService;

	/**
	 *
	 * @param evaluation
	 *            l'entité de evaluation
	 * @return une evaluation
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Evaluation ajouterEvaluation(@RequestBody final Evaluation eva) {
		return evaluationService.addEvaluation(eva);
	}

	/**
	 *
	 * @param evaluation
	 *            l'entité de evaluation
	 * @return une evaluation
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, headers = "Accept=application/json")
	public final Evaluation editEvaluation(@RequestBody final Evaluation eva) {
		return evaluationService.updateEvaluation(eva);
	}

	/**
	 *
	 * @param idEvaluation
	 *            l'id de evaluation
	 * @return une evaluation
	 */
	@RequestMapping(value = "/{idEvaluation}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Evaluation getevaluation(
			@PathVariable(value = "idEvaluation") final long idEvaluation) {
		return evaluationService.getEvaluation(idEvaluation);

	}

	@RequestMapping(value = "/getbyens/{ens}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final List<Evaluation> getEvaluationByEns(
			@PathVariable("ens") final int ens) {
		return (List<Evaluation>) evaluationService.getbyens(ens);
	}

	@RequestMapping(value = "/getbypromotion/{codeFormation}/{anneeUniversitaire}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Iterable<Evaluation> getEvaluationByPromotion(
			@PathVariable("codeFormation") final String codeFormation,
			@PathVariable("anneeUniversitaire") final String anneeUniversitaire) {
		return evaluationService.getEvaluationByPromotion(codeFormation,
				anneeUniversitaire);
	}

	/**
	 *
	 * @return list de evaluation
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public final Iterable<Evaluation> AllEvaluations() {

		return evaluationService.listEvaluations();

	}

	/**
	 *
	 * @param idEvaluation
	 *            l'id de evaluation
	 */
	@RequestMapping(value = "/{idEvaluation}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public final void removeEvaluation(
			@PathVariable("idEvaluation") final long idEvaluation) {
		evaluationService.deleteEvaluation(idEvaluation);
	}

}
