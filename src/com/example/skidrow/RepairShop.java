package com.example.skidrow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import android.util.Log;

public class RepairShop implements Serializable{
	private Map<Ship, Integer> shipList = new HashMap<Ship, Integer>();
	private Map<String, Ship> nameList = new HashMap<String, Ship>();
	private Map<String, Integer> priceList = new HashMap<String, Integer>();
	protected static final int ERROR=0;
	protected static final int ENOUGH_MONEY=1;
	protected static final int SAME_CAR=2;
	protected static final int NOT_ENOUGH_MONEY=3;

	//Tag for logcat
    protected static final String TAG = "RepairShop";
    //True if we want to debug false otherwise
    private boolean Debug=true;
	public RepairShop(){
		generateShips();
	}
	
	/**
	 * This method fills the number of each good available at a given market.
	 */
	private void generateShips(){
		//public Ship(String shipName, int armour, int respect, int hiddenStorage, boolean plateChanger, int turbo, int gunDamage, int fuelCapacity, double fuelEfficiency, int speed){
		shipList.put(new Ship("1977 Tokyo Sedan", 10, 1, 1, false, 0, 1, 1000, 5,80,10),0);
		shipList.put(new Ship("1986 Detroit SUV", 30, 1, 2, false, 0, 2, 220, 6,70,16),2000);
		shipList.put(new Ship("2006 Beijing Sedan", 20, 2, 2, false, 1, 3, 180, 4,120,12),6000);
		shipList.put(new Ship("2005 Detroit SUV", 40, 3, 4, false, 2, 5, 230, 5,100,18),20000);
		shipList.put(new Ship("2010 Tokyo Sedan", 30, 3, 4, false, 3, 5, 210, 2,130,14),15000);
		shipList.put(new Ship("2012 Kyoto Coupe", 40, 5, 4, false, 3, 6, 210, 1,140,12),35000);
		shipList.put(new Ship("2012 Detroit SUV", 60, 7, 6, true, 3, 8, 240, 3,130,25),45000);
		shipList.put(new Ship("2012 Munich Coupe", 50, 8, 4,true, 4, 7, 210, 0.5,180,14),50000);
		shipList.put(new Ship("2013 Monaco Super Car", 60, 9, 4, true, 5, 9, 210, 0.2,220,16),80000);
		shipList.put(new Ship("2012 Mishawaka All Terrain Vehicle", 100, 10, 10, true, 4, 10, 300, 3,150,40),100000);
		shipList.put(new Ship("2013 Rome Super Car", 80, 10, 10, true, 5, 10, 210, 0.1,300,25),1500000);
		
		nameList.put("1977 Tokyo Sedan",new Ship("1977 Tokyo Sedan", 10, 1, 1, false, 0, 1, 1000, 5,80,10));
		nameList.put("1986 Detroit SUV",new Ship("1986 Detroit SUV", 30, 1, 2, false, 0, 2, 60, 6,70,16));
		nameList.put("2006 Beijing Sedan",new Ship("2006 Beijing Sedan", 20, 2, 2, false, 1, 3, 50, 4,120,12));
		nameList.put("2005 Detroit SUV",new Ship("2005 Detroit SUV", 40, 3, 4, false, 2, 5, 70, 5,100,18));
		nameList.put("2010 Tokyo Sedan",new Ship("2010 Tokyo Sedan", 30, 3, 4, false, 3, 5, 50, 2,130,14));
		nameList.put("2012 Kyoto Coupe",new Ship("2012 Kyoto Coupe", 40, 5, 4, false, 3, 6, 50, 1,140,12));
		nameList.put("2012 Detroit SUV",new Ship("2012 Detroit SUV", 60, 7, 6, true, 3, 8, 80, 3,130,25));
		nameList.put("2012 Munich Coupe",new Ship("2012 Munich Coupe", 50, 8, 4,true, 4, 7, 50, 0.5,180,14));
		nameList.put("2013 Monaco Super Car",new Ship("2013 Monaco Super Car", 60, 9, 4, true, 5, 9, 50, 0.2,220,16));
		nameList.put("2012 Mishawaka All Terrain Vehicle",new Ship("2012 Mishawaka All Terrain Vehicle", 100, 10, 10, true, 4, 10, 100, 3,150,40));
		nameList.put("2013 Rome Super Car",new Ship("2013 Rome Super Car", 80, 10, 10, true, 5, 10, 50, 0.1,300,25));
		
		priceList.put("1977 Tokyo Sedan",0);
		priceList.put("1986 Detroit SUV",2000);
		priceList.put("2006 Beijing Sedan",6000);
		priceList.put("2005 Detroit SUV", 20000);
		priceList.put("2010 Tokyo Sedan",15000);
		priceList.put("2012 Kyoto Coupe",35000);
		priceList.put("2012 Detroit SUV",45000);
		priceList.put("2012 Munich Coupe",50000);
		priceList.put("2013 Monaco Super Car",80000);
		priceList.put("2012 Mishawaka All Terrain Vehicle",100000);
		priceList.put("2013 Rome Super Car",1500000);

	}
	public String[] getShips(){
		Log.i(TAG, "Gadget list size: "+shipList.size());
		String[] arr=new String[shipList.size()];
		ArrayList<Ship> list=new ArrayList<Ship>();
		Iterator iterator = shipList.entrySet().iterator();
		Entry<Ship, Integer> entry;
		for(int i=0;i<shipList.size();i++){
			entry = (Map.Entry<Ship, Integer>)iterator.next();
			list.add((Ship)entry.getKey());
		}
		Collections.sort(list, new Comparator<Ship>() {
			public int compare(Ship a, Ship b) {
				return priceList.get(a.getShipName())-priceList.get(b.getShipName());
			}
		});
		for(int i=0;i<shipList.size();i++){
			arr[i]=((Ship) list.get(i)).getShipName();
		}
		return arr;
		
	}
	public HashMap<String,String> getHashMapOfShip(String shipName){
		HashMap<String,String> temp = new HashMap<String,String>();
		temp.put("carModel",shipName);
    	temp.put("price", "Price: $"+Integer.toString(priceList.get(shipName)));
    	temp.put("speed","Max speed: "+Integer.toString(nameList.get(shipName).getSpeed())+" mph");
		return temp;
	}
	public Ship getShipByName(String shipName){
		return nameList.get(shipName);
	}
	public double getGasPrice(int techLevel){
		return 2+(3/(techLevel+1));
	}
	public void getGas(double amount){
		
	}
	public int getShipPrice(Ship ship){
		if(priceList.get(ship.getShipName())==null)Log.i(TAG,"Price of " +ship.getShipName()+" is not in price list.");
		return priceList.get(ship.getShipName());
	}
	public int buyShip(Ship newShip){
		int price;
		Log.i("RepairShop", "Trying to buy: " + newShip.getShipName());
		if(nameList.containsKey(newShip.getShipName())){
			price=priceList.get(newShip.getShipName());
			if(price<AppUtil.game.getMoney()&&!AppUtil.game.getShip().getShipName().equals(newShip.getShipName())){
				AppUtil.game.setShip(newShip);
				AppUtil.game.setMoney(AppUtil.game.getMoney()-priceList.get(newShip.getShipName()));
				if(Debug) Log.i(TAG, "Bought a new car: "+newShip.getShipName() + " Old car: "+AppUtil.game.getShip().getShipName());
				return ENOUGH_MONEY;
			} else if(AppUtil.game.getShip().getShipName().equals(newShip.getShipName())){
				if(Debug) Log.i(TAG, "The user already has this car.");
				return SAME_CAR;
			}
			else{
				if(Debug) Log.i(TAG, "Not enough money to buy this carp.");
				return NOT_ENOUGH_MONEY;
			}	
			
		} else{
			if(Debug) Log.e(TAG, "This type of ship does not exist.");
			return ERROR;
		}
	}
}
