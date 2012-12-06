package com.example.skidrow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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
	private final Random rng;
	private int currentStep;
	private Event currE;
	private int dbVersion;
	//Tag for logcat
    protected static final String TAG = "RandomEventGenerator";
    //True if we want to debug false otherwise
    private boolean Debug=true;
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_DESCRIPTION, MySQLiteHelper.COLUMN_DRUG,
  		  MySQLiteHelper.COLUMN_PRICE_CHANGE, MySQLiteHelper.COLUMN_INITIAL_STEP, MySQLiteHelper.COLUMN_DURATION, MySQLiteHelper.COLUMN_CITY, MySQLiteHelper.COLUMN_TERMINATION_MESSAGE};
    
    
    public RandomEventGenerator(Context context) {
    	dbHelper = new MySQLiteHelper(context);
  		ArrayList<String> affectedGoods = new ArrayList<String>();
  		rng=new Random();
  		
  		
  		//String name, String terminationMessage,int initialStep, int duration, String description, int priceEffect, String goodAffected, String city) {
		eArray[0]= new Event("drought","It has been raining the last couple of days",4,4, " There has been a drought for an extended period that has decreased the levels of psilocybin and psilocin in the last batch of shrooms.", 2, "PsychedelicMushroom","Atlanta");
		eArray[1]= new Event("cold","The temparute has been rising.", 4,4,   " There has been a radical decrease of temperature in the surrounding areas, and homeless people are desperate to get some crack.", 1, "Cocaine","Atlanta");
		eArray[2]= new Event("bullish economy","The economy is slowing down.",12,4," There are early signs of a bull market, and bankers are eager to celebrate their future growth.", 1, "Adderall","Atlanta");
		eArray[3]= new Event("music festival","Crazy fans burn down the music festival site.",2,4," An important music festival is coming to town.", 1, "Extacy","Atlanta");
		eArray[4]= new Event("war","The war is finally over!",24,4," The Revolutionary Armed Forces of Colombia have intensied their efforts to overthrow the Colombian government. This has allowed Colombian drug lords to increase their weed production due to less supervision on behalf of the government. ", -2, "Cocaine","Atlanta");
		eArray[5]= new Event("intensified border control","Border Control funds have been reduced.",12,4," Border control has been intensified at the nearby border.", 3, "Heroin","Atlanta");
		eArray[6]= new Event("recent legislation changes","The legislation was reverted.",12,4," Recent legislation changes have increased the severity of punishment of illegal drugs consumption.", -3, "Adderall","Atlanta");
		eArray[7]= new Event("bearish economy","The economy is recuperating from the financial downturn.",12,4," An economic downturn just hit the surrounding areas, and people are forced to work long hours.", -1, "LSD","Atlanta");
		eArray[8]= new Event("finals","Finals week is over",2,4,"A nearby university has finals week next week.", 1, "Weed","Atlanta");
		eArray[9]= new Event("Heroin confiscation","The local drug dealers were able to recuperate their confiscated Heroin.",2,4,"200 kilograms of heroin were confiscated in Guatemala.", 2, "Heroin","Atlanta");
  
    }

    public void open() throws SQLException {
      database = dbHelper.getWritableDatabase();
    }

    public void close() {
      dbHelper.close();
    }
    public Event createEvent(String name, String terminationMessage,int initialStep, int duration, String description, int priceEffect, String goodAffected, String city) {
      ContentValues values = new ContentValues();
      values.put(MySQLiteHelper.COLUMN_NAME, name);
      values.put(MySQLiteHelper.COLUMN_DESCRIPTION, description);
      values.put(MySQLiteHelper.COLUMN_DRUG, goodAffected);
      values.put(MySQLiteHelper.COLUMN_PRICE_CHANGE, priceEffect);
      values.put(MySQLiteHelper.COLUMN_INITIAL_STEP, initialStep);
      values.put(MySQLiteHelper.COLUMN_DURATION, duration);
      values.put(MySQLiteHelper.COLUMN_CITY, city);
      values.put(MySQLiteHelper.COLUMN_TERMINATION_MESSAGE, terminationMessage);
      
      long insertId = database.insert(MySQLiteHelper.TABLE_EVENTS, null,values);
      Cursor cursor = database.query(MySQLiteHelper.TABLE_EVENTS, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,null, null, null);
      cursor.moveToFirst();
      Event e = cursorToEvent(cursor);
      cursor.close();
      return e;
    }

    public void deleteEvent(Event event) {
      long id = event.getId();
      System.out.println("Comment deleted with id: " + id);
      database.delete(MySQLiteHelper.TABLE_EVENTS, MySQLiteHelper.COLUMN_ID
          + " = " + id, null);
    }

    public List<Event> getAllCurrentEventsSortedByInitialStep() {
      List<Event> eventsList = new ArrayList<Event>();
      Cursor cursor = database.query(MySQLiteHelper.TABLE_EVENTS,
      allColumns, null, null, null, null, MySQLiteHelper.COLUMN_INITIAL_STEP + " DESC");
      cursor.moveToFirst();
      while (!cursor.isAfterLast()) {
        Event e = cursorToEvent(cursor);
        eventsList.add(e);
        cursor.moveToNext();
      }
      
      // Make sure to close the cursor
      cursor.close();
      return eventsList;
    }
    public Event[] getAllCurrentEventsSortedByInitialStepArr() {
    	List<Event> list=getAllCurrentEventsSortedByInitialStep();
	    Event[] arr=new Event[list.size()];
	    for(int i=0;i<list.size();i++){
			arr[i]=(Event) list.get(i);
		}
	    return arr;
    }
    /**
     * Returns the next event
     * @return next event
     */
    public Event findNextEvent(){
        List<Event> eventsList = new ArrayList<Event>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_EVENTS,
        allColumns, null, null, null, null, MySQLiteHelper.COLUMN_INITIAL_STEP + " DESC");
        Log.i("RandomEventGenerator","cursor.getCount(): "+cursor.getCount());
        if(cursor.getCount()!=0){
        	cursor.moveToFirst();
        	Event e = cursorToEvent(cursor);
        	return e;
        } 
        Log.i("RandomEventGenerator","Failed in fetching the next event.");
        return null;
    }
    
    public Event findEventWithInitialnStep(int terminationStep){
    	/*Cursor c = sampleDB.rawQuery("SELECT FirstName, Age FROM " +
                SAMPLE_TABLE_NAME +
                " where Age > 10 LIMIT 5", null);*/
    	Cursor cursor = database.rawQuery("SELECT "+MySQLiteHelper.COLUMN_INITIAL_STEP+" FROM " +
    			MySQLiteHelper.TABLE_EVENTS +
                " where "+MySQLiteHelper.COLUMN_INITIAL_STEP+" = "+String.valueOf(terminationStep), null);
    	cursor.moveToFirst();
    	Event e=cursorToEvent(cursor);
    	if(e==null) Log.i("RandomEventGenerator","findEventWithTerminationStep is not working: NULL");
    	return cursorToEvent(cursor);
    }
   
    

    private Event cursorToEvent(Cursor cursor) {
	      Event e = new Event();
	      if(cursor.getString(1)!=null){
	    	  e.setId(cursor.getLong(0));
		      e.setName(cursor.getString(1));
		      e.setDescription(cursor.getString(2));
		      e.setDrugAffected(cursor.getString(3));
		      e.setPriceEffect(Integer.parseInt(cursor.getString(4)));
		      e.setStepNum(Integer.parseInt(cursor.getString(5)));
		      e.setDuration(Integer.parseInt(cursor.getString(6)));
		      e.setCity(cursor.getString(7));
		      e.setTerminationMessage(cursor.getString(8));
		      return e;
	      } else return null;
       
    }
    
    	/**
	 * This method generates a random event
	 */
	public void generateEvent(){
		Log.i(TAG,"New event starts: "+AppUtil.getStep()+1);
		createEvent("drought","It has been raining the last couple of days",AppUtil.getStep()+1,rng.nextInt(3)+1, " There has been a drought for an extended period that has decreased the levels of psilocybin and psilocin in the last batch of shrooms.", 2, "PsychedelicMushroom","Atlanta");
	}

	/**
	 * This method get the next event to occur
	 * @return next event
	 */
	public Event pop(){
		Event nextE=findNextEvent();
		if(nextE!=null){
			deleteEvent(nextE);
			return nextE;
		}else{
			Log.i("RandomEventGenerator","Tried to pop an event that was not in the database");
		}
		return null;
	}
	
	/**
	 * This event gets the informaiton of what will be the next event to occur
	 * @return next event on line
	 */
	public Event peek(){
		Event nextE=findNextEvent();
		if(nextE!=null){
			return nextE;
		}else{
			Log.i("RandomEventGenerator","Tried to peek but there is nothing in the database");
		}
		return null;
	}
	
	/**
	 * This method returns the name of the next event
	 * @return string name of next event
	 */
	public String getNextEventName(){
		Event e=peek();
		if(e!=null){
			return e.getName();
		}
		Log.i("RandomEventGenerator","Tried to getNextEventName, but nothing was in the datbase");
		return null;
	}
	
	/**
	 * This method returns the step number of the next event
	 * @return int event step number
	 */
	public int getNextEventStepNum(){
		Event e=peek();
		if(e!=null){
			return e.getStepNum();
		}
		Log.i("RandomEventGenerator","Tried to getNextStepNum, but nothing was in the datbase");
		return 0;
	}
	
	/**
	 * This method return the duration of the next event
	 * @return next event duration
	 */
	public int getNextEventDuration(){
		Event e=peek();
		if(e!=null){
			return e.getDuration();
		}
		Log.i("RandomEventGenerator","Tried to getNextStepNum, but nothing was in the datbase");
		return 0;
	}
	
	/**
	 * This method returns a brief description of the next event
	 * @return string event description
	 */
	public String getNextEventDescription(){

		Event e=peek();
		if(e!=null){
			return e.getDescription();
		}
		Log.i("RandomEventGenerator","Tried to getNextEventDescription, but nothing was in the datbase");
		return null;
	}
	
	/**
	 * This method returns a boolean indicating if next event is FIRST to occur 
	 * @return boolean first event
	 */
	public boolean checkStartEvent(){
		Log.i(TAG,"checkStartEvent: "+(AppUtil.getStep()-getNextEventStepNum()));
		if(peek()!=null&&AppUtil.getStep()==peek().getStepNum()){
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
		if(peek()!=null&&AppUtil.getStep()==peek().getStepNum()+peek().getDuration()){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the number of different events that can happen throughout the game
	 * @return number of different events that can happen throughout the game
	 */
	public int getNumberOfEventTypes(){
		return eArray.length;
	}
	
	public int getSizeOfEventList(){
		List l=getAllCurrentEventsSortedByInitialStep();
		if(l!=null){
			return l.size();
		}
		return 0;
	}
	
	public HashMap<String,String> getHashMapOfEvent(Event e){
		HashMap<String,String> temp = new HashMap<String,String>();
		temp.put("eventName",e.getName());
		temp.put("drug",e.getDrugAffected());
    	temp.put("deltaPrice",Integer.toString(e.getPriceEffect()));
    	temp.put("city",e.getCity());
    	temp.put("termination",Integer.toString(Math.max(0,e.getDuration()+e.getStepNum()-AppUtil.game.getStep())));
		return temp;
	}

}
