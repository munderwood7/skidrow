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
	int getBasePriceDuringEvent(String event) {
		// TODO Auto-generated method stub
		return 0;
	}

}
