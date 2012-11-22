package com.example.skidrow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import com.example.skidrow.activities.RepairShopActivity;

import android.util.Log;

public class RepairShop {
	private Map<Ship, Integer> shipList = new HashMap<Ship, Integer>();
	private Map<String, Ship> nameList = new HashMap<String, Ship>();
	private Map<String, Integer> priceList = new HashMap<String, Integer>();
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
		shipList.put(new Ship("Tokyo Sedan 1977", 10, 1, 1, false, 0, 1, 1000, 5,80,10),0);
		shipList.put(new Ship("Detroit SUV 1986", 30, 1, 2, false, 0, 2, 220, 6,70,16),2000);
		shipList.put(new Ship("Beijing Sedan 2006", 20, 2, 2, false, 1, 3, 180, 4,120,12),6000);
		shipList.put(new Ship("Berlin Sedan 2000", 20, 3, 3, false, 2, 4, 210, 3,130,14),12000);
		shipList.put(new Ship("Detroit SUV 2005", 40, 3, 4, false, 2, 5, 230, 5,100,18),20000);
		shipList.put(new Ship("Tokyo Sedan 2010", 30, 3, 4, false, 3, 5, 210, 2,130,14),15000);
		shipList.put(new Ship("Kyoto Coupe 2012", 40, 5, 4, false, 3, 6, 210, 1,140,12),35000);
		shipList.put(new Ship("Detroit SUV 2012", 60, 7, 6, true, 3, 8, 240, 3,130,25),45000);
		shipList.put(new Ship("Munich Coupe 2012", 50, 8, 4,true, 4, 7, 210, 0.5,180,14),50000);
		shipList.put(new Ship("Monaco Super Car 2013", 60, 9, 4, true, 5, 9, 210, 0.2,220,16),80000);
		shipList.put(new Ship("Mishawaka All Terrain Vehicle 2012", 100, 10, 10, true, 4, 10, 300, 3,150,40),100000);
		shipList.put(new Ship("Rome Super Car 2013", 80, 10, 10, true, 5, 10, 210, 0.1,300,25),1500000);
		
		nameList.put("Tokyo Sedan 1977",new Ship("Tokyo Sedan 1977", 10, 1, 1, false, 0, 1, 1000, 5,80,10));
		nameList.put("Detroit SUV 1986",new Ship("Detroit SUV 1986", 30, 1, 2, false, 0, 2, 60, 6,70,16));
		nameList.put("Beijing Sedan 2006",new Ship("Beijing Sedan 2006", 20, 2, 2, false, 1, 3, 50, 4,120,12));
		nameList.put("Berlin Sedan 2000",new Ship("Berlin Sedan 2000", 20, 3, 3, false, 2, 4, 50, 3,130,14));
		nameList.put("Detroit SUV 2005",new Ship("Detroit SUV 2005", 40, 3, 4, false, 2, 5, 70, 5,100,18));
		nameList.put("Tokyo Sedan 2010",new Ship("Tokyo Sedan 2010", 30, 3, 4, false, 3, 5, 50, 2,130,14));
		nameList.put("Kyoto Coupe 2012",new Ship("Kyoto Coupe 2012", 40, 5, 4, false, 3, 6, 50, 1,140,12));
		nameList.put("Detroit SUV 2012",new Ship("Detroit SUV 2012", 60, 7, 6, true, 3, 8, 80, 3,130,25));
		nameList.put("Munich Coupe 2012",new Ship("Munich Coupe 2012", 50, 8, 4,true, 4, 7, 50, 0.5,180,14));
		nameList.put("Monaco Super Car 2013",new Ship("Monaco Super Car 2013", 60, 9, 4, true, 5, 9, 50, 0.2,220,16));
		nameList.put("Mishawaka All Terrain Vehicle 2012",new Ship("Mishawaka All Terrain Vehicle 2012", 100, 10, 10, true, 4, 10, 100, 3,150,40));
		nameList.put("Rome Super Car 2013",new Ship("Rome Super Car 2013", 80, 10, 10, true, 5, 10, 50, 0.1,300,25));
		
		priceList.put("Tokyo Sedan 1977",0);
		priceList.put("Detroit SUV 1986",2000);
		priceList.put("Beijing Sedan 2006",6000);
		priceList.put("Berlin Sedan 2000",12000);
		priceList.put("Detroit SUV 2005", 20000);
		priceList.put("Tokyo Sedan 2010",15000);
		priceList.put("Kyoto Coupe 2012",35000);
		priceList.put("Detroit SUV 2012",45000);
		priceList.put("Munich Coupe 2012",50000);
		priceList.put("Monaco Super Car 2013",80000);
		priceList.put("Mishawaka All Terrain Vehicle 2012",100000);
		priceList.put("Rome Super Car 2013",1500000);

	}
	public String[] getShips(){
		String[] arr=new String[shipList.size()];
		Iterator iterator = shipList.entrySet().iterator();
		Map.Entry<Ship, Integer> entry;
		for(int i=0;i<shipList.size();i++){
			entry = (Map.Entry<Ship, Integer>)iterator.next();
			arr[i]=entry.getKey().getShipName();
		}
		return arr;
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
		return priceList.get(ship.getShipName());
	}
	public boolean buyShip(Ship newShip){
		int price;
		Log.i("RepairShop", "Trying to buy: " + newShip.getShipName());
		if(nameList.containsKey(newShip.getShipName())){
			price=priceList.get(newShip.getShipName());
			if(price<AppUtil.game.getMoney()){
				AppUtil.game.setShip(newShip);
				AppUtil.game.setMoney(AppUtil.game.getMoney()-priceList.get(newShip.getShipName()));
				if(Debug) Log.i(TAG, "Bought a new ship: "+newShip.getShipName());
				return true;
			} else{
				if(Debug) Log.i(TAG, "Not enough money to buy this ship.");
				return false;
			}	
			
		} else{
			if(Debug) Log.e(TAG, "This type of ship does not exist.");
			return false;
		}
	}
}
