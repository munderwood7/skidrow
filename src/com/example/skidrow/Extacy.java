package com.example.skidrow;

/**
 * This class contains the logic for an ecstacy drug
 * @author Michael Underwood
 * @version 0.1
 *
 */

public class Extacy extends Good {
	
	public Extacy()
	{
		super(250, 3, 1, 6, -10, 5);
	}

	@Override
	int getBasePriceDuringEvent(String event) {
		// TODO Auto-generated method stub
		return 0;
	}

}
