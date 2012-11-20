package com.example.skidrow.JUnitTest;

//import org.junit.Test;

import com.example.skidrow.City;
import com.example.skidrow.Game;

import junit.framework.TestCase;

public class GetDistanceTest extends TestCase {
	City cityA=new City("City A",0,0);
	City cityB=new City("City B",3,0);
	City cityC=new City("City C",0,4);
	City cityD=new City("City D",8,0);
	City cityE=new City("City E",0,6);
	City cityF=new City("City F",5,5);
	City cityG=new City("City G",6,6);
	Game tester = new Game();	
	//@Test
	  public void testDistanceToItself() {    
		    tester.setCurrentCity(cityA);
	    	assertEquals("Distance from city A to city A should be 0.", "0.000", tester.getDistance(cityA));
	  }
	  //@Test
	  public void testHorizontalDistance() {    
		    tester.setCurrentCity(cityA);
	    	assertEquals("Distance from city A to city B should be 3.", "3.000", tester.getDistance(cityB));
	  }
	  //@Test
	  public void testVerticallDistance() {    
		    tester.setCurrentCity(cityA);
	    	assertEquals("Distance from city A to city C should be 4.", "4.000", tester.getDistance(cityC));
	  }
	  public void testDistance1() {    
		    tester.setCurrentCity(cityB);
	    	assertEquals("Distance from city B to city C should be 5.", "5.000", tester.getDistance(cityC));
	  }
	  public void testDistance2() {    
		    tester.setCurrentCity(cityD);
	    	assertEquals("Distance from city D to city E should be 10.", "10.000", tester.getDistance(cityE));
	  }
	  public void testDistance3() {    
		    tester.setCurrentCity(cityF);
	    	assertEquals("Distance from city F to city G should be 1.414.", "1.414", tester.getDistance(cityG));
	  }
	  public void testDistance4() {    
		    tester.setCurrentCity(cityA);
	    	assertEquals("Distance from city a to city G should be 8.485.", "8.485", tester.getDistance(cityG));
	  }
	  public void testDistance5() {    
		    tester.setCurrentCity(cityG);
	    	assertEquals("Distance from city G to city F should be 1.414.", "1.414", tester.getDistance(cityF));
	  }
	  public void testDistance6() {    
		    tester.setCurrentCity(cityG);
	    	assertEquals("Distance from city G to city A should be 8.485.", "8.485", tester.getDistance(cityA));
	  }

}
