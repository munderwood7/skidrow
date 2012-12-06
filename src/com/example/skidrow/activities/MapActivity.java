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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MapActivity extends Activity {
	
	
	/**
	 * tag for logcat
	 */
    protected static final String TAG = "MapActivity";
    /**
     * True if we want to debug false otherwise
     */
    private boolean Debug=true;
    
    /**
     * Current city where player is at
     */
    private City currentCity;
    /**
     * City to be shown 
     */
    private City displayedCity;
    private RandomEventGenerator eventGen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        currentCity=AppUtil.game.getCurrentCity();
        eventGen=new RandomEventGenerator(this);
        //eventGen= new RandomEventGenerator();
        //eventGen.generateEvent();
        fillCityList();
    }
    @Override
    protected void onResume() {
      eventGen.open();
      super.onResume();
    }

    @Override
    protected void onPause() {
      eventGen.close();
      super.onPause();
    }

    /**
     * Fills the listview with the cities
     */
    public void fillCityList(){
    	refreshBottomStats();
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
    	refreshBottomStats();
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
    	return AppUtil.game.getDistance(city);
    }
    
    
    
    /**
     * Changes the view of the game between the four main game information screens (player stats, market, shop, and map).
     * 
     * @param view Layout that the user wishes to navigate to
     */
    public void changeGameLayout(View view){
    	int viewId = view.getId();
    	Log.i(TAG, "ViewId MapActivity: " +viewId );
    	
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
		if(AppUtil.game.getStep()==1){
			eventGen.generateEvent();
		}
    	if(AppUtil.game.checkGas(displayedCity)){
    		if(!AppUtil.game.getCurrentCity().getName().equals(displayedCity.getName())){
	    		Event e=eventGen.peek();
	    		if(e!=null)
	    			if(Debug) Log.i(TAG,"Current event: " + e.getDescription() +"\nCurrent move: "+AppUtil.game.getStep()+"\nStarts: "+eventGen.getNextEventStepNum()+"\nEnds: "+(eventGen.getNextEventStepNum()+eventGen.getNextEventDuration()));
	            AppUtil.game.makeMove(displayedCity, eventGen.findNextEvent());
		    	currentCity=displayedCity;
		    	if(Debug) Log.i(TAG, "Gas left: " + AppUtil.game.getGas());
		    	if(Debug) Log.i(TAG, "new current city -> " + currentCity.getName());
		    	TextView distance = (TextView)this.findViewById(R.id.crntDistance);
		    	distance.setText(getDistance(displayedCity));
				if(eventGen.checkStartEvent()){
					//change the market values according to the event 
					//show toast
					e=eventGen.peek();
					if(Debug) Log.i(TAG, "New event starts-> " + e.getName());
					refreshBottomStats();
					showMessage2("You are now in " + currentCity.getName()+".","Newsflash!\n\n"+eventGen.peek().getDescription());
	
					
				}
				else if(eventGen.checkEndEvent()){
					//change the market values back to the original values
					//dequeue event
					//generate new event
					e=eventGen.pop();
					if(Debug) Log.i(TAG, "Event ends-> " + e.getName());
					showMessage2("You are now in " + currentCity.getName(),"Newsflash!\n\n"+ eventGen.peek().getTerminationMessage() );
					eventGen.generateEvent();
				} else{
					showMessage2("You are now in " + currentCity.getName()+".","");
				}
				AppUtil.game.generateMarket();
    		}else{
    			showMessage2("You are already in "+ currentCity.getName()+".","");
    		}
    		
    	} else{
    		showMessage2("You do not have enough fuel to reach this destination.","");
    	}
    	refreshBottomStats();
    }
    
    @Override
    /**
     * This method renders menu options for this activity
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_map, menu);
        return true;
    }
    
    @Override
    /**
     * This method what will be performed when menu item is perform 
     * only item at this moment is save
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.saveGame:
            	//AppUtil.displayError(this, "Game was saved");
            	saveStates2(this.getCurrentFocus());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void saveStates2(View view){ 
    	View parent = (View)this.findViewById(R.id.map_view);
    	Bitmap screen = Bitmap.createBitmap(parent.getWidth(), parent.getHeight(), Bitmap.Config.ARGB_8888);
    	Canvas canvas = new Canvas(screen);
    	parent.draw(canvas);
    	AppUtil.saveState(this, screen);

    }
    public void showMessage2(String title,final String message){
 		LayoutInflater inflater = this.getLayoutInflater();
 		final LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.message_popup, null);
 		AlertDialog.Builder popup2 = new AlertDialog.Builder(this, 0);
 		final TextView content = (TextView)layout.findViewById(R.id.message_content);
 		content.setText(message);
 		popup2.setView(layout).setTitle(title).setNegativeButton("Ok", new DialogInterface.OnClickListener(){
			
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
}
