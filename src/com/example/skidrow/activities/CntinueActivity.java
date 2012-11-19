package com.example.skidrow.activities;

import com.example.skidrow.AppUtil;
import com.example.skidrow.R;
import com.example.skidrow.R.drawable;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class CntinueActivity extends Activity {

	private Context context;
	private CustomSwipeListener swipeListener;
	private int height;
	private int width;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cntinue);
        
        //Gets the width and the height of the display
      	Display display = this.getWindowManager().getDefaultDisplay();
      	Point size = new Point();
      	display.getSize(size);
      	height = size.y;
      	width = size.x;
      	
        context = this;
        swipeListener = new CustomSwipeListener(this);
        
        AppUtil.forceLayout(this);
        initSaveScroller();
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
    
    public void initSaveScroller(){
    	LinearLayout scrollerLayout = (LinearLayout)this.findViewById(R.id.stateScrollerLayout);
    	SharedPreferences userSettings = getSharedPreferences("UserSettings", MODE_PRIVATE);
    	int numStates = userSettings.getInt("num_states", 0);
    	
    	for(int x=0; x<numStates; x+=1){
    		View space = new View(context);
    		ImageView newImage = new ImageView(this);
    			newImage.setImageBitmap(AppUtil.getScreenImage(context, x));
    			newImage.setBackgroundColor(Color.BLACK);
    		TextView newText = new TextView(this);
    			newText.setText("Save State "+x);
    			newText.setTextSize(20);
    			newText.setGravity(Gravity.CENTER_HORIZONTAL);
    		LinearLayout newState = new LinearLayout(this);
    			newState.setBackgroundColor(Color.GRAY);
    			newState.setPadding(1, 1, 1, 1);
	    		newState.setOrientation(LinearLayout.VERTICAL);
	    		newState.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	    		newState.setTag(x);
    		
	    	scrollerLayout.addView(space);
	    		newState.addView(newText);
	    		newState.addView(newImage);
    		scrollerLayout.addView(newState);
    		
    		space.getLayoutParams().width = 50;
    		newImage.getLayoutParams().width = (int) ((int) (height-250)*0.67);
    		newImage.getLayoutParams().height = height-250;
    		newImage.setScaleType(ScaleType.FIT_XY);
    		
    		//Set the listener for throwing the state
    		newState.setOnTouchListener(swipeListener);
    	}
    }
    
    public void loadState(View view){
    	SharedPreferences userSettings = getSharedPreferences("UserSettings", MODE_PRIVATE);
    	int stateIndex = userSettings.getInt("num_states", -1);
    	AppUtil.restoreState(this, stateIndex-1);
    	Intent intent = new Intent(this, PlayerStatsActivity.class);
    	startActivity(intent);
    }
}
