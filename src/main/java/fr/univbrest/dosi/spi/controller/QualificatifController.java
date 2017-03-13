package fr.univbrest.dosi.spi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Qualificatif;
import fr.univbrest.dosi.spi.service.QualificatifService;



/**
 * @author DOSI
 *
 */
@RestController
@RequestMapping("qualificatif")
public class QualificatifController {
	/**
	 *
	 */
	@Autowired
	private QualificatifService qualificatifService;

	/**
	 *
	 * @param qualificatif
	 *            l'entité de qualificatif
	 * @return une qualificatif
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Qualificatif ajouterQualificatif(@RequestBody final Qualificatif qual) {
		return qualificatifService.addQualificatif(qual);
	}

	/**
	 *
	 * @param qualificatif
	 *            l'entité de qualificatif
	 * @return une qualificatif
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, headers = "Accept=application/json")
	public final Qualificatif editQualificatif(@RequestBody final Qualificatif qual) {
		return qualificatifService.updateQualificatif(qual);
	}

	/**
	 *
	 * @param idQualificatif
	 *            l'id de qualificatif
	 * @return une qualificatif
	 */
	@RequestMapping(value = "/{idQualificatif}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final Qualificatif getqualificatif(@PathVariable(value = "idQualificatif") final long idQualificatif) {
		return qualificatifService.getQualificatif(idQualificatif);

	}

	
	/**
	 *
	 * @return list de qualificatif
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public final Iterable<Qualificatif> AllQualificatifs() {

		return qualificatifService.listQualificatifs();

	}

	/**
	 *
	 * @param idQualificatif
	 *            l'id de qualificatif
	 */
	@RequestMapping(value = "/{idQualificatif}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public final void removeQualificatif(@PathVariable("idQualificatif") final long idQualificatif) {
		qualificatifService.deleteQualificatif(idQualificatif);
	}
}
