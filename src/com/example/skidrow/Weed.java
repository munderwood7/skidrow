package com.example.skidrow;

import java.io.Serializable;

/**
 * This class contains the logic for a Weed drug
 * @author Michael Underwood
 * @version 0.1
 *
 */

public class Weed extends Good implements Serializable{

	/**
	 * Constructor for weed good
	 */
	public Weed() {
		super("Weed", 30,0,0,0,10,10, 5, 8);
	}

	/**
	 * This method overrides parent method by setting weed good's base price for different techlevels
	 */
	@Override
	int getBasePrice(String event) 
	{
		 if (event.equals("LACKOFWORKERS"))
		 {
			 return basePrice * randomGen.nextInt(5)+1;
		 }
		 else{
			 return basePrice;
		 }
	}

}
