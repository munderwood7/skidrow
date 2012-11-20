package com.example.skidrow;

import java.io.Serializable;

/**
 * This class contains the logic for a heroin drug 
 * @author Michael Underwood
 * @version 0.1
 *
 */

public class Heroin extends Good implements Serializable{
	/**
	 * Constructor for heroin good
	 */
	public Heroin() {
		super("Heroin",900,4,3,5,-30,5, 5, 2);
	}

	/**
	 * This method overrides parent method by setting heroin good's base price for different techlevels
	 */
	@Override
	public int getBasePrice(String event) 
	{
		 if (event.equals("WAR"))
		 {
			 return basePrice * randomGen.nextInt(5)+1;
		 }
		 else if (event.equals("WAR1"))
		 {
			 return basePrice * 5;
		 }
		 else if (event.equals("MINERALRICH"))
		 {
			 return Math.round(basePrice *(1/randomGen.nextInt(3)+1));
		 }
		 else if (event.equals("MINERALRICH1"))
		 {
			 return basePrice + 500;
		 }
		 else if (event.equals("MINERALPOOR1"))
		 {
			 return basePrice - 500;
		 }
		 else if (event.equals("MINERALPOOR"))
		 {
			 return basePrice + randomGen.nextInt(150);
		 }
		 else{
			 return basePrice;
		 }
	}

}
