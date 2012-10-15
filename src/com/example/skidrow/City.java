package com.example.skidrow;

/**
 * It assigns the properties of cities 
 * 
 * @author Waleed
 * @version 1.0
 */
public class City {
	private String Name= " ";
	
	private int TechLevel = 0;
	
	public City(String CityName){
		Name = CityName;
	}
	
	/**
	 * Getter for the name of the city]
	 * 
	 * @return The name of the city
	 */
	public String getName(){
		return Name;
	}
	
	/**
	 * Getter for the tech level of the city
	 * 
	 * @return Tech level of the city
	 */
	public int getTechLevel(){
		return TechLevel;
	}
}
