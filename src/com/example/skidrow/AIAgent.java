package com.example.skidrow;

import java.util.Random;

public class AIAgent {
	private static final double SMALL_CRIT_MULT = .5;
	private static final double LARGE_CRIT_MULT = .5;
	private int SMALL_CRIT_PROB = 2;
	private int LARGE_CRIT_PROB = 3;
	private static final int TOTAL_PROB = 8;
	private static final int MAX_FORTIFY=5;
	
	private Random rand;
	private int aggression;
	private double attack;
	private int searchDepth;
	private double health;
	private double speed;
	
	public AIAgent(int aggression, int level, double attack, double health, double speed){
		this.aggression = aggression;
		this.rand = new Random();
		this.searchDepth = level;
		this.attack = attack;
		this.health = health;
		this.speed = speed;
	}
	
	/**
	 * This gets the damage done by a small attack. It has a lower probability but has the same critical damage percentage as a
	 * large attack. 
	 * 
	 * @param test Used to get the average 
	 * @return Damage done by attack
	 */
	public double smallAttack(boolean test, Player player){
		//This returns the expected average damage for a small attack
		if(test){
			return this.attack*(SMALL_CRIT_MULT/(TOTAL_PROB-SMALL_CRIT_PROB));
		}
		
		int randNum = rand.nextInt(TOTAL_PROB);
		double damage = this.attack;
		if(randNum <= SMALL_CRIT_PROB){
			damage = damage * SMALL_CRIT_MULT;
		}
		
		player.setHealth(player.getHealth()-damage);
		
		return damage;
	}
	
	/**
	 * This gets the damage done by a large attack. It has a higher probability than a small attack. It takes two moves, however
	 * 
	 * @param test Used to get the average damage
	 * @return Damage done by attack
	 */
	public double largeAttack(boolean test, Player player){
		double damage = attack*2;
		if(test){
			return damage*(LARGE_CRIT_MULT/(TOTAL_PROB-LARGE_CRIT_PROB));
		}
		
		int randNum = rand.nextInt(TOTAL_PROB);
		if(randNum <= LARGE_CRIT_PROB){
			damage = damage * LARGE_CRIT_MULT;
		}
		
		player.setHealth(player.getHealth()-damage);
		
		return damage;
	}
	
	public double getHealth(){
		return this.health;
	}
	
	public double getAttack(){
		return this.attack;
	}
	
	public double getSpeed(){
		return this.speed;
	}
	
	public void setHealth(double health){
		this.health = health;
	}
	
	public int fortifyAttack(boolean test){
		if(test)
			return SMALL_CRIT_PROB+(MAX_FORTIFY/2);
		
		int probability_increase = rand.nextInt(MAX_FORTIFY);
		if(SMALL_CRIT_PROB+probability_increase < TOTAL_PROB){
			SMALL_CRIT_PROB+=probability_increase;
			LARGE_CRIT_PROB+=probability_increase;
		}
		
		return 0;
	}
	
	public double[] getOrigingalFightVals(){
		return new double[]{this.health, this.attack, this.SMALL_CRIT_PROB, this.LARGE_CRIT_PROB};
	}
	
	public void setOriginalFightVals(boolean[] setVals, double[] vals){
		if(setVals[0])
			this.health = vals[0];
		if(setVals[1])
			this.attack = vals[1];
		if(setVals[2])
			this.SMALL_CRIT_PROB = (int)vals[2];
		if(setVals[3])
			this.LARGE_CRIT_PROB = (int)vals[3];
	}
}
