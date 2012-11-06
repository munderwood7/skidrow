package com.example.skidrow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class will contain the players information -- information holder.
 * @author Michael Underwood
 * @version 0.1
 */

public class Player {
	
	private String name;
	private int money;
	private int communicationSkills,fighterSkills, driverSkills, dealerSkills;
	private String difficultyLevel;
	private Map<String, Integer> goodsList = new HashMap<String, Integer>();
	private int cargoSpace=10;
	private double gasCapacity=100;
	private double gas;
	private double fuelEfficiency;

	/**
	 * Constructor for a player with a name and a set of skills
	 * @param name of player in string format
	 * @param communicationSkills int value of communication level 
	 * @param fighterSkills int value for fighting skills
	 * @param driverSkills int value for driving skills
	 * @param dealerSkills int value for dealer skills
	 * @param difficultyLevel 
	 */
	public Player(String name, int communicationSkills, int fighterSkills, int driverSkills, int dealerSkills, String  difficultyLevel)
	{
		this.name = name;
		this.communicationSkills = communicationSkills;
		this.fighterSkills = fighterSkills;
		this.driverSkills = driverSkills;
		this.dealerSkills = dealerSkills;
		this.difficultyLevel= difficultyLevel;
		System.out.println("Name: " + name + " ComSkills: " + communicationSkills + 
				" FightSkills: " + fighterSkills + " DriveSkills: " + driverSkills
				+ " DealSkills: " + dealerSkills + " difficultyLevel: "+ difficultyLevel);
		this.money = 10000;
		this.gas=gasCapacity;
		this.fuelEfficiency=1;
		
		initGoods();
		setCargoSpace();
	}
	
	/**
	 * Getter for player name.
	 * 
	 * @return player name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Getter for all player skills. Returns an array of the skills organized as follows:
	 * [0]=communicationSkills,[1]=fighterSkills,[2]=driverSkills.[3]=dealerSkills
	 * 
	 * @return Array of player skills
	 */
	public int[] getSkillsArray(){
		return new int[]{communicationSkills,fighterSkills, driverSkills, dealerSkills};
	}
	
	/**
	 * Creates a new goods list with all drugs starting at a quantity of 0
	 */
	private void initGoods(){
		goodsList.put("Adderall", 0);
		goodsList.put("HorseTranquilizer", 0);
		goodsList.put("Weed", 0);
		goodsList.put("Cocaine", 0);
		goodsList.put("Extacy", 0);
		goodsList.put("Heroin", 0);
		goodsList.put("Jenkem", 0);
		goodsList.put("LSD", 0);
		goodsList.put("PsychedelicMushroom", 0);
	}
	
	/**
	 * Gets the players current goods list
	 * 
	 * @return Map of player goods and their quantity
	 */
	public Map<String, Integer> getGoods(){
		return goodsList;
	}
	
	/**
	 * Gets the amount of money a player has
	 * 
	 * @return The amount of money the player has
	 */
	public int getMoney(){
		return money;
	}
	
	/**
	 * Buys a good from the player. This means the value of the goods is subtracted from the money and the quantity of the good is added to the goodsList
	 * 
	 * @param good Good to buy
	 * @param quantity Amount of good to be bought
	 * @param deltaMoney Total value of goods being bought
	 */
	public void buyGood(String good, int quantity, int deltaMoney){
		goodsList.put(good, goodsList.get(good)+quantity); 
		this.money = money - deltaMoney;
		this.cargoSpace = cargoSpace - quantity;
	}
	
	/**
	 * Sells a good from the player. This means the value of the goods is added to the money and the quantity of the good is subtracted from the goodsList
	 * 
	 * @param good Good to sell
	 * @param quantity Amount of good to be sold
	 * @param deltaMoney Total value of goods being sold
	 */
	public void sellMoney(String good, int quantity, int deltaMoney){
		goodsList.put(good, goodsList.get(good)-quantity);
		this.money = money + deltaMoney;
		this.cargoSpace = cargoSpace + quantity;
	}
	
	/**
	 * Sets the space of the cargo
	 * 
	 */
	public void setCargoSpace(){
		cargoSpace= 10;
		
	}
	/**
	 * Gets the space of the cargo
	 * 
	 * @return the value of the cargo space
	 */
	public int getCargoSpace(){
		return cargoSpace;
	}
	/**
	 * Gets the gas capacity of the ship
	 * @return the gas capacity of the ship
	 */
	 public double getGasCapacity(){
		 return gasCapacity;
	 }
	 /**
	  * Set the gas capacity of the ship
	  */
	 public void setGasCapacity(double gasCap){
		 gasCapacity=gasCap;
	 }
	 /**
	 * Gets the amount of gas left
	 * @return the gas capacity of the ship
	 */
	 public double getGas(){
		 return gas;
	 }
	/**
	* Set the amount of gas left 
	*/
	public void setGas(double newGas){
		gas=newGas;
	} 
	
	/**
	 * This method sets the efficiency of the fuel
	 * @param fe int fuel efficiency
	 */
	public void setFuelEfficiency(double fe){
		this.fuelEfficiency=fe;
	}
	
	/**
	 * This method returns the current value of fuel efficiency
	 * @return fuel efficiency 
	 */
	public double getFuelEfficiency(){
		return fuelEfficiency;
	}
	  
}
