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
	int getBasePriceDuringEvent(String event) {
		// TODO Auto-generated method stub
		return 0;
	}

}
