package fr.univbrest.dosi.spi.service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Iterables;

import fr.univbrest.dosi.spi.Application;
import fr.univbrest.dosi.spi.bean.Etudiant;
import fr.univbrest.dosi.spi.bean.Promotion;
import fr.univbrest.dosi.spi.bean.PromotionPK;
import fr.univbrest.dosi.spi.exception.SPIException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EtudiantServiceTest {

	@Autowired
	private EtudiantService etudiantService;
	
	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private FormationService formationService;
	/**
	 *
	 */
	private String noEtudiant;
	/**
	 *
	 */
	private String nom;
	/**
	 *
	 */
	private String prenom;
	
	@Test
	public void listeEtudiants() {
		
		final Iterable<Etudiant> listEtudiant = etudiantService.getEtudiantByPromotion(promotionService.getPromotion(new PromotionPK("M2DOSI","2014-2015")));
		Assert.assertNotNull(listEtudiant);
		Assert.assertEquals(10, Iterables.size(listEtudiant));
	}
	@Test
	public final void addEtudiant() {
	final Etudiant etudiant = new Etudiant();
	final Promotion promotion = new Promotion();
	final PromotionPK promotionPk = new PromotionPK("M2DOSI","2014-2015");
	promotion.setPromotionPK(promotionPk);
	promotion.setSiglePromotion("DOSI4");
	promotion.setNbMaxEtudiant(new Short("24"));
	etudiant.setNoEtudiant("21409999");
	etudiant.setAdresse("cite universitaire de kergoat");
	etudiant.setCodePostal("29000");
	etudiant.setEmail("eljarroudihaifa@gmail.com");
	Date date = new Date(01-01-2017);
	etudiant.setDateNaissance(date);
	etudiant.setLieuNaissance("Tanger");
	etudiant.setEmailUbo("eljarroudihaifa@uni-brest.fr");
	etudiant.setMobile("06.67.58.23.68");
	etudiant.setNationalite("marocaine");
	etudiant.setSexe("F");
	etudiant.setPaysOrigine("maroc");
	etudiant.setNom("EL JARROUDI");
	etudiant.setPrenom("haifa");
	etudiant.setUniversiteOrigine("fst");
	etudiant.setVille("Tanger");
	etudiant.setGroupeTp(new BigInteger("1"));
	etudiant.setGroupeAnglais(new BigInteger("2"));
	etudiant.setTelephone("06.67.58.23.68");
	etudiant.setUniversiteOrigine("fstt");
	etudiant.setPromotion(promotion);

	try {
		final Etudiant newEtudiant = etudiantService.addEtudiant(etudiant);
		Assert.assertNotNull(newEtudiant.getNoEtudiant());
		Assert.assertEquals(etudiant.getNoEtudiant(), newEtudiant.getNoEtudiant());
		// Assert.fail();
	} catch (final SPIException ex) {
		Assert.assertEquals("l'etudiant que vous souhaitez ajouter exsite déja ", ex.getMessage());
	}
	 
	}


	@Test
	public final void deleteEtudiant() {
		final String id = "21409999";
		try {
			etudiantService.deleteEtudiant(id);
		} catch (final SPIException ex) {
			Assert.assertEquals("Cant delete Etudiant", ex.getMessage());
		}
	}

	/**
	 *
	 */
	@Test
	public final void deleteEtudiantNotExist() {
		final String id = "21406123";
		try {
			etudiantService.deleteEtudiant(id);
			Assert.fail();
		} catch (final SPIException ex) {
			Assert.assertEquals("Cant delete Etudiant", ex.getMessage());
		}
	}


	/**
	 *
	 */
	

	/**
	 *
	 */
	@Before
	public final void init() {
		// this.business = new GreetingBusinessImpl();
		this.noEtudiant = "21406999";
		this.nom = "EL JARROUDI";
		this.prenom = "haifa";
	}


	@Test
	public void updateEtudiantExist() {
		final Etudiant etudiant = new Etudiant();
		final Promotion promotion = new Promotion();
		final PromotionPK promotionPk = new PromotionPK("M2DOSI","2014-2015");
		promotion.setPromotionPK(promotionPk);
		promotion.setSiglePromotion("DOSI6");
		promotion.setNbMaxEtudiant(new Short("24"));
		etudiant.setNoEtudiant("21406954");
		etudiant.setAdresse("2 rue des archives");
		etudiant.setCodePostal("29000");
		etudiant.setEmail("eljarroudihaifa@gmail.com");
		Date date = new Date(01-01-2017);
		etudiant.setDateNaissance(date);
		etudiant.setLieuNaissance("Marrakech");
		etudiant.setEmailUbo("eljarroudihaifa@uni-brest.fr");
		etudiant.setMobile("06.67.58.23.68");
		etudiant.setNationalite("marocaine");
		etudiant.setSexe("F");
		etudiant.setPaysOrigine("maroc");
		etudiant.setNom("EL JARROUDI");
		etudiant.setPrenom("haifa");
		etudiant.setUniversiteOrigine("fst");
		etudiant.setVille("Tanger");
		etudiant.setGroupeTp(new BigInteger("1"));
		etudiant.setGroupeAnglais(new BigInteger("2"));
		etudiant.setTelephone("06.67.58.23.68");
		etudiant.setUniversiteOrigine("fstt");
		etudiant.setPromotion(promotion);
		 
		try {
			final Etudiant newEtudiant = etudiantService.updateEtudiant(etudiant);
			Assert.assertNotNull(newEtudiant.getNoEtudiant());
			Assert.assertEquals(etudiant.getNom(), newEtudiant.getNom());
			// Assert.fail();
		} catch (final SPIException ex) {
			Assert.assertEquals("l'etudiant que vous souhaitez modifier n'exsite pas", ex.getMessage());
		}
	}

	@Test
	public final void updateEtudiantNotExist() {
		final Etudiant etudiant = new Etudiant();
		final Promotion promotion = new Promotion();
		final PromotionPK promotionPk = new PromotionPK("M2DOSI","2014-2015");
		promotion.setPromotionPK(promotionPk);
		promotion.setSiglePromotion("DOSI6");
		promotion.setNbMaxEtudiant(new Short("24"));
		etudiant.setNoEtudiant("21406955");
		etudiant.setAdresse("2 rue des archives");
		etudiant.setCodePostal("29000");
		etudiant.setEmail("eljarroudihaifa@gmail.com");
		Date date = new Date(01-01-2017);
		etudiant.setDateNaissance(date);
		etudiant.setLieuNaissance("Marrakech");
		etudiant.setEmailUbo("eljarroudihaifa@uni-brest.fr");
		etudiant.setMobile("06.67.58.23.68");
		etudiant.setNationalite("marocaine");
		etudiant.setSexe("F");
		etudiant.setPaysOrigine("maroc");
		etudiant.setNom("EL JARROUDI");
		etudiant.setPrenom("haifa");
		etudiant.setUniversiteOrigine("fst");
		etudiant.setVille("Tanger");
		etudiant.setGroupeTp(new BigInteger("1"));
		etudiant.setGroupeAnglais(new BigInteger("2"));
		etudiant.setTelephone("06.67.58.23.68");
		etudiant.setUniversiteOrigine("fstt");
		etudiant.setPromotion(promotion);
	 ;
		try {
			final Etudiant newEtudiant = etudiantService.updateEtudiant(etudiant);
			Assert.fail();
		} catch (final SPIException ex) {
			Assert.assertEquals("l'etudiant que vous souhaitez modifier n'exsite pas ", ex.getMessage());
		}
	
	}
}