package com.example.skidrow;

import java.util.HashMap;
import java.util.Map;

public class Market {
	private Map goodsList = new HashMap<Good, Integer>();
	private int techLevel;
	private Event event;
	
	public Market(int techLevel, Event event){
		this.techLevel = techLevel;
		this.event = event;
		
		generateGoods();
	}
	
	private void generateGoods(){
		
	}
}
