package com.example.skidrow;

import java.util.HashMap;
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
	private Map goodsList = new HashMap<Good, Integer>();

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
		goodsList.put(new Adderall(), 0);
		goodsList.put(new HorseTranquilizer(), 0);
		goodsList.put(new Weed(), 0);
		goodsList.put(new Cocaine(), 0);
		goodsList.put(new Extacy(), 0);
		goodsList.put(new Heroin(), 0);
		goodsList.put(new Jenkem(), 0);
		goodsList.put(new LSD(), 0);
		goodsList.put(new PsychedelicMushroom(), 0);
	}
	
	/**
	 * Gets the players current goods list
	 * 
	 * @return Map of player goods and their quantity
	 */
	public Map<Good, Integer> getGoods(){
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
}
