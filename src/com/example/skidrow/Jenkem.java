package com.example.skidrow;

public class Jenkem extends Good{
	/**
	 * Constructor for jenkem good
	 */
	public Jenkem()
	{
		super("Jenkem", 5000, 6, 4, 7, -150, 100, 4, 4);
	}

	/**
	 * This method overrides parent method by setting jenkem good's base price for different techlevels
	 */
	@Override
	int getBasePrice(String event) 
	{
		 if (event.equals("BOREDOM"))
		 {
			 return basePrice * randomGen.nextInt(5)+1;
		 }
		 else{
			 return basePrice;
		 }
	}

}
