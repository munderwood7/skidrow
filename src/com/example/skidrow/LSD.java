package com.example.skidrow;

/**
 * This class contains the logic for an LSD drug
 * @author Michael Underwood
 * @version 0.1
 *
 */

public class LSD extends Good {
	
	public LSD()
	{
		super(350, 2, 2, 3, 20, 10);
	}

	@Override
	int getBasePriceDuringEvent(String event) {
		// TODO Auto-generated method stub
		return 0;
	}

}
