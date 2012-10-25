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
	protected int minBuyTechLevel;
	protected int minSellTechLevel;
	protected int favorableTechLevel;
	protected int priceIncreasePerTechLevel;
	protected int maximumVariance;
	protected int basePriceDuringEvent;
	
	public Good(int price, int minBuyTechLevel, int minSellTechLevel, int favorableTechLevel, int priceIncreasePerTechLevel, int maximumVariance)
	{
		basePrice = price;
		this.minBuyTechLevel = minBuyTechLevel;
		this.minSellTechLevel = minSellTechLevel;
		this.favorableTechLevel = favorableTechLevel;
		this.priceIncreasePerTechLevel = priceIncreasePerTechLevel;
		this.maximumVariance = maximumVariance;
	}
	
	public int getMinBuyTechLevel()
	{
		return minBuyTechLevel;
	}
	
	public int getMinSellTechLevel()
	{
		return minSellTechLevel;
	}
	
	public int getFavorableTechLevel()
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
