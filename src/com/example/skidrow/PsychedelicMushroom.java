package com.example.skidrow;

import java.io.Serializable;

/**
 * This class contains the logic for a PsychedelicMushroom good.
 * @author Michael Underwood
 * @version 0.1
 *
 */

public class PsychedelicMushroom extends Good implements Serializable{
	/**
	 * Constructor for Psychedelic Mushroom good
	 */
	public PsychedelicMushroom()
	{
		super("PsychedelicMushroom", 3500, 5, 0, 5, -125, 150, 6, 2);
	}

	/**
	 * This method overrides parent method by setting PsychedelicMushroom good's base price for different techlevels
	 */
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
