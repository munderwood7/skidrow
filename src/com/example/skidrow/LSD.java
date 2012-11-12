package com.example.skidrow;

import java.io.Serializable;

/**
 * This class contains the logic for an LSD drug
 * @author Michael Underwood
 * @version 0.1
 *
 */

public class LSD extends Good implements Serializable{
	
	/**
	 * Constructor for LSD good
	 */
	public LSD()
	{
		super("LSD", 350, 2, 2, 3, 20, 10, 5, 2);
	}

	/**
	 * This method overrides parent method by setting LSD good's base price for different techlevels
	 */
	@Override
	int getBasePrice(String event) 
	{
		 if (event.equals("LACKOFWORKERS"))
		 {
			 return basePrice * randomGen.nextInt(5)+1;
		 }
		 else if (event.equals("WARLIKE"))
		 {
			 return Math.round(basePrice *(1/randomGen.nextInt(3)+1));
		 }
		 else{
			 return basePrice;
		 }
	}

}
