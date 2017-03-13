package fr.univbrest.dosi.spi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.Enseignant;
import fr.univbrest.dosi.spi.dao.EnseignantRepository;
import fr.univbrest.dosi.spi.exception.SPIException;

/**
 * @author DOSI
 *
 */
@Service
public class EnseignantService {
	/**
	 *
	 */
	@Autowired
	private EnseignantRepository enseignantRepository;

	/**
	 *
	 * @param enseignant
	 *            l'entité
	 * @return l'enseignant ajouter
	 */
	public  Enseignant addEnseignant(final Enseignant enseignant) {
		if (enseignantRepository.exists(enseignant.getNoEnseignant())) {
			throw new SPIException("l'enseignant que vous souhaitez ajouter exsite déja ");
		}
		return enseignantRepository.save(enseignant);
	}

	/**
	 *
	 * @param noEnseignant
	 *            l'id de l'enseignant
	 */
	public final void deleteEnseignant(final Integer noEnseignant) {

		if (enseignantRepository.exists(noEnseignant)) {
			enseignantRepository.delete(noEnseignant);
		} else {
			throw new SPIException("Cant delete Enseignant");
		}

	}

	/**
	 *
	 * @param noEnseignant
	 *            l'id de l'enseignant
	 * @return test de existe
	 */
	public final Boolean existEnseignant(final Integer noEnseignant) {
		final Boolean exist = enseignantRepository.exists(noEnseignant);
		if (exist) {
			return exist;
		} else {
			throw new SPIException("Il y a aucun enseignant avec ce numero");
		}
	}

	/**
	 *
	 * @param id
	 *            l'id de l'enseignant
	 * @return l'enseignant
	 */
	public final Enseignant getEnseignant(final Integer noEnseignant) {
		return enseignantRepository.findOne(noEnseignant);
	}

	/**
	 *
	 * @param nom
	 *            de l'enseignant
	 * @return la liste des enseignants
	 */
	public final List<Enseignant> getEnseignantByNom(final String nom) {
		return enseignantRepository.findByNom(nom);
	}

	/**
	 *
	 * @return getter
	 */
	public final EnseignantRepository getEnseignantRepository() {
		return enseignantRepository;
	}

	/**
	 *
	 * @return liste des enseignant
	 */
	public final Iterable<Enseignant> listens() {
		return enseignantRepository.findAll();
	}

	/**
	 *
	 * @param enseignantRepository
	 *            setter
	 */
	public final void setEnseignantRepository(final EnseignantRepository enseignantRepository) {
		this.enseignantRepository = enseignantRepository;
	}

	/**
	 *
	 * @param enseignant
	 *            l'entité enseignant
	 * @return enseignat modifier
	 */
	public final Enseignant updateEnseignant(final Enseignant enseignant) {
		if (enseignantRepository.exists(enseignant.getNoEnseignant())) {
			return enseignantRepository.save(enseignant);
		} else {
			throw new SPIException("l'enseignant que vous souhaitez modifier n'exsite pas ");
		}
	}

}
