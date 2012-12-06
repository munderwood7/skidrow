package com.example.skidrow.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.skidrow.AppUtil;
import com.example.skidrow.Event;
import com.example.skidrow.R;
import com.example.skidrow.RandomEventGenerator;
import com.example.skidrow.Ship;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class PlayerStatsActivity extends ListActivity {
	public static final ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
	private RandomEventGenerator datasource;
	Button saveButton;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_stats);
        
        datasource = new RandomEventGenerator(this);
        datasource.open();
        
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.row_event,
                new String[] {"eventName","drug","deltaPrice","city","termination"},
                new int[] {R.id.eventName,R.id.eventDrugDynamic,R.id.eventPriceDynamic, R.id.eventCityDynamic,R.id.eventTerminationDynamic}
                );
        
	     setListAdapter(adapter);
	     refreshBottomStats();
	     fillEventsList();
        
        
        
        
        
        
    }
    
    /**
     * Fills the player stats view of the layout with the appropriate values.
     */
    public void fillEventsList(){
    	Event[] events=datasource.getAllCurrentEventsSortedByInitialStepArr();
    	Log.i("PlayerStatsActivity","Number of elements returned by datasource.getAllCurrentEventsSortedByInitialStepArr(): "+events.length);
        for(int i=0;i<events.length;i++){
                HashMap<String,String> temp=datasource.getHashMapOfEvent(events[i]);
                System.out.println("eventName: "+temp.get("eventName"));
                System.out.println("price effect: "+temp.get("deltaPrice"));
                list.add(temp);
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
                case R.id.repairShopView:
                    intent = new Intent(this, RepairShopActivity.class);
                    break;
                default:
                        intent = new Intent(this, PlayerStatsActivity.class);
                        break;
        }
        startActivity(intent);
    }
    
    @Override
    /**
     * This method renders menu options for this activity
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_player_stats, menu);
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
    public void saveStates2(View view){ 
    	View parent = (View)this.findViewById(R.id.player_stats_root);
    	Bitmap screen = Bitmap.createBitmap(parent.getWidth(), parent.getHeight(), Bitmap.Config.ARGB_8888);
    	Canvas canvas = new Canvas(screen);
    	parent.draw(canvas);
    	AppUtil.saveState(this, screen);
    }
    protected void onResume() {
        datasource.open();
        super.onResume();
      }

      @Override
      protected void onPause() {
        datasource.close();
        super.onPause();
      }
}
