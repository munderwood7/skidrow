package com.example.skidrow;

import static org.junit.Assert.*;

import org.junit.Test;

public class CityTest {
	
	
	@Test
	public void testSetTechLevel() {
		//fail("Not yet implemented");
		City city1 = new City("city1");
		 city1.setTechLevel();
		 int ct= city1.getTechLevelInt();
		 assertTrue(" If the tech level is not 8 then this should be true ",ct!=8);
		 
	}
	
	@Test
	public void testSetTechLevelNegative() {
		City city1 = new City("city1");
		 city1.setTechLevel();
		 int ct= city1.getTechLevelInt();
		assertTrue(" if the tech level is not negavtive this should be true ",ct != -1);
	}
	
}
