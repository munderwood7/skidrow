package com.example.skidrow;

import java.io.Serializable;

public class Ship implements Serializable{
	/**
	 * Represents a ship in the game
	 */
	private int armour;
	private int respect;
	private String shipName;
	private int hiddenStorage;
	private boolean plateChanger;
	private int turbo;
	private int gunDamage;
	private int fuelCapacity;
	private double fuelEfficiency;
	private int speed;
	private int maxCargoSpace;
	private int availableCargoSpace;
	
	public Ship(String shipName, int armour, int respect, int hiddenStorage, boolean plateChanger, int turbo, int gunDamage, int fuelCapacity, double fuelEfficiency, int speed, int cargoSpace){
		this.setShipName(shipName);
		this.setArmour(armour);
		this.setRespect(respect);
		this.setPlateChanger(plateChanger);
		this.setTurbo(turbo);
		this.setFuelCapacity(fuelCapacity);
		this.setGunDamage(gunDamage);
		this.setFuelEfficiency(fuelEfficiency);
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

	public int getHiddenStorage() {
		return hiddenStorage;
	}

	public void setHiddenStorage(int hiddenStorage) {
		this.hiddenStorage = hiddenStorage;
	}

	public boolean isPlateChanger() {
		return plateChanger;
	}

	public void setPlateChanger(boolean plateChanger) {
		this.plateChanger = plateChanger;
	}

	/**
	 * @return the shipName
	 */
	public String getShipName() {
		return shipName;
	}

	/**
	 * @param shipName the shipName to set
	 */
	public void setShipName(String shipName) {
		this.shipName = shipName;
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
	 * @return the fuelEfficieny
	 */
	public double getFuelEfficiency() {
		return fuelEfficiency;
	}

	/**
	 * @param fuelEfficieny the fuelEfficieny to set
	 */
	public void setFuelEfficiency(double fuelEfficiency) {
		this.fuelEfficiency = fuelEfficiency;
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
