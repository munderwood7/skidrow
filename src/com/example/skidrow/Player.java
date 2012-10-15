package com.example.skidrow;

/**
 * This class will contain the players information -- information holder.
 * @author Michael Underwood
 * @version 0.1
 */

public class Player {
	
	private String name;
	private int communicationSkills,fighterSkills, driverSkills, dealerSkills;
	private String difficultyLevel;

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
}
