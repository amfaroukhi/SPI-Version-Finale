package fr.univbrest.dosi.spi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.UniteEnseignement;
import fr.univbrest.dosi.spi.bean.UniteEnseignementPK;
import fr.univbrest.dosi.spi.service.UniteEnseignementService;



/**
 * @author DOSI
 *
 */
@RestController
@RequestMapping("ue")
public class UniteEnseignementController {
	/**
	 *
	 */
	@Autowired
	private UniteEnseignementService ueService;

	/**
	 *
	 * @param ue
	 *            l'entité de ue
	 * @return une ue
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final UniteEnseignement ajouterUniteEnseignement(@RequestBody final UniteEnseignement ue) {
		return ueService.addUnitEnseignement(ue);
	}
	
	@RequestMapping(value = "/countUniteEnseignement")
    public final long countUniteEnseignement(){
   	 return ueService.countUniteEnseignement();
    }

	/**
	 *
	 * @param ue
	 *            l'entité de ue
	 * @return une ue
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, headers = "Accept=application/json")
	public final UniteEnseignement editUniteEnseignement(@RequestBody final UniteEnseignement ue) {
		return ueService.updateUnitEnseignement(ue);
	}

	/**
	 *
	 * @param idUniteEnseignement
	 *            l'id de ue
	 * @return une ue
	 */
	@RequestMapping(value = "/{codeFormation}/{codeUe}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final UniteEnseignement getue(@PathVariable(value = "codeFormation") final String codeFormation,@PathVariable(value = "codeUe") final String codeue) {
		UniteEnseignementPK uePK = new UniteEnseignementPK(codeFormation, codeue);
		return ueService.uniteEnseignement(uePK);
	}
	
	@RequestMapping(value = "/{CodeFormation}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Iterable<UniteEnseignement> getuebyFormation(@PathVariable(value = "CodeFormation") final String CodeFormation) {
		return ueService.getUEbyFormation(CodeFormation);
	}
	
	@RequestMapping(value = "getbyensandFormation/{noenseignant}/{CodeFormation}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Iterable<UniteEnseignement> getue(@PathVariable(value = "noenseignant") final Integer noEnseignant, @PathVariable(value = "CodeFormation") final String CodeFormation) {
		return  ueService.getUEByEnseignantandFormation(CodeFormation,noEnseignant);
	}
	
	@RequestMapping(value = "getbyens/{noenseignant}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Iterable<UniteEnseignement> getue(@PathVariable(value = "noenseignant") final Integer noEnseignant) {
		return  ueService.getUEByEnseignant(noEnseignant);
	}
	
	/**
	 *
	 * @return list de ue
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public final Iterable<UniteEnseignement> AllUniteEnseignements() {
		return ueService.getAllUniteEnseignements();
	}

	/**
	 *
	 * @param idUniteEnseignement
	 *            l'id de ue
	 */
	@RequestMapping(value = "/{codeFormation}/{codeUe}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public final void removeUniteEnseignement(@PathVariable(value = "codeFormation") final String codeFormation,@PathVariable(value = "codeUe") final String codeue) {
		UniteEnseignementPK uePK = new UniteEnseignementPK(codeFormation, codeue);
		ueService.deleteUnitEnseignement(uePK);
	}
}
