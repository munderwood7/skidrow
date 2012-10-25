package com.example.skidrow;

/**
 * This class contains the logic for a PsychedelicMushroom good.
 * @author Michael Underwood
 * @version 0.1
 *
 */

public class PsychedelicMushroom extends Good {
	
	public PsychedelicMushroom()
	{
		super(3500, 5, 0, 5, -125, 150);
	}

	@Override
	int getBasePrice(String event) 
	{
		 if (event.equals("BOREDOM"))
		 {
			 return basePrice * randomGen.nextInt(5)+1;
		 }
		 else if (event.equals("LOTSOFHERBS"))
		 {
			 return Math.round(basePrice *(1/randomGen.nextInt(3)+1));
		 }
		 else{
			 return basePrice;
		 }
	}

}
