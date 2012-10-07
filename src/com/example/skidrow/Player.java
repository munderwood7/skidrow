package com.example.skidrow;

public class Player {
	
	private String name;
	private int communicationSkills,fighterSkills, driverSkills, dealerSkills;

	public Player(String name, int communicationSkills, int fighterSkills, int driverSkills, int dealerSkills)
	{
		this.name = name;
		this.communicationSkills = communicationSkills;
		this.fighterSkills = fighterSkills;
		this.driverSkills = driverSkills;
		this.dealerSkills = dealerSkills;
		System.out.println("Name: " + name + " ComSkills: " + communicationSkills + 
				" FightSkills: " + fighterSkills + " DriveSkills: " + driverSkills
				+ " DealSkills: " + dealerSkills);
	}
	

}
