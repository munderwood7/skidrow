package com.example.skidrow.test;

import com.example.skidrow.*;
import junit.framework.Assert;
import android.test.AndroidTestCase;

/**
 * This JUnit Test class tests the functionality/pricing of a Heroin object.
 * @author Michael Underwood
 * @version 11/19/2012
 */

public class HeroinPriceTest extends AndroidTestCase {
	
	/**
	 * Tests the Heroin's base price.
	 * @throws Throwable
	 */
	

	public void testBasePrice() throws Throwable {
		Assert.assertTrue(new Heroin().getBasePrice() > 0);
	}
	
	/**
	 * Tests that the Heroin's base price is less than the War1 price.
	 * @throws Throwable
	 */

	public void testBasePriceLessThanWarPrice() throws Throwable {
	    Assert.assertTrue(new Heroin().getBasePrice() <= new Heroin().getBasePrice("WAR1"));
	}
	
	/**
	 * Tests that the Heroin's base price is less than the MineralRich1 price.
	 * @throws Throwable
	 */
	
	public void testBasePriceLessThanMineralRichPrice() throws Throwable {
		Assert.assertTrue(new Heroin().getBasePrice() <= new Heroin().getBasePrice("MINERALRICH1"));
	}
	
	/**
	 * Tests that the Heroin's base price is greater than the MineralPoor1 price.
	 * @throws Throwable
	 */

	public void testBasePriceGreaterThanMineralPoorPrice() throws Throwable {
	    Assert.assertTrue(new Heroin().getBasePrice() >= new Heroin().getBasePrice("MINERALPOOR1"));
	}
	
	/**
	 * Tests the Heroin's base price with a non-impacting event.
	 * @throws Throwable
	 */
	
	public void testBasePriceDuringNonAffectingEvent() throws Throwable {
		Assert.assertTrue(Math.abs(new Heroin().getBasePrice() - new Heroin().getBasePrice("BOREDOM")) <= 25);
	}
	
	/**
	 * Tests the Heroin's minimum technology sell level.
	 * @throws Throwable
	 */

	public void testMinSellTechLevel() throws Throwable {
	    Assert.assertTrue(new Heroin().getMinSellTechLevel()  == 3);
	}
	
	/**
	 * Tests the Heroin's minimum buy technology level.
	 * @throws Throwable
	 */
	
	public void testMinBuyTechLevel() throws Throwable {
	    Assert.assertTrue(new Heroin().getMinBuyTechLevel()  == 4);
	}
	
	/**
	 * Tests the Heroin's favorable tech level.
	 * @throws Throwable
	 */
	
	public void testFavorableTechLevel() throws Throwable {
	    Assert.assertTrue(new Heroin().getFavorableTechLevel()  == 5);
	}
	
	/**
	 * Tests the Heroin's base price with regard to its price increase per level.
	 * @throws Throwable
	 */
	
	public void testPriceIncreasePerTechLevel() throws Throwable {
	    Assert.assertTrue(new Heroin().getPriceIncreasePerTechLevel()  == -30);
	}
	

}
