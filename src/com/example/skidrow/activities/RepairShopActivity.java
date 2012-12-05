package com.example.skidrow.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
    		case R.id.gasRepairShopView:
    			intent = new Intent(this, GasRepairShopActivity.class);
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
        TextView playerMoney = (TextView)this.findViewById(R.id.moneyTextViewRepairShop);
        TextView gadgetText = (TextView)this.findViewById(R.id.gadgetSpecs);
        TextView shipText = (TextView)this.findViewById(R.id.shipSpecs);
        TextView recruitsText = (TextView)this.findViewById(R.id.recruitSpecs);
        TextView gasText = (TextView)this.findViewById(R.id.gasSpecs);
        String[] info = AppUtil.game.getPlayerStatInfo();
        
        playerMoney.setText("Money: "+info[7]);
        gasText.setText("Gas Left: "+AppUtil.game.getGas()+"\nGas Efficiency: "+AppUtil.game.getFuelEfficiency()+"\nMax Distance: "+String.format("%.2f", AppUtil.game.getShip().getFuelCapacity()/AppUtil.game.getFuelEfficiency()));
        gadgetText.setText("Fire Power: " +AppUtil.game.getShip().getGunDamage()+"\nArmour: "+AppUtil.game.getShip().getArmour());
        shipText.setText("Your Car: "+AppUtil.game.getShip().getShipName()+"\nSpeed: "+AppUtil.game.getShip().getSpeed()+"\nTurbo level: "+AppUtil.game.getShip().getTurbo()+"\nSecret Containers: "+AppUtil.game.getShip().getHiddenStorage());
        recruitsText.setText("Communication Skills: "+info[0]+"\nDriver Skills: "+info[1]+"\nFighting Skills: "+info[2]+"\nDealer Skills: "+info[3]);
        
    }
	public static Context getContext(){
		return RepairShopActivity.getContext();
	}
	
}
