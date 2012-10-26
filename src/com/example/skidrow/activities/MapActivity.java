package com.example.skidrow.activities;

import java.text.DecimalFormat;

import com.example.skidrow.AppUtil;
import com.example.skidrow.City;
import com.example.skidrow.Event;
import com.example.skidrow.R;
import com.example.skidrow.RandomEventGenerator;
import com.example.skidrow.R.id;
import com.example.skidrow.R.layout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MapActivity extends Activity {
	
	//Tag for logcat
    protected static final String TAG = "MapActivity";
    //True if we want to debug false otherwise
    private boolean D=true;
    private RandomEventGenerator eventGen; 
    private City currentCity;
    private City displayedCity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        currentCity=AppUtil.game.getCurrentCity();
        eventGen= new RandomEventGenerator();
        eventGen.generateEvent();
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
    	RelativeLayout cityInfo = (RelativeLayout)this.findViewById(R.id.cityInfo);
    	TextView name = (TextView)this.findViewById(R.id.crntCityName);
    	TextView techLevel = (TextView)this.findViewById(R.id.crntTechLevel);
    	TextView resources = (TextView)this.findViewById(R.id.crntResources);
    	TextView distance = (TextView)this.findViewById(R.id.crntDistance);
    	String[] info = AppUtil.game.getCityInfo(index);
    	displayedCity=AppUtil.game.getCity(index);
    	
    	if(cityInfo.getVisibility()==View.GONE){
    		cityInfo.setVisibility(View.VISIBLE);
    	}
    	
    	name.setText(info[0]);
    	techLevel.setText(info[1]);
    	resources.setText(info[2]);
    	distance.setText(getDistance(displayedCity));
    }
    /**
     * Gets the distance between the current city and the city being investigated
     * @return String containing the distance between the current city and the selected city
     * @author apavia3
     */
    public String getDistance(City city){
    	int[] displayedCityLocation=city.getLocation();
		int[] currentCityLocation=AppUtil.game.getCurrentCity().getLocation();
		double hypotenuse= Math.sqrt(Math.pow(displayedCityLocation[0]-currentCityLocation[0], 2)+Math.pow(displayedCityLocation[1]-currentCityLocation[1], 2));
    	return String.format("%.3f", hypotenuse);
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
    
    /**
     * Overrides the back button to use as a function in the activity. Pressing the back button
     * closes the city preview and puts the city list in fullview.
     */
    @Override
    public void onBackPressed(){
    	RelativeLayout cityInfo = (RelativeLayout)this.findViewById(R.id.cityInfo);
    	
    	cityInfo.setVisibility(View.GONE);
    }
    /**
     * Makes the player travel to the chosen location if there is enough gas.
     * 
     * @param view Travel button
     */
    public void travel(View view){
    	currentCity=displayedCity;
    	if(D) Log.i(TAG, "new current city -> " + currentCity.getName());
    	AppUtil.game.setCurrentCity(displayedCity);
    	AppUtil.game.makeMove();
    	TextView distance = (TextView)this.findViewById(R.id.crntDistance);
    	distance.setText(getDistance(displayedCity));
    	Event e;
		if(eventGen.checkStartEvent()){
			//change the market values according to the event 
			//show toast
			e=eventGen.peek();
			if(D) Log.i(TAG, "New event starts-> " + e.getName());
			AppUtil.displayMessage(this,e.getName()+"\n"+e.getDescription());
			
		}
		else if(eventGen.checkEndEvent()){
			//change the market values back to the original values
			//dequeue event
			//generate new event
			e=eventGen.pop();
			if(D) Log.i(TAG, "Event ends-> " + e.getName());
			eventGen.generateEvent();
		}
    }
}
