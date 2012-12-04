package com.example.skidrow;

public class Gadget {
	/**
	 * Represents a ship in the game
	 */
	private int armour;
	private int respect;
	private String gadgetName;
	
	private int turbo;
	private int gunDamage;
	private int fuelCapacity;
	
	private int speed;
	private int maxCargoSpace;
	private int availableCargoSpace;
	
	public Gadget(String gadgetName, int armour, int respect, int turbo, int gunDamage, int fuelCapacity,int speed, int cargoSpace){
		this.setGadgetName(gadgetName);
		this.setArmour(armour);
		this.setRespect(respect);
		this.setTurbo(turbo);
		this.setFuelCapacity(fuelCapacity);
		this.setGunDamage(gunDamage);	
		this.setSpeed(speed);
		this.setMaxCargoSpace(cargoSpace);
		this.setAvailableCargoSpace(cargoSpace);
	}

	public int getArmour() {
		return armour;
	}

	public void setArmour(int armour) {
		this.armour = armour;
	}

	public int getRespect() {
		return respect;
	}

	public void setRespect(int respect) {
		this.respect = respect;
	}

	
	

	/**
	 * @return the GadgetName
	 */
	public String getGadgetName() {
		return gadgetName;
	}

	/**
	 * @param GadgetName the gadgetName to set
	 */
	public void setGadgetName(String gadgetName) {
		this.gadgetName = gadgetName;
	}

	/**
	 * @return the turbo
	 */
	public int getTurbo() {
		return turbo;
	}

	/**
	 * @param turbo the turbo to set
	 */
	public void setTurbo(int turbo) {
		this.turbo = turbo;
	}

	/**
	 * @return the fuelCapacity
	 */
	public int getFuelCapacity() {
		return fuelCapacity;
	}

	/**
	 * @param fuelCapacity the fuelCapacity to set
	 */
	public void setFuelCapacity(int fuelCapacity) {
		this.fuelCapacity = fuelCapacity;
	}

	/**
	 * @return the gunDamage
	 */
	public int getGunDamage() {
		return gunDamage;
	}

	/**
	 * @param gunDamage the gunDamage to set
	 */
	public void setGunDamage(int gunDamage) {
		this.gunDamage = gunDamage;
	}


	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the maxCargoSpace
	 */
	public int getMaxCargoSpace() {
		return maxCargoSpace;
	}

	/**
	 * @param maxCargoSpace the maxCargoSpace to set
	 */
	public void setMaxCargoSpace(int maxCargoSpace) {
		this.maxCargoSpace = maxCargoSpace;
	}

	/**
	 * @return the availableCargoSpace
	 */
	public int getAvailableCargoSpace() {
		return availableCargoSpace;
	}

	/**
	 * @param availableCargoSpace the availableCargoSpace to set
	 */
	public void setAvailableCargoSpace(int availableCargoSpace) {
		this.availableCargoSpace = availableCargoSpace;
	}
	
}
