package com.example.skidrow;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * This class will contain the players information -- information holder.
 * @author Michael Underwood
 * @version 0.1
 */

public class Player implements Serializable{
	
	private static final double SMALL_CRIT_MULT = .5;
	private static final double LARGE_CRIT_MULT = .5;
	private static final int SMALL_CRIT_PROB = 2;
	private static final int LARGE_CRIT_PROB = 3;
	private static final int TOTAL_PROB = 8;
	private String name;
	private Random rand;
	private int money;
	private int communicationSkills,fighterSkills, driverSkills, dealerSkills;
	private String difficultyLevel;
	private Map<String, Integer> goodsList = new HashMap<String, Integer>();
	private Ship car;
	private Gadget gadget;
	private double gas;
	private double health;
	private double attack;

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
		this.setDifficultyLevel(difficultyLevel);
		System.out.println("Name: " + name + " ComSkills: " + communicationSkills + 
				" FightSkills: " + fighterSkills + " DriveSkills: " + driverSkills
				+ " DealSkills: " + dealerSkills + " difficultyLevel: "+ difficultyLevel);
		this.money = 10000;
		this.car=new Ship("1977 Tokyo Sedan", 10, 1, 1, false, 0, 1, 1000, 5,80,10);
		setGas(car.getFuelCapacity());
		this.health = car.getArmour();
		this.attack = car.getGunDamage();
		
		initGoods();

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
	public void setMoney(int money){
		this.money=money;
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
		System.out.println("this is deltaMoney" + deltaMoney);
		System.out.println("this is player's money before buy: " + money);
		this.money = money - deltaMoney;
		System.out.println("this is player's money after buy: " + money);
		car.setAvailableCargoSpace(getCargoSpace() - quantity);
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
		car.setAvailableCargoSpace(getCargoSpace()+ quantity);
		System.out.println("Player is getting money back");
		
	}
	
	/**
	 * Gets the space of the cargo
	 * 
	 * @return the value of the cargo space
	 */
	public int getCargoSpace(){
		return car.getAvailableCargoSpace();
	}
	/**
	 * Gets the gas capacity of the ship
	 * @return the gas capacity of the ship
	 */
	 public double getGasCapacity(){
		 return car.getFuelCapacity();
	 }
	 /**
	  * Set the gas capacity of the ship
	  */
	 public void setGasCapacity(int gasCap){
		 car.setFuelCapacity(gasCap);
	 }
	 /**
	 * Gets the amount of gas left
	 * @return the gas capacity of the ship
	 */
	 public double getGas(){
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(this.gas));
	 }
	/**
	* Set the amount of gas left 
	*/
	public void setGas(double newGas){
		gas=newGas;
	} 
	
	
	
	/**
	 * This method returns the current value of fuel efficiency
	 * @return fuel efficiency 
	 */
	public double getFuelEfficiency(){
		return car.getFuelEfficiency();
	}
	public Ship getShip(){
		return car;
	}
	public void setShip(Ship ship){
		this.car=ship;
	}
	public void setGadget(Gadget gadget){
		car.setArmour(car.getArmour()+gadget.getArmour());
		car.setRespect(car.getRespect()+gadget.getRespect());
		car.setTurbo(car.getTurbo()+gadget.getTurbo());
		car.setFuelCapacity(car.getFuelCapacity()+gadget.getFuelCapacity());
		car.setGunDamage(car.getGunDamage()+gadget.getGunDamage());
		car.setSpeed(car.getSpeed()+gadget.getSpeed());
		car.setMaxCargoSpace(car.getMaxCargoSpace()+gadget.getMaxCargoSpace());
		car.setAvailableCargoSpace(car.getArmour()+gadget.getArmour());
	}
	
	public String toString(){
		return this.name;
	}
	  
	/**
	 * This gets the damage done by a small attack. It has a lower probability but has the same critical damage percentage as a
	 * large attack. 
	 * 
	 * @param test Used to get the average 
	 * @return Damage done by attack
	 */
	public double smallAttack(boolean test){
		//This returns the expected average damage for a small attack
		if(test){
			return this.attack*(SMALL_CRIT_MULT/(TOTAL_PROB-SMALL_CRIT_PROB));
		}
		
		int randNum = rand.nextInt(TOTAL_PROB);
		double damage = this.attack;
		if(randNum <= SMALL_CRIT_PROB){
			damage = damage * SMALL_CRIT_MULT;
		}
		
		return damage;
	}
	
	/**
	 * This gets the damage done by a large attack. It has a higher probability than a small attack. It takes two moves, however
	 * 
	 * @param test Used to get the average damage
	 * @return Damage done by attack
	 */
	public double largeAttack(boolean test){
		double damage = attack*2;
		if(test){
			return damage*(LARGE_CRIT_MULT/(TOTAL_PROB-LARGE_CRIT_PROB));
		}
		
		int randNum = rand.nextInt(TOTAL_PROB);
		if(randNum <= LARGE_CRIT_PROB){
			damage = damage * LARGE_CRIT_MULT;
		}
		
		return damage;
	}

	/**
	 * @return the health
	 */
	public double getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(double health) {
		this.health = health;
	}

	/**
	 * @return the difficultyLevel
	 */
	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	/**
	 * @param difficultyLevel the difficultyLevel to set
	 */
	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
}
