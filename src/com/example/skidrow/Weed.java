package com.example.skidrow;

/**
 * This class contains the logic for a Weed drug
 * @author Michael Underwood
 * @version 0.1
 *
 */

public class Weed extends Good {

	public Weed() {
		super("Weed", 30,0,0,0,10,10, 5, 8);
	}

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
