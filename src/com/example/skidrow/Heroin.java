package com.example.skidrow;

/**
 * This class contains the logic for a heroin drug 
 * @author Michael Underwood
 * @version 0.1
 *
 */

public class Heroin extends Good {

	public Heroin() {
		super(900,4,3,5,-30,5);
	}

	@Override
	int getBasePrice(String event) 
	{
		 if (event.equals("WAR"))
		 {
			 return basePrice * randomGen.nextInt(5)+1;
		 }
		 else if (event.equals("MINERALRICH"))
		 {
			 return Math.round(basePrice *(1/randomGen.nextInt(3)+1));
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
