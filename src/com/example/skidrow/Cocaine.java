package com.example.skidrow;

/**
 * This class contains the logic for a cocaine drug
 * @author Michael Underwood
 * @version 0.1
 *
 */

public class Cocaine extends Good {
	
	public Cocaine()
	{
		super(1250, 3, 1, 5, -75, 100);
	}
	
	@Override
	int getBasePrice(String event) 
	{
		 if (event.equals("COLD"))
		 {
			 return basePrice * randomGen.nextInt(5)+1;
		 }
		 else if (event.equals("RICHFAUNA"))
		 {
			 return Math.round(basePrice *(1/randomGen.nextInt(3)+1));
		 }
		 else if (event.equals("LIFELESS"))
		 {
			 return basePrice + randomGen.nextInt(150);
		 }
		 else{
			 return basePrice;
		 }
	}

}
