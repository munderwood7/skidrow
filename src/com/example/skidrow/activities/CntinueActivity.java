package com.example.skidrow.activities;

import com.example.skidrow.AppUtil;
import com.example.skidrow.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

public class CntinueActivity extends Activity {

	private Context context;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cntinue);
        
        context = this;
        
        AppUtil.forceLayout(this);
    }
    
    /**
     * This method displays the menu for switching between the three main start screens(Continue, New Game, High Score).
     * It is called when the user clicks the navigation button.
     * 
     * @param view Navigation button clicked by user
     */
    public void showLayoutMenu(View view){
        int viewID = view.getId();
        int menuID;
        
        switch(viewID){
            case R.id.newGame:
                menuID=R.menu.layout_menu_new_game;
                break;
            case R.id.cntinue:
                menuID=R.menu.layout_menu_cntinue;
                break;
            case R.id.highScore:
                menuID=R.menu.layout_menu_high_score;
                break;
            default:
                menuID=R.menu.layout_menu_new_game;
        }        
        
        PopupMenu popup = new PopupMenu(this, view);
        popup.inflate(menuID);
        popup.show(); 
    }
    /** 
     * Behaves the same way as changeLayout(View view), however takes in a menuitem
     * 
     * @param item Menuitem clicked by user
     */
    public void changeLayout(MenuItem item){
        int itemID = item.getItemId();
        
        Intent intent;
        switch(itemID){
	        case R.id.newGame:
	        	intent = new Intent(this, NewGameActivity.class);
	            break;
	        case R.id.cntinue:
	            intent = new Intent(this, CntinueActivity.class);
	            break;
	        case R.id.highScore:
	        	intent = new Intent(this, HighScoreActivity.class);
	            break;
	        default:
	        	intent = new Intent(this, NewGameActivity.class);
	    }
	    startActivity(intent);  
    }
    
    /**
     * Overrides the back button to navigate up the app structure rather than going back
     */
    @Override
    public void onBackPressed(){
    	Intent intent = new Intent(this, MainActivity.class);
    	//Clears the activity stack
    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	startActivity(intent);
    }
    
    public void loadState(View view){
    	SharedPreferences userSettings = getSharedPreferences("UserSettings", MODE_PRIVATE);
    	int stateIndex = userSettings.getInt("num_states", -1);
    	AppUtil.restoreState(this, stateIndex-1);
    	Intent intent = new Intent(this, PlayerStatsActivity.class);
    	startActivity(intent);
    }
}
