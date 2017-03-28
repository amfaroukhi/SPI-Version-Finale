package fr.univbrest.dosi.spi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.UniteEnseignement;
import fr.univbrest.dosi.spi.bean.UniteEnseignementPK;
import fr.univbrest.dosi.spi.dao.UniteEnseignementRepository;

/**
 * @author DOSI
 *
 */
@Service
public class UniteEnseignementService {

	@Autowired
	private UniteEnseignementRepository uniteEnseignementRepository;

	public UniteEnseignement addUnitEnseignement(final UniteEnseignement uniteEnseignement) {
		return uniteEnseignementRepository.save(uniteEnseignement);
	}
	
	public UniteEnseignement updateUnitEnseignement(final UniteEnseignement uniteEnseignement) {
		return uniteEnseignementRepository.save(uniteEnseignement);
	}

	public final void deleteUnitEnseignement(final UniteEnseignementPK uniteEnseignementPK) {
		uniteEnseignementRepository.delete(uniteEnseignementPK);
	}

	public Boolean existUnitEnseignement(final UniteEnseignementPK uniteEnseignementPK) {
		return uniteEnseignementRepository.exists(uniteEnseignementPK);
	}

	public final List<UniteEnseignement> getUEByEnseignant(final Integer noEnseignant) {

		return uniteEnseignementRepository.findByNoEnseignant(noEnseignant);

	}
	
	public final long countUniteEnseignement() {
		return uniteEnseignementRepository.count();
	}

	public final UniteEnseignement uniteEnseignement(final UniteEnseignementPK uniteEnseignementPK) {
		return uniteEnseignementRepository.findOne(uniteEnseignementPK);
	}
	
	public final Iterable<UniteEnseignement> getAllUniteEnseignements() {
		return uniteEnseignementRepository.findAll();
	}
	
	public final Iterable<UniteEnseignement> getUEbyFormation(String CodeFormation) {
		return uniteEnseignementRepository.findByCodeFormation(CodeFormation);
	}
	
	public final Iterable<UniteEnseignement> getUEByEnseignantandFormation(String CodeFormation, Integer noEnseignant) {
		return uniteEnseignementRepository.findByEnseignantandFormation(CodeFormation,noEnseignant);
	}
}
