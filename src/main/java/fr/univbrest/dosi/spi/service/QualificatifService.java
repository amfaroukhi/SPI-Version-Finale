package fr.univbrest.dosi.spi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.Qualificatif;
import fr.univbrest.dosi.spi.dao.QualificatifRepository;

/**
 * @author DOSI
 *
 */
@Service
public class QualificatifService {

	@Autowired
	private QualificatifRepository qualificatifRepository;

	public final Qualificatif addQualificatif(final Qualificatif qual) {
		return qualificatifRepository.save(qual);
	}

	public final void deleteQualificatif(final long idQualificatif) {
		qualificatifRepository.delete(idQualificatif);
	}

	public final Boolean existeQualificatif(final long idQualificatif) {
		return qualificatifRepository.exists(idQualificatif);
	}

	public final Qualificatif getQualificatif(final long idQualificatif) {
		return qualificatifRepository.findOne(idQualificatif);
	}

	public final Iterable<Qualificatif> listQualificatifs() {
		final Iterable<Qualificatif> qualificatifs = qualificatifRepository.findAll();
		return qualificatifs;
	}

	public final Qualificatif updateQualificatif(final Qualificatif qual) {

		return qualificatifRepository.save(qual);

	}

}
