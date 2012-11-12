package com.example.skidrow;
import java.io.Serializable;
import java.util.Random;

/**
 * It assigns the properties of cities 
 * 
 * @author Waleed
 * @version 1.0
 */
public class City implements Serializable{
	
	private String Name;
	private int xLocation;
	private int yLocation;
	private String techLevel;
	private int techLevlInt;
	private static boolean[][] locationTaken = new boolean[50][50];
	private String resources = " ";
	private final String[] listOfResources={"NOSPECIAL", "MINERALRICH", "MINERALPOOR", "DESERT", "LOTSOFWATER", 
											"RICHSOIL", "POORSOIL", "RICHFAUNA", "LIFELESS", "WEIRDMUSHROOMS", "LOTSOFHERBS",
											"ARTISTIC", "WARLIKE"};
	/**
	 * Constructor for city
	 * @param CityName string of city name
	 */
	public City(String CityName){
		Name = CityName;
		setTechLevel();
		setLocation();
		setResources();
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
	 */
	public void setTechLevel(){
		Random rand = new Random();
		String techLevel=" ";
		int randTechLevel = rand.nextInt(8);
		this.techLevlInt = randTechLevel;
		
		switch(randTechLevel){
			case 0:  techLevel = "Pre-Agriculture";
					break;
			case 1:  techLevel = "Agriculture";
					break;
			case 2:  techLevel = "Medieval";
					break;
			case 3:  techLevel = "Renaissance";
					break;
			case 4:  techLevel = "Early Industrial";
					break;
			case 5:  techLevel = "Industrial";
					break;
			case 6:  techLevel = "Post-Industrial";
					break;
			case 7:  techLevel = "Hi-Tech";
					break;
		}
		this.techLevel= techLevel;
	}
	/**
	 * Assigns a unique point in a 50-by-50 grid using a random number generator
	 * 
	 */
	public void setLocation(){
		boolean flag=true;
		Random rand = new Random();
		while(flag){
			xLocation = rand.nextInt(50);
			yLocation = rand.nextInt(50);
			if(City.locationTaken[xLocation][yLocation]==false){
				City.locationTaken[xLocation][yLocation]=true;
				flag=false;
			}
		}
		this.yLocation=yLocation;
		this.xLocation=xLocation;
	}
	/**
	 * Returns the x and y location of the city
	 * @return an array of ints where the first value corresponds to the x location and the second value
	 * corresponds to the y location
	 */
	 public int[] getLocation(){
		 int[] location=new int[2];
		 location[0]=xLocation;
		 location[1]=yLocation;
		return location;
		 
	 }
	 /**
	 * Returns the tech level of the city
	 * @return a string with the tech level of the city
	 */
	 public String getTechLevel(){
		 return techLevel;
	 }
	 /**
	  * Returns the tech level as an integer
	  * @return Integer of tech level
	  */
	 public int getTechLevelInt(){
		 return this.techLevlInt;
	 }
	 /**
	  * Assigns resources to the city randomly from the array of resources
	  * 
	  */ 
	 public void setResources(){
		 Random rand = new Random();
		 int randResources = rand.nextInt(listOfResources.length);
		 resources = listOfResources[randResources]; 
		 
	 }
	 /**
	  * Returns the resources of the city
	  * @return a string of the resources of the city
	  */
	 public String getResources(){
		 return resources;
	 }
	 
}
