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
		super(250, 0, 0, 2, 3, 4);
	}

	@Override
	int getBasePriceDuringEvent(String event) {
		// TODO Auto-generated method stub
		return 0;
	}

}
