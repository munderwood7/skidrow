package com.example.skidrow;

/**
 * This class contains the logic for a horse tranquilizer drug
 * @author Michael Underwood
 * @version 0.1
 *
 */

public class HorseTranquilizer extends Good {
	
	public HorseTranquilizer()
	{
		super("HorseTranquilizer", 250, 0, 0, 2, 0, 4, 5, 1);
	}

	@Override
	int getBasePrice(String event) 
	{
		 if (event.equals("PLAGUE"))
		 {
			 return basePrice * randomGen.nextInt(5)+1;
		 }
		 else if (event.equals("ARTISTIC"))
		 {
			 return Math.round(basePrice *(1/randomGen.nextInt(3)+1));
		 }
		 else{
			 return basePrice;
		 }
	}

}
