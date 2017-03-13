package fr.univbrest.dosi.spi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.CGRC;
import fr.univbrest.dosi.spi.dao.CGRCRepository;

/**
 * @author DOSI
 *
 */
@Service
public class CGRCService {

	@Autowired
	private CGRCRepository cgrcRepository;


	public final CGRC getCGRC(final long idCGRC) {
		return cgrcRepository.findOne(idCGRC);
	}

	public final Iterable<CGRC> listCGRCs() {
		final Iterable<CGRC> domaines = cgrcRepository.findAll();
		return domaines;
	}
	
	public final Iterable<CGRC> getByDomain(String rvDomain) {
		final Iterable<CGRC> domaines = cgrcRepository.findByrvDomain(rvDomain);
		return domaines;
	}

}
