package com.example.skidrow;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class Game {
	private Player player;
	private City currentCity;
	private City[] citiesList;
	private int step;
	
	private static final String TOAST="toast";
	private static final int MESSAGE_STEP_CHANGE = 0;
	private static final int MESSAGE_TOAST = 1;
	//List of top 50 Most Dangerous cities in the country (USA)
	private final String[] cityNames = {"Poughkeepsie", "New Bedford","Fall River","Cincinnati","Miami","Alexandria",
			"Bridgeton","Springfield","Lake Worth","Hartford","Washington","Daytona Beach","Springfield","Battle Creek",
			"Compton","Buffalo","Cleveland","Atlanta","Pine Bluff","Trenton","Stockton","Homestead","Desert Hot Springs",
			"Texarkana","Rockford","Baltimore","Little Rock","New Haven","Salisbury","Harvey","Harrisburg","Memphis",
			"Oakland","Spartanburg","Inkster","Myrtle Beach","Chelsea","Riviera Beach","Newburgh","Pontiac","St. Louis",
			"Atlantic City","Wilmington","Flint","Detroit","Camden","Saginaw","West Memphis","Chester","East St. Louis",
	};
	
	/**
	 * Constructor for the game class
	 */
	public Game(){
		step=0;
	}
	
	/**
	 * Method used to create a new instance of player in the game.
	 * 
	 * @param name Name of the Player
	 * @param communicationSkills Communications skill points
	 * @param fighterSkills Fighter skill points
	 * @param driverSkills Driver skill points
	 * @param dealerSkills Dealer skill Points
	 * @param difficultyLevel the level of difficulty
	 */
	public void createGame(String name, int communicationSkills, int fighterSkills, int driverSkills, int dealerSkills, String difficultyLevel){
		player = new Player(name, communicationSkills, fighterSkills, driverSkills, dealerSkills, difficultyLevel);
		generateCountry();
		currentCity=citiesList[17];//Atlanta is set as the default starting location
	}
	/**
	 * Method used to generate all cities in the country. 
	 */
	private void generateCountry(){
		//Checks the total number of city NAMES
		int numCities = cityNames.length;
		//initialize the total NUMBER of countries that will be in the game
		citiesList = new City[numCities];
		int checkNumCities = 0;
		//Instantiate the Cities in the country
		for (int i = 0; i < numCities; i++){
			citiesList[i] = new City(cityNames[i]);
			//maintains track of how many cities are actually created
			checkNumCities++;
		}
		//Prints confirmation of how many cities are created
		if (checkNumCities == numCities){
			System.out.println("Country with " + checkNumCities + " cities was created");
		} else{
			System.out.println("Not all cities were instanciated");
		}
	}
	
	/**
	 * Method to get the list of all the cities
	 * 
	 * @return the list of cities
	 */
	public String[] getCityList(){
		return cityNames;
	}
	
	/**
	 * Returns the city where the player is currently located
	 * @return City where the player is currently at
	 */
	public City getCurrentCity(){
		return currentCity;
	}
	
	/**
	 * Method to get a city based on an index of CityList
	 * @param the index of the city list
	 * @return the city that is stored on the city list
	 */
	public City getCity(int index){
		return citiesList[index];
	}
	
	/**
	 * Gets the information about a given city. The city is determined by its index in the citiesList.
	 * It returns all the information needed by the UI in a String array
	 * 
	 * @param index The index of the city
	 * @return String of information needed by the UI with the following format: [0]=name, [1]=techLevel, [2]=politicalSystem, [3]=distance
	 */
	public String[] getCityInfo(int index){
		String[] info = new String[4];
		City city = citiesList[index];
		
		info[0]=city.getName();
		info[1]=city.getTechLevel();
		info[2]=city.getResources();
		
		return info;
	}
	
	/**
	 * Grabs the necessary information for the UI from the player and packages it in a String array.
	 * The information is as follows(from index 0 to 7):communicationSkills, fighterSkills, driverSkills, dealerSkills, currentCity, playerHealth, playerName, playerMoney
	 * 
	 * @return Returns an array of player information for the UI
	 */
	public String[] getPlayerStatInfo(){
		String[] info = new String[8];
		int[] playerLevels = player.getSkillsArray();
		
		for(int x=0; x<4; x+=1){
			info[x] = Integer.toString(playerLevels[x]);
		}
		//info[4] = city
		//info[5] = health
		info[6] = player.getName();
		//info[7] = money;
		
		return info;
	}
	/**
	 * Method that is called every time the user makes a move;
	 * As of now, the only functionality of this method is to increment
	 * the step counter. 
	 */
	public void makeMove(){
		step++;
	}
	/**
	 * Returns the current step number
	 * @return step number
	 */
	public int getStep(){
		return step;
	}
	private final Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            	case MESSAGE_STEP_CHANGE:
            		break;  
            }		
        }
    };


}
