package com.example.skidrow;

/**
 * This class contains the logic for an Adderall drug
 * @author Michael Underwood
 * @version 0.1
 *
 */

public class Adderall extends Good {
	
	public Adderall()
	{
		super(100, 1, 0, 1, 5, 5);
	}

	@Override
	int getBasePrice(String event) 
	{
		 if (event.equals("DROUGHT"))
		 {
			 return basePrice * randomGen.nextInt(5)+1;
		 }
		 else if (event.equals("LOTSOFWATER"))
		 {
			 return Math.round(basePrice *(1/randomGen.nextInt(3)+1));
		 }
		 else if (event.equals("DESERT"))
		 {
			 return basePrice + randomGen.nextInt(150);
		 }
		 else{
			 return basePrice;
		 }
	}

}
