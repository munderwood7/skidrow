package com.example.skidrow;

/**
 * This class contains the logic for a "Trade Good"
 * @author Michael Underwood
 * @version 0.1
 *
 */

public abstract class Good 
{
	protected int basePrice;
	protected String minBuyTechLevel;
	protected String minSellTechLevel;
	protected String favorableTechLevel;
	protected int priceIncreasePerTechLevel;
	protected int maximumVariance;
	protected int basePriceDuringEvent;
	
	public Good(int price)
	{
		basePrice = price;
	}
	
	public String getMinBuyTechLevel()
	{
		return minBuyTechLevel;
	}
	
	public String getMinSellTechLevel()
	{
		return minSellTechLevel;
	}
	
	public String getFavorableTechLevel()
	{
		return favorableTechLevel;
	}
	
	public int getPriceIncreasePerTechLevel()
	{
		return priceIncreasePerTechLevel;
	}
	
	public int getMaxVariance()
	{
		return maximumVariance;
	}
	
	abstract int getBasePriceDuringEvent(String event);
	
	public int getBasePrice()
	{
		return basePrice;
	}
	
}
