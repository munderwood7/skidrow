package com.example.skidrow.activities;

import com.example.skidrow.AppUtil;
import com.example.skidrow.R;
import com.example.skidrow.R.id;
import com.example.skidrow.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class PlayerStatsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_stats);
        
        fillPlayerStats();
    }
    
    public void fillPlayerStats(){
    	TextView playerFighting = (TextView)this.findViewById(R.id.playerFightingLevel);
    	TextView playerDealing = (TextView)this.findViewById(R.id.playerDealerLevel);
    	TextView playerCommunications = (TextView)this.findViewById(R.id.playerCommunicationsLevel);
    	TextView playerDriving = (TextView)this.findViewById(R.id.playerDriverLevel);
    	TextView playerCity = (TextView)this.findViewById(R.id.playerCity);
    	TextView playerName = (TextView)this.findViewById(R.id.playerName);
    	TextView playerHealth = (TextView)this.findViewById(R.id.playerHealth);
    	TextView playerMoney = (TextView)this.findViewById(R.id.playerMoney);
    	String[] info = AppUtil.game.getPlayerStatInfo();
    	
    	playerCommunications.setText(info[0]);
    	playerFighting.setText(info[1]);
    	playerDriving.setText(info[2]);
    	playerDealing.setText(info[3]);
    	playerCity.setText(info[4]);
    	playerName.setText(info[6]);
    	playerMoney.setText(info[7]);
    }
    
    /**
     * Changes the view of the game between the four main game information screens (player stats, market, shop, and map).
     * 
     * @param view Layout that the user wishes to navigate to
     */
    public void changeGameLayout(View view){
    	int viewId = view.getId();
    	
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
    		default:
    			intent = new Intent(this, PlayerStatsActivity.class);
    			break;
    	}
    	startActivity(intent);
    }
}
