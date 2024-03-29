package com.example.skidrow;

import java.io.Serializable;

/**
 * This class contains the logic for an ecstacy drug
 * @author Michael Underwood
 * @version 0.1
 *
 */

public class Extacy extends Good implements Serializable{
	/**
	 * Constructor for cocaine good
	 */
	public Extacy()
	{
		super("Extacy", 250, 3, 1, 6, -10, 5, 2, 6);
	}

	/**
	 * This method overrides parent method by setting ecstacy good's base price for different techlevels
	 */
	@Override
	int getBasePrice(String event) 
	{
		 if (event.equals("CROPFAIL"))
		 {
			 return basePrice * randomGen.nextInt(5)+1;
		 }
		 else if (event.equals("RICHSOIL"))
		 {
			 return Math.round(basePrice *(1/randomGen.nextInt(3)+1));
		 }
		 else if (event.equals("POORSOIL"))
		 {
			 return basePrice + randomGen.nextInt(150);
		 }
		 else{
			 return basePrice;
		 }
	}

}
