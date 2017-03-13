package fr.univbrest.dosi.spi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.ElementConstitutif;
import fr.univbrest.dosi.spi.bean.ElementConstitutifPK;
import fr.univbrest.dosi.spi.bean.UniteEnseignement;
import fr.univbrest.dosi.spi.bean.UniteEnseignementPK;
import fr.univbrest.dosi.spi.service.ElementConstitutifService;



/**
 * @author DOSI
 *
 */
@RestController
@RequestMapping("ec")
public class ElementConstitutifController {
	/**
	 *
	 */
	@Autowired
	private ElementConstitutifService ecService;

	/**
	 *
	 * @param ec
	 *            l'entité de ec
	 * @return une ec
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final ElementConstitutif ajouterElementConstitutif(@RequestBody final ElementConstitutif ec) {
		return ecService.addElementConstitutif(ec);
	}

	/**
	 *
	 * @param ec
	 *            l'entité de ec
	 * @return une ec
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, headers = "Accept=application/json")
	public final ElementConstitutif editElementConstitutif(@RequestBody final ElementConstitutif ec) {
		return ecService.updateElementConstitutif(ec);
	}

	/**
	 *
	 * @param idElementConstitutif
	 *            l'id de ec
	 * @return une ec
	 */
	@RequestMapping(value = "/{codeFormation}/{codeUe}/{codeEc}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final ElementConstitutif getByCodeFormationandUE(@PathVariable(value = "codeFormation") final String codeFormation,@PathVariable(value = "codeUe") final String codeue,@PathVariable(value = "codeEc") final String codeec) {
		ElementConstitutifPK ecPK = new ElementConstitutifPK(codeFormation, codeue,codeec);
		return ecService.getElementConstitutif(ecPK);
	}
	
	@RequestMapping(value = "/{codeFormation}/{codeUe}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final List<ElementConstitutif> getByCodeFormationandUE(@PathVariable(value = "codeFormation") final String codeFormation,@PathVariable(value = "codeUe") final String codeUe) {
		UniteEnseignementPK uePk = new UniteEnseignementPK(codeFormation,codeUe);
		UniteEnseignement ue = new UniteEnseignement(uePk);
		return ecService.getByuniteEnseignement(ue);
	}
	
	/**
	 *
	 * @return list de ec
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public final Iterable<ElementConstitutif> AllElementConstitutifs() {
		return ecService.getAllElementConstitutifs();
	}

	/**
	 *
	 * @param idElementConstitutif
	 *            l'id de ec
	 */
	@RequestMapping(value = "/{codeFormation}/{codeUe}/{codeEc}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public final void removeElementConstitutif(@PathVariable(value = "codeFormation") final String codeFormation,@PathVariable(value = "codeUe") final String codeue,@PathVariable(value = "codeEc") final String codeec) {
		ElementConstitutifPK ecPK = new ElementConstitutifPK(codeFormation, codeue,codeec);
		ecService.deleteElementConstitutif(ecPK);
	}
}
