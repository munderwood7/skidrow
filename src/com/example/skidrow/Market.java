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
public class Market {
	private Map goodsList = new HashMap<Good, Integer>();
	private int techLevel;
	private Event event;
	private Random random;
	
	/**
	 * Constructor to create a market
	 * 
	 * @param techLevel Tech level of the city
	 * @param event	An event that has occurred on player movement. Null if no event was generated
	 */
	public Market(int techLevel, Event event){
		this.techLevel = techLevel;
		this.event = event;
		random = new Random();
		
		generateGoods();
	}
	
	/**
	 * This method fills the number of each good available at a given market.
	 */
	private void generateGoods(){
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
		for(int x=0; x<goodsList.size(); x+=1){
			Map.Entry<Good, Integer> entry = (Map.Entry<Good, Integer>)iterator.next();
			if(techLevel >= entry.getKey().getMinBuyTechLevel()){
				int quantityMultiplier = 1;
				if(techLevel == entry.getKey().getFavorableTechLevel()){
					quantityMultiplier = random.nextInt(2)+2;
				}				
				int numberItems = (random.nextInt(10)+1) * quantityMultiplier;
				goodsList.put(entry.getKey(), numberItems);		
			}
		}
	}
	
	/**
	 * Returns the list of goods and how many of each good a market has.
	 * 
	 * @return Goods and their quantity
	 */
	public Map<Good, Integer> getGoods(){
		return goodsList;
	}
}
