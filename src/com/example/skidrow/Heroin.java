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
	int getBasePriceDuringEvent(String event) {
		// TODO Auto-generated method stub
		return 0;
	}

}
