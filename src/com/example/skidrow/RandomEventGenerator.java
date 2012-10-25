package com.example.skidrow;

public class RandomEventGenerator {
	private Event[] eArray= new Event[10];
	public RandomEventGenerator(){
		eArray[0]= new Event("DROUGHT",5," There has been a drought for an extended period of months that has decreased the levels of psilocybin and psilocin in the last batch of shrooms.");
		eArray[1]= new Event("COLD", 8,  " There has been a radical decrease of temperature in the surrounding areas, and homeless people are desperate to get some crack.");
		eArray[2]= new Event("BULLISH_ECONOMY",12," There are early signs of a bull market, and bankers are eager to celebrate their future growth.");
		eArray[3]= new Event("MUSIC_FESTIVAL",3," An important music festival is coming to town.");
		eArray[4]= new Event("WAR",12," The Revolutionary Armed Forces of Colombia have intensied their efforts to overthrow the Colombian government. This has allowed Colombian drug lords to increase their weed production due to less supervision on behalf of the government. ");
		eArray[5]= new Event("INTESIFIED_BORDER_CONTROL",12," Border control has been intensified in the surrounding borders.");
		eArray[6]= new Event("RECENT_LEGISTATION_CHANGES",12," Recent legislation changes have increased the severity of punishment of illegal drugs consumption.");
		eArray[7]= new Event("BEARISH_ECONOMY",12," An economic downturn just hit the surrounding areas, and people are forced to work long hours.");
		eArray[8]= new Event("FINALS",2,"A nearby university has finals week next week.");
	}
	
}
