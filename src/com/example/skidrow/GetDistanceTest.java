package com.example.skidrow;

//import org.junit.Test;

import junit.framework.TestCase;

public class GetDistanceTest extends TestCase {
	City a=new City("City A",0,0);
	City b=new City("City B",3,0);
	City c=new City("City C",0,4);
	City d=new City("City D",8,0);
	City e=new City("City E",0,6);
	City f=new City("City F",5,5);
	City g=new City("City G",6,6);
	Game tester = new Game();	
	  //@Test
	  public void testHorizontalDistance() {    
		    tester.setCurrentCity(a);
	    	assertEquals("Distance from city A to city B should be 3.", "3.000", tester.getDistance(b));
	  }
	  //@Test
	  public void testVerticallDistance() {    
		    tester.setCurrentCity(a);
	    	assertEquals("Distance from city A to city C should be 4.", "4.000", tester.getDistance(c));
	  }
	  public void testDistance1() {    
		    tester.setCurrentCity(b);
	    	assertEquals("Distance from city B to city C should be 5.", "5.000", tester.getDistance(c));
	  }
	  public void testDistance2() {    
		    tester.setCurrentCity(d);
	    	assertEquals("Distance from city D to city E should be 10.", "10.000", tester.getDistance(e));
	  }
	  public void testDistance3() {    
		    tester.setCurrentCity(f);
	    	assertEquals("Distance from city F to city G should be 1.414.", "1.414", tester.getDistance(g));
	  }
	  public void testDistance4() {    
		    tester.setCurrentCity(a);
	    	assertEquals("Distance from city F to city G should be 8.485.", "8.485", tester.getDistance(g));
	  }

}
