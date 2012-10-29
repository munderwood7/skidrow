package com.example.skidrow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * This class contains all of the logic and information for the market.
 * 
 * @author Clayton
 *
 */
public class Market 
{
	private Map goodsList = new HashMap<Good, Integer>();
	private int techLevel;
	private Event event;
	private Random random;
	private int money;
	
	/**
	 * Constructor to create a market
	 * 
	 * @param techLevel Tech level of the city
	 * @param event	An event that has occurred on player movement. Null if no event was generated
	 */
	public Market(int techLevel)
	{
		this.techLevel = techLevel;
		//this.event = event;
		random = new Random();
		
		generateGoods();
		
		money = random.nextInt(100)*techLevel;
	}
	
	/**
	 * This method fills the number of each good available at a given market.
	 */
	private void generateGoods(){
		goodsList.put(new Adderall(), 0);
		goodsList.put(new HorseTranquilizer(), 0);
		goodsList.put(new Weed(), 0);
		goodsList.put(new Cocaine(), 0);
		goodsList.put(new Extacy(), 0);
		goodsList.put(new Heroin(), 0);
		goodsList.put(new Jenkem(), 0);
		goodsList.put(new LSD(), 0);
		goodsList.put(new PsychedelicMushroom(), 0);
		
		/*
		 * Code sequence which generates the quantity of each good.
		 * The equation is as follows: rand(1-10)*multiplier. 
		 * multiplier is based on whether the techLevel == favorable tech level
		 */
		Iterator iterator = goodsList.entrySet().iterator();
		for(int x=0; x<goodsList.size(); x+=1)
		{
			Map.Entry<Good, Integer> entry = (Map.Entry<Good, Integer>)iterator.next();
			if(techLevel >= entry.getKey().getMinBuyTechLevel())
			{
				int quantityMultiplier = 1;
				if(techLevel == entry.getKey().getFavorableTechLevel())
				{
					quantityMultiplier = random.nextInt(2)+2;
				}				
				int numberItems = (random.nextInt(20)+1) * quantityMultiplier;
				goodsList.put(entry.getKey(), numberItems);		
			}
		}
	}
	
	/**
	 * Returns the list of goods and how many of each good a market has.
	 * 
	 * @return Goods and their quantity
	 */
	public Map<Good, Integer> getGoods()
	{
		return goodsList;
	}
	
	/**
	 * This method check if city market fulfills requirements for use of good and enough money to buy it. 
	 * If it passes the check corresponding changes are done to the inventory of the market (increase 
	 * queantity of this good).
	 * @param g good to by bought
	 * @return true if city market had required tech level for use of good and enough money to buy it, else return false.
	 */
	public boolean buyGood(Good g, Event e)
	{
		if(g.minimumTechLeveltoUseResource<=techLevel)
		{
			int goodPrice = getPrice(g, e);
			if (g.basePrice<=money)
			{
				money-=goodPrice;
				Iterator iterator = goodsList.entrySet().iterator();
				for(int x=0; x<goodsList.size(); x++)
				{
					Map.Entry<Good, Integer> entry = (Map.Entry<Good, Integer>)iterator.next();
					if(entry.getKey().equals(g))
					{
						entry.setValue(entry.getValue()+1);
						System.out.println("City has just bought " + g + ".");
						return true;
					}
				}
			}
		}
		System.out.println("City has not enough money to buy " + g + ".");
		return false;
	}
	
	/**
	 * This method reduces the quantity of a certain good in the market and increases the market money based on how much the buyer payed for the good
	 * @param g
	 * @return true or false depending if correct reduction was possible from quantity of the market good
	 */
	public boolean sellGood(Good g, int purchasePrice)
	{
		Iterator iterator = goodsList.entrySet().iterator();
		for(int x=0; x<goodsList.size(); x++)
		{
			Map.Entry<Good, Integer> entry = (Map.Entry<Good, Integer>)iterator.next();
			if(entry.getKey().equals(g))
			{
				entry.setValue(entry.getValue()-1);
				System.out.println("City has just bought " + g + ".");
				money+=purchasePrice;
				return true;
			}
		}
		return true;
	}
	
	/**
	 * These method calculates the price of a good base on current events and information about the city (it can decrease on increase relative to the base price) 
	 * @param g
	 * @return new price of good base on events and city information
	 */
	public int getPrice(Good g, Event e)
	{
		int currBasePrice = g.getBasePrice();
		int newBasePrice = currBasePrice + event.getPriceEffect();  //increase or decrease price of good depending on the event
		g.setBasePrice(newBasePrice);
		int priceIncXTechLevel = g.getPriceIncreasePerTechLevel();
		int MTLP = g.getMinimumTechLevelProduceResource();
		int variance = g.getMaxVariance();		

		return newBasePrice+(priceIncXTechLevel*(techLevel-MTLP)+variance);
	}

}
