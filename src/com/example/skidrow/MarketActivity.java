package com.example.skidrow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MarketActivity extends Activity {

	public enum Person { PLAYER, MARKET }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market);
        
        Spinner buySell = (Spinner)this.findViewById(R.id.buySellChoice);
        //Set the listener to see if the user switches to sell or vice versa 
        buySell.setOnItemSelectedListener(new OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				if(position==0){
					populateScreen(Person.MARKET);
				}
				else{
					populateScreen(Person.PLAYER);
				}				
			}
			public void onNothingSelected(AdapterView<?> arg0) {}       	
			});
    }
    
    /**
     * This method switches the state of the view based on whether the user is buying or selling.
     * 
     * @param person The person with stats displayed on the screen (Player or Market)
     */
    private void populateScreen(Person person){
    	TextView whoGoods = (TextView)this.findViewById(R.id.whoGoods);
    	if(person == Person.PLAYER){
    		whoGoods.setText("Your Goods");
    	}
    	else{
    		whoGoods.setText("Market Goods");
    	}
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
