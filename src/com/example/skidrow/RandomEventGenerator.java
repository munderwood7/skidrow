package com.example.skidrow;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
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
    private boolean Debug=true;

	
	/**
	 * This is the Random Event Generator that instantiates 10 different events for the game
	 */
    private static RandomEventGenerator instance = null;
    protected RandomEventGenerator() {
    	rng=new Random();
		list= new LinkedList();
		eArray[0]= new Event("drought","It has been raining the last couple of days",4, " There has been a drought for an extended period that has decreased the levels of psilocybin and psilocin in the last batch of shrooms.", 2);
		eArray[1]= new Event("cold","The temparute has been rising.", 4,   " There has been a radical decrease of temperature in the surrounding areas, and homeless people are desperate to get some crack.", 1);
		eArray[2]= new Event("bullish economy","The economy is slowing down.",12," There are early signs of a bull market, and bankers are eager to celebrate their future growth.", 1);
		eArray[3]= new Event("music festival","Crazy fans burn down the music festival site.",2," An important music festival is coming to town.", 1);
		eArray[4]= new Event("war","The war is finally over!",24," The Revolutionary Armed Forces of Colombia have intensied their efforts to overthrow the Colombian government. This has allowed Colombian drug lords to increase their weed production due to less supervision on behalf of the government. ", -2);
		eArray[5]= new Event("intensified border control","Border Control funds have been reduced.",12," Border control has been intensified at the nearby border.", 3);
		eArray[6]= new Event("recent legislation changes","The legislation was reverted.",12," Recent legislation changes have increased the severity of punishment of illegal drugs consumption.", -3);
		eArray[7]= new Event("bearish economy","The economy is recuperating from the financial downturn.",12," An economic downturn just hit the surrounding areas, and people are forced to work long hours.", -1);
		eArray[8]= new Event("finals","Finals week is over",2,"A nearby university has finals week next week.", 1);
		eArray[9]= new Event("Heroin confiscation","The local drug dealers were able to recuperate their confiscated Heroin.",2,"200 kilograms of heroin were confiscated in Guatemala.", 2);
    }
    public static RandomEventGenerator getInstance() {
       if(instance == null) {
          instance = new RandomEventGenerator();
       }
       return instance;
    }

	
	/**
	 * This method generates a random event
	 */
	public void generateEvent(){
		currentStep=AppUtil.getStep();
		int rnd1=rng.nextInt(eArray.length);
		currE=eArray[rnd1];
		currE.setStepNum(currentStep+rng.nextInt(5));
		Log.i(TAG,"New event starts: "+currE.getStepNum());
		list.add(currE);
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
		if(!list.isEmpty()&&list.peek()!=null){
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
		Log.i(TAG,"checkStartEvent: "+(AppUtil.getStep()-getNextEventStepNum()));
		if(getSizeOfEventList()!=0&&AppUtil.getStep()==getNextEventStepNum()){
			return true;
		}
		return false;
	}
	
	/**
	 * This method returns a boolean indicating if next event is LAST to occur 
	 * @return boolean last event
	 */
	public boolean checkEndEvent(){
		Log.i(TAG,"checkEndEvent: "+(AppUtil.getStep()-(getNextEventStepNum()+getNextEventDuration())));
		if(getSizeOfEventList()!=0&&AppUtil.getStep()==getNextEventStepNum()+getNextEventDuration()){
			return true;
		}
		return false;
	}
	/**
	 * Gets the current Event
	 * @return current Event
	 */
	public Event getCurrE(){
		return currE;
	}
	/**
	 * Returns the number of different events that can happen throughout the game
	 * @return number of different events that can happen throughout the game
	 */
	public int getNumberOfEventTypes(){
		return eArray.length;
	}
	/**
	 * Returns the length of the event list
	 */
	public int getSizeOfEventList(){
		if(list!=null){
			return list.size();
		}
		return 0;
	}
	public void eraseCurrentInstance(){
		instance=null;
	}
}
