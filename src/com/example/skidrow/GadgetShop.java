package com.example.skidrow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.util.Log;

public class GadgetShop {
	private Map<Gadget, Integer> GadgetList = new HashMap<Gadget, Integer>();
	private Map<String, Gadget> nameList = new HashMap<String, Gadget>();
	private Map<String, Integer> priceList = new HashMap<String, Integer>();
	private String[] gadgetNamesSorted;
	//Tag for logcat
    protected static final String TAG = "RepairShop";
    //True if we want to debug false otherwise
    private boolean Debug=true;
	public GadgetShop(){
	}
	
	/**
	 * This method fills the number of each good available at a given market.
	 */
	public void generateGadgets(){
		GadgetList.put(new Gadget("Basic Tuning", 4, 4, 2, 8, 10, 15, 7),4500);
		GadgetList.put(new Gadget("Laser Gun", 5, 10, 0, 20, 0, 0, 0),20000);
		GadgetList.put(new Gadget("Nitro Turbo", 0, 8, 15, 0, 0, 100, 0),15000);
		GadgetList.put(new Gadget("Machine Gun",3, 7, 0, 15, 0, 0, 0),14000);
		GadgetList.put(new Gadget("Bulltproof Armour", 80, 9 , 0, 0, 0, 0, 0),23000);
		GadgetList.put(new Gadget("Cargo Extender", 0, 2, 0, 0, 0, 0, 40),5000);
		GadgetList.put(new Gadget("Magic Gadget", 5, 5, 4, 4, 10, 25, 5),9000);
		GadgetList.put(new Gadget("Pimp My Car", 20, 9, 20, 6, 30, 40, 8),17000);
		GadgetList.put(new Gadget("Spy Tuning", 30, 6, 4, 3, 6, 35, 3),12000);
		GadgetList.put(new Gadget("Semi-Automatic Gun", 5, 7, 0, 10, 0, 0, 5),3000);
		
		
		nameList.put("Laser Gun",new Gadget("Laser Gun", 5, 10, 0, 20, 0, 0, 0));
		nameList.put("Nitro Turbo",new Gadget("Nitro Turbo", 0, 8, 15, 0, 0, 100, 0));
		nameList.put("Machine Gun",new Gadget("Machine Gun",3, 7, 0, 15, 0, 0, 0));
		nameList.put("Bulltproof Armour",new Gadget("Bulltproof Armour", 80, 9 , 0, 0, 0, 0, 0));
		nameList.put("Cargo Extender",new Gadget("Cargo Extender", 0, 2, 0, 0, 0, 0, 40));
		nameList.put("Magic Gadget",new Gadget("Magic Gadget", 5, 5, 4, 4, 10, 25, 5));
		nameList.put("Pimp My Car",new Gadget("Pimp My Car", 20, 9, 20, 6, 30, 40, 8));
		nameList.put("Basic Tuning",new Gadget("Basic Tuning", 4, 4, 2, 8, 10, 15, 7));
		nameList.put("Spy Tuning",new Gadget("Spy Tuning", 30, 6, 4, 3, 6, 35, 3));
		nameList.put("Semi-Automatic Gun",new Gadget("Semi-Automatic Gun", 5, 7, 0, 10, 0, 0, 5));
		
		priceList.put("Laser Gun",20000);
		priceList.put("Nitro Turbo",15000);
		priceList.put("Machine Gun",14000);
		priceList.put("Bulltproof Armour",23000);
		priceList.put("Cargo Extender", 50000);
		priceList.put("Magic Gadget",9000);
		priceList.put("Pimp My Car",17000);
		priceList.put("Basic Tuning",4500);
		priceList.put("Spy Tuning",12000);
		priceList.put("Semi-Automatic Gun",3000);
		
		
		

	}
	public String[] getGadgets(){
		Log.i(TAG, "Gadget list size: "+GadgetList.size());
		String[] arr=new String[GadgetList.size()];
		ArrayList<Gadget> list=new ArrayList<Gadget>();
		Iterator iterator = GadgetList.entrySet().iterator();
		Map.Entry<Gadget, Integer> entry;
		for(int i=0;i<GadgetList.size();i++){
			entry = (Map.Entry<Gadget, Integer>)iterator.next();
			list.add((Gadget)entry.getKey());
		}
		Collections.sort(list, new Comparator<Gadget>() {
			@Override
			public int compare(Gadget a, Gadget b) {
				return priceList.get(a.getGadgetName())-priceList.get(b.getGadgetName());
			}
		});
		for(int i=0;i<GadgetList.size();i++){
			arr[i]=((Gadget) list.get(i)).getGadgetName();
		}
		return arr;
	}
	public Gadget getGadgetByName(String gadgetName){
		return nameList.get(gadgetName);
	}
	
		
	public int getGadgetPrice(Gadget gadget){
		return priceList.get(gadget.getGadgetName());
	}
	public boolean buyGadget(Gadget newGadget){
		int price;
		Log.i(TAG, "Trying to buy: " + newGadget.getGadgetName());
		if(nameList.containsKey(newGadget.getGadgetName())){
			price=priceList.get(newGadget.getGadgetName());
			if(price<AppUtil.game.getMoney()){
				AppUtil.game.setGadget(newGadget);
				AppUtil.game.setMoney(AppUtil.game.getMoney()-priceList.get(newGadget.getGadgetName()));
				if(Debug) Log.i(TAG, "Bought a new Gadget: "+newGadget.getGadgetName());
				return true;
			} else{
				if(Debug) Log.i(TAG, "Not enough money to buy this Gadget.");
				return false;
			}	
			
		} else{
			if(Debug) Log.e(TAG, "This type of Gadget does not exist.");
			return false;
		}
	}
}
