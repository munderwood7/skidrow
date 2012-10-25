package com.example.skidrow;

import java.util.LinkedList;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class RandomEventGenerator {
	
	private Event[] eArray= new Event[10];
	private final Handler myHandler;
	private LinkedList list;
	private Random rng;
	private int currentStep;
	
	
	private static final int MESSAGE_EVENT = 1;
	private static final int START_EVENT = 0;
	private static final int END_EVENT = 1;
	
	
	public RandomEventGenerator(Handler handler){
		myHandler=handler;
		list= new LinkedList();
		eArray[0]= new Event("DROUGHT",4, " There has been a drought for an extended period of months that has decreased the levels of psilocybin and psilocin in the last batch of shrooms.");
		eArray[1]= new Event("COLD",4,   " There has been a radical decrease of temperature in the surrounding areas, and homeless people are desperate to get some crack.");
		eArray[2]= new Event("BULLISH_ECONOMY",12," There are early signs of a bull market, and bankers are eager to celebrate their future growth.");
		eArray[3]= new Event("MUSIC_FESTIVAL",2," An important music festival is coming to town.");
		eArray[4]= new Event("WAR",24," The Revolutionary Armed Forces of Colombia have intensied their efforts to overthrow the Colombian government. This has allowed Colombian drug lords to increase their weed production due to less supervision on behalf of the government. ");
		eArray[5]= new Event("INTESIFIED_BORDER_CONTROL",12," Border control has been intensified in the surrounding borders.");
		eArray[6]= new Event("RECENT_LEGISTATION_CHANGES",12," Recent legislation changes have increased the severity of punishment of illegal drugs consumption.");
		eArray[7]= new Event("BEARISH_ECONOMY",12," An economic downturn just hit the surrounding areas, and people are forced to work long hours.");
		eArray[8]= new Event("FINALS",2,"A nearby university has finals week next week.");
	}
	public void generateEvent(){
		currentStep=AppUtil.getStep();
		int rnd1=rng.nextInt(9);
		Event e=eArray[rnd1];
		e.setStepNum(currentStep+10+rng.nextInt(10));
		list.addLast(e);
	}
	public Event popEvent(){
		if(!list.isEmpty()){
			return (Event) list.pop();
		}
		return null;
	}
	public Event peek(){
		if(!list.isEmpty()){
			return (Event) list.peek();
		}
		return null;
	}
	public String getNextEventName(){
		if(!list.isEmpty()){
			return ((Event)list.peek()).getName();
		}
		return null;
	}
	public int getNextEventStepNum(){
		if(!list.isEmpty()){
			return ((Event)list.peek()).getStepNum();
		}
		return 0;
	}
	public int getNextEventDuration(){
		if(!list.isEmpty()){
			return ((Event)list.peek()).getDuration();
		}
		return 0;
	}
	public String getNextEventDescription(){
		if(!list.isEmpty()){
			return ((Event)list.peek()).getDescription();
		}
		return null;
	}
	public boolean checkStartEvent(){
		if(AppUtil.getStep()==getNextEventStepNum()){
			return true;
		}
		return false;
	}
	public boolean checkEndEvent(){
		if(AppUtil.getStep()==getNextEventStepNum()+getNextEventDuration()){
			return true;
		}
		return false;
	}
	
}
