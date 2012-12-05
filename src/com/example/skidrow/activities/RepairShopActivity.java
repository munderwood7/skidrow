package com.example.skidrow.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.skidrow.AppUtil;
import com.example.skidrow.R;
import com.example.skidrow.activities.MarketActivity.Person;

public class RepairShopActivity extends Activity{
	protected static final String TAG = "RepairShopActivity";
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repair_shop);
        fillRepairShopInfo();
        
    }
	public void changeGameLayout(View view){
    	int viewId = view.getId();
    	Log.i(TAG, "ViewId RepairShopActivity: " +viewId );
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
    		
    		case R.id.gadgetRepairShopView:
    			intent = new Intent(this, GadgetRepairShopActivity.class);
    			break;
    		case R.id.recruitRepairShopView:
    			intent = new Intent(this, RecruitRepairShopActivity.class);
    			break;
   			
    		default:
    			intent = new Intent(this, PlayerStatsActivity.class);
    			break;
    	}
    	startActivity(intent);
    }
	public void fillRepairShopInfo(){
        //TextView playerMoney = (TextView)this.findViewById(R.id.moneyTextViewRepairShop);
        TextView gadgetText = (TextView)this.findViewById(R.id.gadgetSpecs);
        TextView shipText = (TextView)this.findViewById(R.id.shipSpecs);
        TextView recruitsText = (TextView)this.findViewById(R.id.recruitSpecs);
        TextView gasText = (TextView)this.findViewById(R.id.gasSpecs);
        String[] info = AppUtil.game.getPlayerStatInfo();
        
        //playerMoney.setText("Money: "+info[7]);
        gasText.setText("Gas Left: "+AppUtil.game.getGas()+"\nGas Efficiency: "+AppUtil.game.getFuelEfficiency()+"\nMax Distance: "+String.format("%.2f", AppUtil.game.getGas()/AppUtil.game.getFuelEfficiency()));
        gadgetText.setText("Fire Power: " +AppUtil.game.getShip().getGunDamage()+"\nArmour: "+AppUtil.game.getShip().getArmour()+"\nRespect: "+AppUtil.game.getShip().getRespect());
        shipText.setText("Your Car: "+AppUtil.game.getShip().getShipName()+"\nSpeed: "+AppUtil.game.getShip().getSpeed()+" mph\nTurbo level: "+AppUtil.game.getShip().getTurbo()+"\nSecret Containers: "+AppUtil.game.getShip().getHiddenStorage());
        recruitsText.setText("Communication Skills: "+info[0]+"\nDriver Skills: "+info[1]+"\nFighting Skills: "+info[2]+"\nDealer Skills: "+info[3]);
        refreshBottomStats();
    }
	public void gettingGas(View view){
		if(AppUtil.game.getShip().getFuelCapacity()!=(int) AppUtil.game.getGas()){
			gasTransaction(view);
		} else{
			showMessage("Your tank is full.");
		}
	}
	public void gasTransaction(View view){
    	
    	final double gasPrice=AppUtil.game.getGasPrice(AppUtil.game.getCurrentCity());
    	final double availableSpace=AppUtil.game.getShip().getFuelCapacity()-AppUtil.game.getGas();
    	//Setting all the appropriate listeners for the dialog layout
    	LayoutInflater inflater = this.getLayoutInflater();
    	final RelativeLayout layout = (RelativeLayout)inflater.inflate(R.layout.buy_gas_popup, null);
    	final TextView goodCost = (TextView)layout.findViewById(R.id.fuelCost);
    	final TextView quantity = (TextView)layout.findViewById(R.id.quantityValue);
    	final TextView playerMoneyView = (TextView)layout.findViewById(R.id.playerMoney);
    	final TextView carGas = (TextView)layout.findViewById(R.id.availableFuel);
    	final TextView textView2 = (TextView)layout.findViewById(R.id.textView7);
    	textView2.setText("Available: ");
    	goodCost.setText("$"+gasPrice);
    	String playerMoneyStr = AppUtil.game.getPlayerStatInfo()[7];
    	playerMoneyView.setText(playerMoneyStr);
    	carGas.setText(String.format("%.2f", availableSpace));
    	SeekBar slider = (SeekBar)layout.findViewById(R.id.buySellQuantity);
		double playerMoney = Integer.parseInt((String) playerMoneyStr.subSequence(1, playerMoneyStr.length()));
		carGas.setText(String.format("%.2f", Math.min(Math.max(AppUtil.game.getGas(),0),AppUtil.game.getShip().getFuelCapacity())));
		quantity.setText("0");
		playerMoneyView.setText("$"+String.format("%.2f",playerMoney));
    	slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				double buySellQuant =  ((progress*availableSpace)/100);
				Log.i("GasReapirShop","Progess: "+buySellQuant);
				String playerMoneyStr = AppUtil.game.getPlayerStatInfo()[7];
				double playerMoney = Integer.parseInt((String) playerMoneyStr.subSequence(1, playerMoneyStr.length()));
				carGas.setText(String.format("%.2f", Math.min(Math.max(AppUtil.game.getGas()+buySellQuant,0),AppUtil.game.getShip().getFuelCapacity())));
				quantity.setText(String.format("%.2f", buySellQuant));
				playerMoney = playerMoney - buySellQuant * gasPrice;
				playerMoneyView.setText("$"+String.format("%.2f",playerMoney));
			}
		});
    	
        AlertDialog.Builder popup = new AlertDialog.Builder(this);
        popup.setView(layout)
        	.setTitle("Buy Gas")
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
				
			})
			.setPositiveButton("Buy", new DialogInterface.OnClickListener() {
				
				
				
				public void onClick(DialogInterface dialog, int which) {
					buyGas(Double.parseDouble(quantity.getText().toString()));
				}
			});
        popup.create().show();
    }
	public void buyGas(double quant) {
		 if(quant*AppUtil.game.getGasPrice(AppUtil.game.getCurrentCity())<AppUtil.game.getMoney()){
			 AppUtil.game.setMoney((int)(AppUtil.game.getMoney()-quant*AppUtil.game.getGasPrice(AppUtil.game.getCurrentCity())));
			 AppUtil.game.setGas(AppUtil.game.getGas()+quant);
			 showMessage("Transaction Completed");
			 fillRepairShopInfo();
		 } else{
			 showMessage("Not enough money!");
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
	public static Context getContext(){
		return RepairShopActivity.getContext();
	}
	
}
