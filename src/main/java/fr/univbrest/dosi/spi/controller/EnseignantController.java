package fr.univbrest.dosi.spi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Enseignant;
import fr.univbrest.dosi.spi.bean.Promotion;
import fr.univbrest.dosi.spi.bean.UniteEnseignement;
import fr.univbrest.dosi.spi.bean.User;
import fr.univbrest.dosi.spi.service.EnseignantService;
import fr.univbrest.dosi.spi.service.PromotionService;
import fr.univbrest.dosi.spi.service.UniteEnseignementService;

/**
 * @author DOSI
 *
 */

@RestController
@RequestMapping("enseignant")
public class EnseignantController {
	/**
	 *
	 */
	@Autowired
	private EnseignantService enseignantService;

	@Autowired
	private PromotionService promotionService;

	@Autowired
	private UniteEnseignementService uniteEnseignementService;

	@Autowired
	EnseignantService ensService;

	@Autowired
	User user;
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Enseignant addEnseignant(@RequestBody final Enseignant enseignant) {
		// this.checkDroits(TypeDroit.CREATE);
		return enseignantService.addEnseignant(enseignant);
		
	}

	/**
	 *
	 * @param noEnseignant
	 *            l'id de l'enseignant
	 */
	@RequestMapping(value = "/{noenseignant}" ,method = RequestMethod.DELETE)
	public final void deleteEnseignant(@PathVariable(value = "noenseignant") final Integer noEnseignant) {
		// this.checkDroits(TypeDroit.DELETE);
		enseignantService.deleteEnseignant(noEnseignant);
	}

	@RequestMapping(value = "/" ,method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Iterable<Enseignant> enseignant() {
		
		return enseignantService.listens();
	}

	/**
	 *
	 * @param noEnseignant
	 *            l'id de l'enseignant
	 * @return un boolean
	 */
	@RequestMapping(value = "/existens/{noenseignant}", method = RequestMethod.GET)
	public final Boolean existEnseignant(@PathVariable(value = "noenseignant") final Integer noEnseignant) {
		return enseignantService.existEnseignant(noEnseignant);
	}

	/**
	 *
	 * @param noEnseignant
	 *            l'id de l'enseignant
	 * @return un enseignant
	 */
	@RequestMapping(value = "/getens/{noenseignant}" , method = RequestMethod.GET)
	public final Enseignant getEnseignant(@PathVariable(value = "noenseignant") final Integer noEnseignant) {
		return enseignantService.getEnseignant(noEnseignant);
	}

	/**
	 *
	 * @param nom
	 *            de recherche pour un enseignant
	 * @return list des enseignant ayant le parmetre nom
	 */
	@RequestMapping(value = "/getbynom/{nom}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final List<Enseignant> getEnseignantByNom(@PathVariable(value = "nom") final String nom) {
		// this.checkDroits(TypeDroit.SELECT);
		return enseignantService.getEnseignantByNom(nom);
	}

	public EnseignantService getEnseignantService() {
		return enseignantService;
	}

	/**
	 *
	 * @param noEnseignant
	 *            l'id de l'enseignant
	 * @return liste des promotions
	 */
	@RequestMapping(value = "/getpromotionenseignant/{noenseignant}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final List<Promotion> getPromotionEnseignant(@PathVariable(value = "noenseignant") final Integer noEnseignant) {
		return promotionService.getPromotionByEnseignant(noEnseignant);
	}

	public PromotionService getPromotionService() {
		return promotionService;
	}

	/**
	 *
	 * @param noEnseignant
	 *            l'id de l'enseignant
	 * @return liste des unite enseignant
	 */

	@RequestMapping(value ="/getuebyenseignant/{noenseignant}", method = RequestMethod.GET)
	public final List<UniteEnseignement> getUEByEnseignant(@PathVariable("noenseignant") final Integer noEnseignant) {
		return uniteEnseignementService.getUEByEnseignant(noEnseignant);
	}

	public UniteEnseignementService getUniteEnseignementService() {
		return uniteEnseignementService;
	}

	public void setEnseignantService(final EnseignantService enseignantService) {
		this.enseignantService = enseignantService;
	}

	public void setPromotionService(final PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	public void setUniteEnseignementService(final UniteEnseignementService uniteEnseignementService) {
		this.uniteEnseignementService = uniteEnseignementService;
	}

	/**
	 *
	 * @param enseignant
	 *            objet
	 * @return message de modification
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = { "application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public final Enseignant updateEnseignant(@RequestBody final Enseignant enseignant) {
		return enseignantService.updateEnseignant(enseignant);
		
	}
}
