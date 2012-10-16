package com.example.skidrow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MapActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        fillCityList();
    }
    
    /**
     * Fills the listview with the cities
     */
    public void fillCityList(){
    	ListView cities = (ListView)this.findViewById(R.id.cityList);
    	cities.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AppUtil.game.getCityList()));
    	
    	//Set the listener for when an item is selected
    	cities.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				updateDisplayCity(position);
			} 		
    	});
    }
    
    /**
     * Updates the city that is on display in the city preview window.
     * 
     * @param index Index of the city in the citiesList
     */
    public void updateDisplayCity(int index){
    	TextView name = (TextView)this.findViewById(R.id.crntCityName);
    	TextView techLevel = (TextView)this.findViewById(R.id.crntTechLevel);
    	TextView politicalSystem = (TextView)this.findViewById(R.id.crntPoliticalSystem);
    	TextView distance = (TextView)this.findViewById(R.id.crntDistance);
    	String[] info = AppUtil.game.getCityInfo(index);
    	
    	name.setText(info[0]);
    	techLevel.setText(info[1]);
    	//politicalSystem.setText(info[2]);
    	//distance.setText(info[3]);
    }
    
    /**
     * Makes the player travel to the chosen location if there is enough gas.
     * 
     * @param view Travel button
     */
    public void travel(View view){
    	
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
    		default:
    			intent = new Intent(this, PlayerStatsActivity.class);
    			break;
    	}
    	startActivity(intent);
    }
}