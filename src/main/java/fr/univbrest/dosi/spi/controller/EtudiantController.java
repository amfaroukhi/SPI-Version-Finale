package fr.univbrest.dosi.spi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Etudiant;
import fr.univbrest.dosi.spi.bean.EtudiantPromotion;
import fr.univbrest.dosi.spi.bean.Promotion;
import fr.univbrest.dosi.spi.bean.PromotionPK;
import fr.univbrest.dosi.spi.bean.User;
import fr.univbrest.dosi.spi.service.EtudiantService;
import fr.univbrest.dosi.spi.service.PromotionService;

@RestController
@RequestMapping("etudiant")
public class EtudiantController {

	@Autowired
	private EtudiantService etudiantService;

	@Autowired
	User user;

	@Autowired
	private PromotionService promotionService;

	@RequestMapping(value = "/", produces = "application/json")
	public final Iterable<Etudiant> etudiants() {

		return etudiantService.listEtudiants();

	}

	@RequestMapping(value = "/{codeFormation}/{anneeUniversitaire}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Iterable<Etudiant> etudiantsByPromotion(
			@PathVariable(value = "codeFormation") final String codeFormation,
			@PathVariable(value = "anneeUniversitaire") final String anneeUniversitaire) {
		PromotionPK p = new PromotionPK(codeFormation, anneeUniversitaire);
		Promotion pro = promotionService.getPromotion(p);
		return etudiantService.getEtudiantByPromotion(pro);
	}

	@RequestMapping(value = "/countEtudiants/{codeFormation}/{anneeUniversitaire}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public final long etudiantsInPromotion(
			@PathVariable(value = "codeFormation") final String codeFormation,
			@PathVariable(value = "anneeUniversitaire") final String anneeUniversitaire) {
		PromotionPK p = new PromotionPK(codeFormation, anneeUniversitaire);
		Promotion pro = promotionService.getPromotion(p);
		return etudiantService.getEtudiantByPromotion(pro).size();

	}

	@RequestMapping(value = "/countEtudiant")
	public final long countEtudiant() {
		return etudiantService.countEtudiant();
	}

	/**
	 *
	 * @param etudiant
	 *            l'entité de l'etudiant
	 * @return le message d'ajout
	 */

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
	public final Etudiant addEtudiant(
			@RequestBody final EtudiantPromotion etudiantPromotion) {
		Promotion promotion = promotionService.getPromotion(etudiantPromotion
				.getPromotion().getPromotionPK());
		Etudiant etudiant = etudiantPromotion.getEtudiant();
		etudiant.setPromotion(promotion);
		return etudiantService.addEtudiant(etudiant);

	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = "application/json")
	public final Etudiant updateEtudiant(
			@RequestBody final EtudiantPromotion etudiantPromotion) {
		Promotion promotion = promotionService.getPromotion(etudiantPromotion
				.getPromotion().getPromotionPK());
		Etudiant etudiant = etudiantPromotion.getEtudiant();
		etudiant.setPromotion(promotion);
		return etudiantService.updateEtudiant(etudiant);
	}

	@RequestMapping(value = "/{noetudiant}", method = RequestMethod.DELETE)
	public final void deleteEtudiant(
			@PathVariable(value = "noetudiant") final String noEtudiant) {
		// this.checkDroits(TypeDroit.DELETE);
		etudiantService.deleteEtudiant(noEtudiant);
	}

	/**
	 *
	 * @return liste des etudiant
	 */

	/**
	 *
	 * @param noEtudiant
	 *            l'id de l'etudiant
	 * @return un etudiant
	 */
	@RequestMapping(value = "/{noetudiant}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Etudiant getEtudiant(
			@PathVariable(value = "noetudiant") final String noEtudiant) {
		return etudiantService.getEtudiant(noEtudiant);
	}

	/**
	 *
	 * @param nom
	 *            de recherche pour un etudiant
	 * @return list des etudiants ayant le parmetre nom
	 */
	// @RequestMapping(value ="/getetu/{id}")
	@RequestMapping(value = "/getbynom/{nom}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public final List<Etudiant> getEtudiantByNom(
			@PathVariable(value = "nom") final String nom) {
		return etudiantService.getEtudiantByNom(nom);
	}

	public EtudiantService getEtudiantService() {
		return etudiantService;
	}

	@RequestMapping(value = "/promo/{noetudiant}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Promotion getPromoEtu(
			@PathVariable(value = "noetudiant") final String noEtudiant) {
		return etudiantService.findPromotionByNoEtudiant(noEtudiant);
	}

}