package com.example.skidrow;

public class AIAgent {
	private String name;
	private boolean fightPossible;
	private boolean fleePossible;
	private boolean tradePossible;
	private int aggression;
	
	public AIAgent(String name, boolean fight, boolean flee, boolean trade, int aggression){
		this.name = name;
		this.fightPossible = fight;
		this.fleePossible = flee;
		this.tradePossible = trade;
		this.aggression = aggression;
	}
	
	
}
