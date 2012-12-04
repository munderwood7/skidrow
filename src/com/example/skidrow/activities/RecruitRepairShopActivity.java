package com.example.skidrow.activities;

import com.example.skidrow.AppUtil;
import com.example.skidrow.R;
import com.example.skidrow.activities.MarketActivity.Person;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class RecruitRepairShopActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruit_repair_shop);
    	final Button recruitButton=(Button)this.findViewById(R.id.button1);
    	recruitButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				//recruitTransaction();
			}

    	});
        
    }
	public void changeGameLayout(View view){
    	int viewId = view.getId();
    	//Log.i(TAG, "ViewId ShipRepairShopActivity: " +viewId );
    	
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
    		case R.id.recruitRepairShopView:
    			intent = new Intent(this, RecruitRepairShopActivity.class);
    			break;
    		default:
    			intent = new Intent(this, PlayerStatsActivity.class);
    			break;
    	}
    	startActivity(intent);
    }


}
