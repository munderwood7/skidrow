package com.example.skidrow;
import java.util.Random;

/**
 * It assigns the properties of cities 
 * 
 * @author Waleed
 * @version 1.0
 */
public class City {
	
	private String Name= " ";

	
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
	 * Generates a random number between 0 and 7 and based on that we assign the associated Tech Level
	 * 
	 * @return Tech level of the city
	 */
	public String getTechLevel(){
		Random rand = new Random();
		String TechLevel=" ";
		int randTechLevel = rand.nextInt(8);
		
		switch(randTechLevel){
			case 0:  TechLevel = "Pre-Agriculture";
					break;
			case 1:  TechLevel = "Agriculture";
					break;
			case 2:  TechLevel = "Medieval";
					break;
			case 3:  TechLevel = "Renaissance";
					break;
			case 4:  TechLevel = "Early Industrial";
					break;
			case 5:  TechLevel = "Industrial";
					break;
			case 6:  TechLevel = "Post-Industrial";
					break;
			case 7:  TechLevel = "Hi-Tech";
					break;
			
			
		}
		
		return TechLevel;
	}
}
