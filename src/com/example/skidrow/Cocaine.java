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
	int getBasePriceDuringEvent(String event) {
		// TODO Auto-generated method stub
		return 0;
	}

}
