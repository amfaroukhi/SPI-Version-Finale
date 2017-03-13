package fr.univbrest.dosi.spi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Rubrique;
import fr.univbrest.dosi.spi.service.RubriqueService;



/**
 * @author DOSI
 *
 */
@RestController
@RequestMapping("rubrique")
public class RubriqueController {
	/**
	 *
	 */
	@Autowired
	private RubriqueService rubriqueService;

	/**
	 *
	 * @param rubrique
	 *            l'entité de rubrique
	 * @return une rubrique
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Rubrique ajouterRubrique(@RequestBody final Rubrique qual) {
		return rubriqueService.addRubrique(qual);
	}

	/**
	 *
	 * @param rubrique
	 *            l'entité de rubrique
	 * @return une rubrique
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, headers = "Accept=application/json")
	public final Rubrique editRubrique(@RequestBody final Rubrique qual) {
		return rubriqueService.updateRubrique(qual);
	}

	/**
	 *
	 * @param idRubrique
	 *            l'id de rubrique
	 * @return une rubrique
	 */
	@RequestMapping(value = "/{idRubrique}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Rubrique getrubrique(@PathVariable(value = "idRubrique") final long idRubrique) {
		return rubriqueService.getRubrique(idRubrique);

	}

	
	/**
	 *
	 * @return list de rubrique
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public final Iterable<Rubrique> AllRubriques() {

		return rubriqueService.listRubriques();

	}

	/**
	 *
	 * @param idRubrique
	 *            l'id de rubrique
	 */
	@RequestMapping(value = "/{idRubrique}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public final void removeRubrique(@PathVariable("idRubrique") final long idRubrique) {
		rubriqueService.deleteRubrique(idRubrique);
	}
}
