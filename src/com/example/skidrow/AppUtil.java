package com.example.skidrow;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.widget.Toast;

public class AppUtil {
	//Intsance of SkidRow game 
    public static Game game;
    
    /**
     * Generates a message to the user to inform them of an error. This may be due to the user or the the program. 
     * 
     * @param errorMessage The message to display
     */
    public static void displayError(Context context, String errorMessage){
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }
    
    /**
     * Generates a message to the user. 
     * 
     * @param message The message to display
     */
    public static void displayMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    
    
    /**
     * Forces the layout to landscape if it is a tablet (5-10 inches). Otherwise forces portrait.
     * 
     * @author Clayton
     * @param activity The activity which is forcing the layout
     */
    public static void forceLayout(Activity activity){
    	//Gets the size of the phone/tablet using the app
        int layout = activity.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        if(layout == Configuration.SCREENLAYOUT_SIZE_XLARGE || layout == Configuration.SCREENLAYOUT_SIZE_LARGE){
        	activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else{
        	activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
    /**
     * Returns the current step of the game
     * @return current step of the game
     * @author apavia3
     */
    public static int getStep(){
    	return game.getStep();
    }
    /**
     * Make a move
     */
    public static void makeMove(){
    	game.makeMove();
    }
    
}
