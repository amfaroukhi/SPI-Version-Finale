package fr.univbrest.dosi.spi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Promotion;
import fr.univbrest.dosi.spi.bean.PromotionPK;
import fr.univbrest.dosi.spi.service.FormationService;
import fr.univbrest.dosi.spi.service.PromotionService;

@RestController
@RequestMapping("promotion")
public class PromotionController {

	@Autowired
	private FormationService formationService;

	@Autowired
	private PromotionService promotionService;

	// Liste des promotions
	@RequestMapping( value = "/", produces = "application/json", method = RequestMethod.GET)
	public final Iterable<Promotion> promotions() {

		return promotionService.getPromotions();
	}

	// Liste des promotions par formation
	@RequestMapping(value = "/{codeFormation}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Iterable<Promotion> promotionsByFormation(
			@PathVariable(value = "codeFormation") final String codeFormation) {
		return (Iterable<Promotion>) promotionService
				.getPromotionByFormation(codeFormation);

	}
	
	@RequestMapping(value = "/{codeFormation}/{anneeuniv}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Promotion getPromotion(
			@PathVariable(value = "codeFormation") final String codeFormation,
			@PathVariable(value = "anneeuniv") final String anneeuniv) {
		
		PromotionPK propk = new PromotionPK(codeFormation, anneeuniv);
		return promotionService.getPromotion(propk);

	}
}
