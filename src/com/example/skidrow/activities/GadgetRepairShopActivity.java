package com.example.skidrow.activities;


import com.example.skidrow.AppUtil;
import com.example.skidrow.R;
import com.example.skidrow.Gadget;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class GadgetRepairShopActivity extends Activity {
	private ListView gadgetList;
	private final String TAG="GadgetRepairSHopActivity";
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gadget_repair_shop);
       fillGadgetShopInfo();
        
    }
	
	public void changeGameLayout(View view){
    	int viewId = view.getId();
    	//System.out.println("Layout " + viewId );
    	
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
    		case R.id.gadgetRepairShopView:
    			intent = new Intent(this, GadgetRepairShopActivity.class);
    			break;
    		default:
    			intent = new Intent(this, PlayerStatsActivity.class);
    			break;
    	}
    	startActivity(intent);
    }
	private void fillGadgetShopInfo() {
		// TODO Auto-generated method stub
		refreshBottomStats();
    	gadgetList = (ListView)this.findViewById(R.id.gadgetList);
    	TextView playerMoney = (TextView)this.findViewById(R.id.playerMoney);
    	String[] info = AppUtil.game.getPlayerStatInfo();
    	String playerInfo[] = AppUtil.game.getPlayerStatInfo();
    	gadgetList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AppUtil.game.getNamesOfGadgets()));
    	playerMoney.setText("Money: "+info[7]);
    	gadgetList.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
				TextView gadget = (TextView)view;
				selectGadget(gadget.getText().toString());
			}      
        });
		
	}
	
	 private void selectGadget(String Gadget){
		 
		 LayoutInflater inflater = this.getLayoutInflater();
	     final LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.gadget_popup, null);
	     final TextView GadgetName = (TextView)layout.findViewById(R.id.gadgetName);
	     final TextView GadgetPrice = (TextView)layout.findViewById(R.id.gadgetPrice);
		 final TextView armour = (TextView)layout.findViewById(R.id.armour);
		 final TextView respect = (TextView)layout.findViewById(R.id.respect);
		 final TextView turbo = (TextView)layout.findViewById(R.id.turbo);
		 final TextView gunDamage = (TextView)layout.findViewById(R.id.gunDamage);
		 final TextView fuelCapacity = (TextView)layout.findViewById(R.id.fuelCapacity);
		 final TextView speed = (TextView)layout.findViewById(R.id.speed);
		 final TextView cargoSpace = (TextView)layout.findViewById(R.id.cargoSpace);
		 final Gadget gadget=AppUtil.game.getGadgetByName(Gadget);
		 Log.i(TAG, "Selected gadget: " + Gadget);
		 GadgetName.setText(Gadget);
		 GadgetPrice .setText("Price: $"+AppUtil.game.getGadgetPrice(gadget));
		 speed.setText("Max Speed: +"+ gadget.getSpeed());
		 armour.setText("Armour: +"+gadget.getArmour());
		 respect.setText("Respect: +"+gadget.getRespect());
		 turbo.setText("Turbo Level: +"+gadget.getTurbo());
		 gunDamage.setText("Fire Power: +"+gadget.getGunDamage());
		 fuelCapacity.setText("Fuel Capacity: +"+ gadget.getFuelCapacity());
		 
		 speed.setText("Speed: +"+gadget.getSpeed());
		 cargoSpace.setText("Cargo Space: +"+gadget.getMaxCargoSpace());
		 AlertDialog.Builder popup = new AlertDialog.Builder(this);
		 
	     popup.setView(layout).setTitle("Gadget Specifications").setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
	    			
	    	 public void onClick(DialogInterface dialog, int which) {
						
					}
					
			 }).setPositiveButton("Buy", new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int which) {
					    Log.i(TAG, "Trying to buy: " + gadget.getGadgetName());
						boolean buy=AppUtil.game.buyGadget(gadget);
						fillGadgetShopInfo();
						if(buy) showMessage("Congrats! You just boought a "+gadget.getGadgetName()+".");
						else showMessage("You do not have enough money to buy a "+gadget.getGadgetName()+".");
						
					}
			});
	        
	        popup.create().show();
	    }
	 public void refreshBottomStats(){
	    	TextView moneyText = (TextView)this.findViewById(R.id.moneyDynamic);
	    	String[] playerInfo = AppUtil.game.getPlayerStatInfo();
	        moneyText.setText(playerInfo[7]);
	        TextView cityText = (TextView)this.findViewById(R.id.cityDynamic);
	        cityText.setText(AppUtil.game.getCurrentCity().getName());
	        ImageView[] arr=new ImageView[5];
	        
	        arr[0]=(ImageView)this.findViewById(R.id.heart1);
	        arr[1]=(ImageView)this.findViewById(R.id.heart2);
	        arr[2]=(ImageView)this.findViewById(R.id.heart3);
	        arr[3]=(ImageView)this.findViewById(R.id.heart4);
	        arr[4]=(ImageView)this.findViewById(R.id.heart5);
	        for(int i=0;i<arr.length;i++){
	        	arr[i].setVisibility(ImageView.INVISIBLE);
	        }
	        for(int i=0;i<(int) Math.floor(AppUtil.game.getHealth()/2);i++){
	        	arr[i].setVisibility(ImageView.VISIBLE);
	        }

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
