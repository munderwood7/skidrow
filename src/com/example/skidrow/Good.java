package com.example.skidrow;

import java.util.Random;

/**
 * This class contains the logic for a "Trade Good"
 * @author Michael Underwood
 * @version 0.1
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
	protected int minimumTechLevelProduceResource;
	protected int minimumTechLeveltoUseResource;
	protected int quantity;
	protected String goodName;
	protected static Random randomGen = new Random();
	
	public Good(String goodName, int price, int minBuyTechLevel, int minSellTechLevel, int favorableTechLevel, int priceIncreasePerTechLevel, int maximumVariance, int minimumTechLevelProduceResource, int minimumTechLeveltoUseResource)
	{
		basePrice = price;
		this.goodName = goodName;
		this.minBuyTechLevel = minBuyTechLevel;
		this.minSellTechLevel = minSellTechLevel;
		this.favorableTechLevel = favorableTechLevel;
		this.priceIncreasePerTechLevel = priceIncreasePerTechLevel;
		this.maximumVariance = maximumVariance;
		this.minimumTechLevelProduceResource = minimumTechLevelProduceResource;
		this.minimumTechLeveltoUseResource = minimumTechLeveltoUseResource;
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
	
	abstract int getBasePrice(String event); //used if there is an event happening
	
	public int getBasePrice() //used if no event is happening
	{
		return basePrice;
	}
	
	public int getQuantity(){
		return quantity;//used to see how many of these the user or market has
	}
	
	public int getMinimumTechLevelProduceResource(){
		return minimumTechLevelProduceResource;
	}
	
	public int getMinimumTechLeveltoUseResource(){
		return minimumTechLevelProduceResource;
	}
	
	public String toString(){
		return goodName;
	}
	
	public void setBasePrice(int newBasePrice)
	{
		basePrice = newBasePrice;
	}
}
