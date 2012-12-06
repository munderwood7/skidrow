package com.example.skidrow;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that represents an event created by the simulator
 * @author apavia3
 *
 */
public class Event implements Serializable
{
	private String description;
	private String name;
	private int duration;
	private int stepNum;//when it start
	private int priceEffect;//how much it lasts
	private String terminationMessage;
	private String city;
	private String drugAffected;
	private long id;
	
	/**
	 * This is the construction for a event sets default duration to zero
	 * @param name string for the event
	 * @param description brief description of the event
	 */
	public Event(String name, String description)
	{
		this.name=name;
		this.description=description;
		this.duration=0;
	}
	public Event()
	{

	}
	
	/**
	 * Constructor with more parameters that determine its duration and price effect
	 * @param name string of the event
	 * @param duration	int duration of the event
	 * @param description brief description of the event
	 * @param priceEffect int value of event effect on prices of goods. Can be either positive or negative
	 */
	
	public Event(String name, String terminationMessage,int initialStep, int duration, String description, int priceEffect, String goodAffected, String city) {

		this.name=name;
		this.terminationMessage=terminationMessage;
		this.stepNum=initialStep;
		this.duration=duration;
		this.description=description;
		this.priceEffect=priceEffect;
		this.drugAffected =  goodAffected;
		this.city=city;
		

	}
	
	/**
	 * Returns brief description of the event
	 * @return Brief description of the event
	 */
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description=description;
	}
	public void setPriceEffect(int priceEffec){
		this.priceEffect=priceEffect;
	}
	/**
	 * Returns the name of the event
	 * @return event's name
	 */
	public String getName()
	{
		return name;
	}
	public  void setName(String name)
	{
		this.name=name;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getStepNum()
	{
		return stepNum;
	}
	
	/**
	 * 
	 * @param stepNum
	 */
	public void setStepNum(int stepNum)
	{
		this.stepNum= stepNum;
	}
	
	/**
	 * this method returns the duration of the event
	 * @return event's duration
	 */
	public int getDuration()
	{
		return duration;
	}
	
	/**
	 * This method changes the value of the event duration to the value of the argument
	 * @param duration int value of new event duration
	 */
	public void setDuration(int duration)
	{
		this.duration= duration;
	}
	
	/**
	 * This method returns the effect of event on good's price
	 * @return int value of price effect of goods
	 */
	public int getPriceEffect()
	{
		return priceEffect;
	}
	
	public String toString(){
		return this.name;
	}

	/**
	 * @return the terminationMessage
	 */
	public String getTerminationMessage() {
		return terminationMessage;
	}

	/**
	 * @param terminationMessage the terminationMessage to set
	 */
	public void setTerminationMessage(String terminationMessage) {
		this.terminationMessage = terminationMessage;
	}

	/**
	 * @return the drugAffected
	 */
	public String getDrugAffected() {
		return drugAffected;
	}

	/**
	 * @param drugAffected the drugAffected to set
	 */
	public void setDrugAffected(String drugAffected) {
		this.drugAffected = drugAffected;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the terminationNum
	 */


	

}
