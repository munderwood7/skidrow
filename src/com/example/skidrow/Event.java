package com.example.skidrow;
/**
 * Class that represents an event created by the simulator
 * @author apavia3
 *
 */
public class Event {
	private String description;
	private String name;
	private int duration;
	public Event(String name, int duration, String description){
		this.name=name;
		this.description=description;
		this.duration=duration;
	}
	public String getDescription(){
		return description;
	}
	public String getName(){
		return name;
	}
	public int getDuration(){
		return duration;
	}

}
