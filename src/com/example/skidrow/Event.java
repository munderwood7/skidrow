package com.example.skidrow;
/**
 * Class that represents an event created by the simulator
 * @author apavia3
 *
 */
public class Event 
{
	private String description;
	private String name;
	private int duration;
	private int stepNum;
	private int priceEffect;
	
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
	
	/**
	 * Constructor with more parameters that determine its duration and price effect
	 * @param name string of the event
	 * @param duration	int duration of the event
	 * @param description brief description of the event
	 * @param priceEffect int value of event effect on prices of goods. Can be either positive or negative
	 */
	public Event(String name, int duration, String description, int priceEffect)
	{
		this.name=name;
		this.description=description;
		this.duration=duration;
		this.priceEffect=priceEffect;
	}
	
	/**
	 * Returns brief description of the event
	 * @return Brief description of the event
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Returns the name of the event
	 * @return event's name
	 */
	public String getName()
	{
		return name;
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

}
