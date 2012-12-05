package com.example.skidrow.activities;

import com.example.skidrow.AppUtil;
import com.example.skidrow.R;
import com.example.skidrow.Ship;
import com.example.skidrow.activities.MarketActivity.Person;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class ShipRepairShopActivity extends Activity {
	private ListView shipsList;;
	protected static final String TAG = "ShipRepairShopActivity";
	protected static final int ERROR=0;
	protected static final int ENOUGH_MONEY=1;
	protected static final int SAME_CAR=2;
	protected static final int NOT_ENOUGH_MONEY=3;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ship_repair_shop);
        fillShipShopInfo();
        
    }
	public void changeGameLayout(View view){
    	int viewId = view.getId();
    	Log.i(TAG, "ViewId ShipRepairShopActivity: " +viewId );
    	
    	Intent intent;
    	switch(viewId){
    		case R.id.playerStatsView:
    			intent = new Intent(this, PlayerStatsActivity.class);
    			break;
    		case R.id.cityView:
    			intent = new Intent(this, MapActivity.class);
    			break;
    		case R.id.marketView:
    			intent = new Intent(this, MarketActivity.class);
    			break;
    		case R.id.repairShopView:
                intent = new Intent(this, RepairShopActivity.class);
                break;
    		case R.id.shipRepairShopView:
    			intent = new Intent(this, ShipRepairShopActivity.class);
    			break;
    		default:
    			intent = new Intent(this, PlayerStatsActivity.class);
    			break;
    	}
    	startActivity(intent);
    }
	private void fillShipShopInfo() {
		// TODO Auto-generated method stub
    	shipsList = (ListView)this.findViewById(R.id.shipsList);
    	TextView playerMoney = (TextView)this.findViewById(R.id.playerMoney);
    	String[] info = AppUtil.game.getPlayerStatInfo();
    	String playerInfo[] = AppUtil.game.getPlayerStatInfo();
    	shipsList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AppUtil.game.getNamesOfShips()));
    	playerMoney.setText("Money: "+info[7]);
    	shipsList.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
				TextView ship = (TextView)view;
				selectShip(ship.getText().toString());
			}      
        });
		
	}
	 private void selectShip(String ship){
		 //String shipName, int armour, int respect, int hiddenStorage, boolean plateChanger, int turbo, int gunDamage, int fuelCapacity, double fuelEfficiency, int speed, int cargoSpace
		 LayoutInflater inflater = this.getLayoutInflater();
	     final LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.ship_popup, null);
	     final TextView shipPrice = (TextView)layout.findViewById(R.id.shipPrice);
		 final TextView armour = (TextView)layout.findViewById(R.id.armour);
		 final TextView respect = (TextView)layout.findViewById(R.id.respect);
		 final TextView hiddenStorage = (TextView)layout.findViewById(R.id.hiddenStorage);
		 final TextView plateChanger= (TextView)layout.findViewById(R.id.plateChanger);
		 final TextView turbo = (TextView)layout.findViewById(R.id.turbo);
		 final TextView gunDamage = (TextView)layout.findViewById(R.id.gunDamage);
		 final TextView fuelCapacity = (TextView)layout.findViewById(R.id.fuelCapacity);
		 final TextView fuelEfficiency = (TextView)layout.findViewById(R.id.fuelEfficiency);
		 final TextView speed = (TextView)layout.findViewById(R.id.speed);
		 final TextView cargoSpace = (TextView)layout.findViewById(R.id.cargoSpace);
		 final Ship car=AppUtil.game.getShipByName(ship);
		 
		 Log.i("ShipRepairSHopActivity", "Selected car: " + ship);
		 shipPrice .setText("Price: $"+AppUtil.game.getShipPrice(car));
		 speed.setText("Max Speed: "+ car.getSpeed());
		 armour.setText("Armour: "+car.getArmour());
		 respect.setText("Respect: "+car.getRespect());
		 hiddenStorage.setText("Hidden Storage Containers: "+car.getHiddenStorage());
		 if(car.isPlateChanger()){
			 plateChanger.setText("Plate Changer: Included");
		 }else{
			 plateChanger.setText("Plate Changer: Not included");
		 }
		 turbo.setText("Turbo level: "+car.getTurbo());
		 gunDamage.setText("Fire power: "+car.getGunDamage());
		 fuelCapacity.setText("Fuel Capacity: "+ car.getFuelCapacity());
		 fuelEfficiency.setText("Fuel Efficiency: "+car.getFuelEfficiency());
		 speed.setText("Speed: "+car.getSpeed());
		 cargoSpace.setText("Cargo Space: "+car.getMaxCargoSpace());
		 AlertDialog.Builder popup = new AlertDialog.Builder(this);
		 String title;
		 if(AppUtil.game.getShip().getShipName().equals(ship))
			 title=ship+" (Current Car)";
		 else 
			 title=ship;
	     popup.setView(layout).setTitle(title).setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
	    	public void onClick(DialogInterface dialog, int which) {
						
					}
					
			 }).setPositiveButton("Buy", new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int which) {
					    Log.i("ShipRepairSHopActivity", "Trying to buy: " + car.getShipName());
						int buy=AppUtil.game.buyShip(car);
						fillShipShopInfo();
						if(ENOUGH_MONEY==buy) showMessage("Congrats! You just boought a "+car.getShipName()+".");
						else if(NOT_ENOUGH_MONEY==buy)  showMessage("You do not have enough money to buy a "+car.getShipName()+".");
						else if(SAME_CAR==buy)  showMessage("You already have this car.");
						
					}
			});
	        
	        popup.create().show();
	    }
	 	public void showMessage(String message){
	 		LayoutInflater inflater = this.getLayoutInflater();
	 		final LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.message_popup, null);
	 		AlertDialog.Builder popup2 = new AlertDialog.Builder(this, 0);
	 		popup2.setView(layout).setTitle(message).setNegativeButton("Ok", new DialogInterface.OnClickListener(){
    			
		    	 public void onClick(DialogInterface dialog, int which) {
							
						}
						
				 });
	 		popup2.create().show();
	 	}
}
