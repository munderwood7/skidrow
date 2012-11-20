package com.example.skidrow;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * This class creates an array of randomly generated events
 * @author les7uve
 *
 */
public class RandomEventGenerator implements Serializable{
	
	private Event[] eArray= new Event[10]; //different number of total events in the game
	private LinkedList list;
	private final Random rng;
	private int currentStep;
	private Event currE;
	//Tag for logcat
    protected static final String TAG = "RandomEventGenerator";
    //True if we want to debug false otherwise
    private boolean D=true;

	
	/**
	 * This is the Random Event Generator that instantiates 10 different events for the game
	 */
	public RandomEventGenerator(){
		rng=new Random();
		list= new LinkedList();
		eArray[0]= new Event("DROUGHT",4, " There has been a drought for an extended period that has decreased the levels of psilocybin and psilocin in the last batch of shrooms.", 2);
		eArray[1]= new Event("COLD",4,   " There has been a radical decrease of temperature in the surrounding areas, and homeless people are desperate to get some crack.", 1);
		eArray[2]= new Event("BULLISH_ECONOMY",12," There are early signs of a bull market, and bankers are eager to celebrate their future growth.", 1);
		eArray[3]= new Event("MUSIC_FESTIVAL",2," An important music festival is coming to town.", 1);
		eArray[4]= new Event("WAR",24," The Revolutionary Armed Forces of Colombia have intensied their efforts to overthrow the Colombian government. This has allowed Colombian drug lords to increase their weed production due to less supervision on behalf of the government. ", -2);
		eArray[5]= new Event("INTESIFIED_BORDER_CONTROL",12," Border control has been intensified at the nearby border.", 3);
		eArray[6]= new Event("RECENT_LEGISTATION_CHANGES",12," Recent legislation changes have increased the severity of punishment of illegal drugs consumption.", -3);
		eArray[7]= new Event("BEARISH_ECONOMY",12," An economic downturn just hit the surrounding areas, and people are forced to work long hours.", -1);
		eArray[8]= new Event("FINALS",2,"A nearby university has finals week next week.", 1);
		eArray[9]= new Event("HEROIN_CONFISCATION",2,"200 kilograms of heroin were confiscated in Guatemala.", 2);
	}
	
	/**
	 * This method generates a random event
	 */
	public void generateEvent(){
		currentStep=AppUtil.getStep();
		int rnd1=rng.nextInt(eArray.length);
		currE=eArray[rnd1];
		currE.setStepNum(currentStep+10+rng.nextInt(10));
		list.addLast(currE);
		if(D) Log.i(TAG, "New event -> " + currE.getName() + "\nEvent will occur on step " + currE.getStepNum()+" and will last " + currE.getDuration() +" steps.");
	}
	
	/**
	 * This method get the next event to occur
	 * @return next event
	 */
	public Event pop(){
		if(!list.isEmpty()){
			return (Event) list.pop();
		}
		return null;
	}
	
	/**
	 * This event gets the informaiton of what will be the next event to occur
	 * @return next event on line
	 */
	public Event peek(){
		if(!list.isEmpty()){
			return (Event) list.peek();
		}
		return null;
	}
	
	/**
	 * This method returns the name of the next event
	 * @return string name of next event
	 */
	public String getNextEventName(){
		if(!list.isEmpty()){
			return ((Event)list.peek()).getName();
		}
		return null;
	}
	
	/**
	 * This method returns the step number of the next event
	 * @return int event step number
	 */
	public int getNextEventStepNum(){
		if(!list.isEmpty()){
			return ((Event)list.peek()).getStepNum();
		}
		return 0;
	}
	
	/**
	 * This method return the duration of the next event
	 * @return next event duration
	 */
	public int getNextEventDuration(){
		if(!list.isEmpty()){
			return ((Event)list.peek()).getDuration();
		}
		return 0;
	}
	
	/**
	 * This method returns a brief description of the next event
	 * @return string event description
	 */
	public String getNextEventDescription(){
		if(!list.isEmpty()){
			return ((Event)list.peek()).getDescription();
		}
		return null;
	}
	
	/**
	 * This method returns a boolean indicating if next event is FIRST to occur 
	 * @return boolean first event
	 */
	public boolean checkStartEvent(){
		if(AppUtil.getStep()==getNextEventStepNum()){
			return true;
		}
		return false;
	}
	
	/**
	 * This method returns a boolean indicating if next event is LAST to occur 
	 * @return boolean last event
	 */
	public boolean checkEndEvent(){
		if(AppUtil.getStep()==getNextEventStepNum()+getNextEventDuration()){
			return true;
		}
		return false;
	}
	public Event getCurrE(){
		return currE;
	}
}
