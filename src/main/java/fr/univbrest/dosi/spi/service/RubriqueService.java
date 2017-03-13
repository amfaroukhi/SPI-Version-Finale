package fr.univbrest.dosi.spi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.Rubrique;
import fr.univbrest.dosi.spi.dao.RubriqueRepository;

/**
 * @author DOSI
 *
 */
@Service
public class RubriqueService {

	@Autowired
	private RubriqueRepository rubriqueRepository;

	public final Rubrique addRubrique(final Rubrique rub) {
		return rubriqueRepository.save(rub);
	}

	public final void deleteRubrique(final long idRubrique) {
		rubriqueRepository.delete(idRubrique);
	}

	public final Boolean existeRubrique(final long idRubrique) {
		return rubriqueRepository.exists(idRubrique);
	}

	public final Rubrique getRubrique(final long idRubrique) {
		return rubriqueRepository.findOne(idRubrique);
	}

	public final Iterable<Rubrique> listRubriques() {
		final Iterable<Rubrique> rubriques = rubriqueRepository.findAll();
		return rubriques;
	}

	public final Rubrique updateRubrique(final Rubrique rub) {

		return rubriqueRepository.save(rub);

	}

}
