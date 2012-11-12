package com.example.skidrow;

import java.io.Serializable;
import java.util.Random;

/**
 * This class contains the logic for a "Trade Good"
 * @author Michael Underwood
 * @version 0.1
 */

public abstract class Good implements Serializable
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
	
	/**
	 * This is the cunstructor for a Good and it initializes its attributes with given arguments
	 * @param goodName string goods name
	 * @param price goods price
	 * @param minBuyTechLevel minimum tech level that good can be purchased by
	 * @param minSellTechLevel minimum tech level that good can be sold to
	 * @param favorableTechLevel
	 * @param priceIncreasePerTechLevel amount of price increment for given techlevel 
	 * @param maximumVariance maximum price fluctuation 
	 * @param minimumTechLevelProduceResource minimum tech level that produces good
	 * @param minimumTechLeveltoUseResource minimum level that uses resource
	 */
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
	
	/**
	 * This method returns good's minimum tech level that it can be bought by
	 * @return minimum purchase level
	 */
	public int getMinBuyTechLevel()
	{
		return minBuyTechLevel;
	}
	
	/**
	 * This method return minimum tech level that good can be sold to
	 * @return minimum sell to level
	 */
	public int getMinSellTechLevel()
	{
		return minSellTechLevel;
	}
	/**
	 * This method return the tech level for buying and selling preference 
	 * @return facorable techlevel to be sold and buy 
	 */
	public int getFavorableTechLevel()
	{
		return favorableTechLevel;
	}
	
	/**
	 * This method returns price of good increase by a techlevel
	 * @return price increase per techlevel
	 */
	public int getPriceIncreasePerTechLevel()
	{
		return priceIncreasePerTechLevel;
	}
	
	/**
	 * This method returns maximum good's price fluctuation
	 * @return int value of maximum price variance
	 */
	public int getMaxVariance()
	{
		return maximumVariance;
	}
	
	/**
	 * This method is used in case event is happening to get base price information of good
	 * @param event current event
	 * @return calculated baseprice at the given event occurrence 
	 */
	abstract int getBasePrice(String event); //used if there is an event happening
	
	/**
	 * This method returns baseprice of the good. used when not event is happening
	 * @return ing amount of good's base price
	 */
	public int getBasePrice() //used if no event is happening
	{
		return basePrice;
	}
	
	/**
	 * This method is used to see how many of these the user or market has
	 * @return current quantity of good
	 */
	public int getQuantity(){
		return quantity;
	}
	
	/**
	 * This method returns mi
	 * @return
	 */
	public int getMinimumTechLevelProduceResource(){
		return minimumTechLevelProduceResource;
	}
	
	/**
	 * This method returns minimum level that uses good
	 * @return int minimum tech level to use resource
	 */
	public int getMinimumTechLeveltoUseResource(){
		return minimumTechLeveltoUseResource;
	}
	
	/**
	 * string for good returns good name
	 */
	public String toString(){
		return goodName;
	}
	
	/**
	 * This method sets the base price of good the given value
	 * @param newBasePrice int new base price
	 */
	public void setBasePrice(int newBasePrice)
	{
		basePrice = newBasePrice;
	}
}
