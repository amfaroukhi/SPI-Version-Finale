package fr.univbrest.dosi.spi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.CGRC;
import fr.univbrest.dosi.spi.service.CGRCService;



/**
 * @author DOSI
 *
 */
@RestController
@RequestMapping("domain")
public class CGRCController {

	@Autowired
	private CGRCService cgrcService;


	@RequestMapping(value = "/{iddomaine}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public final CGRC getquestion(@PathVariable(value = "iddomaine") final long idCGRC) {
		return cgrcService.getCGRC(idCGRC);

	}
	
	@RequestMapping(value = "getBydomain/{domain}", method = RequestMethod.GET, produces = "application/json")
	public final Iterable<CGRC> getByDomain(@PathVariable(value = "domain") final String domain) {

		return cgrcService.getByDomain(domain);

	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public final Iterable<CGRC> AllDomaines() {

		return cgrcService.listCGRCs();

	}


}
