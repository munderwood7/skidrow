package com.example.skidrow;

public class Game {
	private Player player;
	private City[] citiesList;
	
	/**
	 * Constructor for the game class
	 */
	public Game(){
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
	}
	/**
	 * Method used to generate all cities in the country. 
	 */
	private void generateCountry(){
		//List of top 50 Most Dangerous cities in the country (USA)
		final String[] CityNames= {
				"Poughkeepsie",
				"New Bedford",
				"Fall River",
				"Cincinnat",
				"Miami",
				"Alexandria",
				"Bridgeton",
				"Springfield",
				"Lake Worth",
				"Hartford",
				"Washington",
				"Daytona Beach",
				"Springfield",
				"Battle Creek",
				"Compton",
				"uffalo",
				"Cleveland",
				"Atlanta",
				"Pine Bluff",
				"Trenton",
				"Stockton",
				"Homestead",
				"Desert Hot Springs",
				"Texarkana",
				"Rockford",
				"Baltimore",
				"Little Rock",
				"New Haven",
				"Salisbury",
				"Harvey",
				"Harrisburg",
				",Memphis",
				"Oakland",
				"Spartanburg",
				"Inkster",
				"Myrtle Beach",
				"Chelsea",
				"Riviera Beach",
				"Newburgh",
				"Pontiac",
				"St. Louis",
				"Atlantic City",
				"Wilmington",
				"Flint",
				"Detroit",
				"Camden",
				"Saginaw",
				"West Memphis",
				"Chester",
				"East St. Louis",
		};
		//Checks the total number of city NAMES
		int numCities = CityNames.length;
		//initialize the total NUMBER of countries that will be in the game
		citiesList = new City[numCities];
		int checkNumCities = 0;
		//Instantiate the Cities in the country
		for (int i = 0; i < numCities; i++){
			citiesList[i] = new City(CityNames[i]);
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
}
