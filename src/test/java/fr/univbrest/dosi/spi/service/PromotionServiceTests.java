package fr.univbrest.dosi.spi.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Iterables;

import fr.univbrest.dosi.spi.Application;
import fr.univbrest.dosi.spi.bean.Promotion;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PromotionServiceTests {

	@Autowired
	private FormationService formationService;

	@Autowired
	private PromotionService promotionService;

	@Test
	public void listePromotions() {
		/*
		 * final Formation f = new Formation(); f.setCodeFormation("WVS");
		 * f.setDebutAccreditation(new Date);
		 */
		final List<Promotion> listPromotion = promotionService
				.getPromotionByFormation(formationService
						.getFormation("M2DOSI").getCodeFormation());
		Assert.assertNotNull(listPromotion);
		Assert.assertEquals(2, Iterables.size(listPromotion));
	}
}
