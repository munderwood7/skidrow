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
	public Event(String name, String description)
	{
		this.name=name;
		this.description=description;
		this.duration=0;
	}
	public Event(String name, int duration, String description, int priceEffect)
	{
		this.name=name;
		this.description=description;
		this.duration=duration;
		this.priceEffect=priceEffect;
	}
	public String getDescription()
	{
		return description;
	}
	public String getName()
	{
		return name;
	}
	public int getStepNum()
	{
		return stepNum;
	}
	public void setStepNum(int stepNum)
	{
		this.stepNum= stepNum;
	}
	public int getDuration()
	{
		return duration;
	}
	public void setDuration(int duration)
	{
		this.duration= duration;
	}
	public int getPriceEffect()
	{
		return priceEffect;
	}

}
