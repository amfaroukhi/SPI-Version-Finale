package fr.univbrest.dosi.spi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.Etudiant;
import fr.univbrest.dosi.spi.bean.Promotion;
import fr.univbrest.dosi.spi.dao.EtudiantRepository;
import fr.univbrest.dosi.spi.exception.SPIException;

/**
 * @author DOSI
 *
 */
@Service
public class EtudiantService {

	@Autowired
	private EtudiantRepository etudiantRepository;

	public Etudiant addEtudiant(final Etudiant etudiant) {
		if (etudiantRepository.exists(etudiant.getNoEtudiant())) {
			throw new SPIException(
					"l'etudiant que vous souhaitez ajouter exsite déja ");
		}
		return etudiantRepository.save(etudiant);
	}

	public final void deleteEtudiant(final String noEtudiant) {
		etudiantRepository.delete(noEtudiant);
	}

	public final long countEtudiant() {
		return etudiantRepository.count();
	}

	public final Boolean existEtudiant(final String noEtudiant) {
		final Boolean exist = etudiantRepository.exists(noEtudiant);
		if (exist) {
			return exist;
		} else {
			throw new SPIException("Il y a aucun etudiant avec ce numero");
		}
	}

	public final Etudiant getEtudiant(final String noEtudiant) {
		return etudiantRepository.findOne(noEtudiant);
	}

	public final List<Etudiant> getEtudiantByPromotion(final Promotion promotion) {
		return etudiantRepository.findByPromotion(promotion);
	}

	public final Iterable<Etudiant> listEtudiants() {
		final Iterable<Etudiant> etudiants = etudiantRepository.findAll();
		return etudiants;
	}

<<<<<<< HEAD
	public final void deleteEtudiant(final String id) {
		if (etudiantRepository.exists(id)) {
=======

	public final void deletEtudiant(final String id) {
		if (etudiantRepository.findOne(id) != null) {
>>>>>>> 55328d69684b4f7075d36c176da918861b7b7beb
			etudiantRepository.delete(id);
		} else {
			throw new SPIException("Cant delete Etudiant");
		}

	}

	public final List<Etudiant> getEtudiantByNom(final String nom) {
		return etudiantRepository.findByNom(nom);
	}

	/**
	 *
	 * @return getter
	 */
	public final EtudiantRepository getEtudiantRepository() {
		return etudiantRepository;
	}

	/**
	 *
	 * @return liste des etudiants
	 */
	public final Iterable<Etudiant> listens() {
		final Iterable<Etudiant> etudiants = etudiantRepository.findAll();
		return etudiants;
	}

	/**
	 *
	 * @param etudiantRepository
	 *            setter
	 */
	public final void setEtudiantRepository(
			final EtudiantRepository etudiantRepository) {
		this.etudiantRepository = etudiantRepository;
	}

	/**
	 *
	 * @param etudiant
	 *            l'entité etudiant
	 * @return etudiant modifier
	 */
	public final Etudiant updateEtudiant(final Etudiant etudiant) {
		if (etudiantRepository.exists(etudiant.getNoEtudiant())) {
			return etudiantRepository.save(etudiant);
		} else {
			throw new SPIException(
					"l'etudiant que vous souhaitez modifier n'exsite pas ");
		}
	}

	public final Promotion findPromotionByNoEtudiant(final String noEtudiant) {
		return etudiantRepository.findPromotionByNoEtudiant(noEtudiant);

	}
}
