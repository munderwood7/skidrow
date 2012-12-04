package com.example.skidrow;

public class Recruit {
	
	private int cumSkill;
	private int fightSkill;	
	private int driveSkill;
	private int dealSkill;
	
	public Recruit(int cumSkill, int fightSkill, int driveSkill, int dealSkilld){
		this.setCumSkill(cumSkill);
		this.setFightSkill( fightSkill);
		this.setDriveSkill( driveSkill);
		this.setDealSkill( dealSkill);
		
	}

	public int getCumSkill() {
		return cumSkill;
	}

	public void setCumSkill(int cumSkill) {
		this.cumSkill = cumSkill;
	}

	public int getFightSkill() {
		return fightSkill;
	}

	public void setFightSkill(int fightSkill) {
		this.fightSkill = fightSkill;
	}
	
	public int getDriveSkill() {
		return driveSkill;
	}

	public void setDriveSkill(int driveSkill) {
		this.driveSkill = driveSkill;
	}
	
	public int getDealSkill() {
		return dealSkill;
	}

	public void setDealSkill(int dealSkill) {
		this.dealSkill = dealSkill;
	}
	


	

	
}
