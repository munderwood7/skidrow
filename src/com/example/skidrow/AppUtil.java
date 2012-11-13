package com.example.skidrow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.util.Log;
import android.widget.Toast;

public class AppUtil {
	//Tag for the logcat
    protected static final String TAG = "AppUtil";
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
     * Saves state of the game currently in progress
     * 
     * @param context The activity calling the save
     */
    public static void saveState(Context context){
    	SharedPreferences userSettings = context.getSharedPreferences("UserSettings", Context.MODE_PRIVATE);
    	int numberStates = userSettings.getInt("num_states", 0);
    	String fileName = "saveState"+numberStates;
    	try{
	    	FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
		    	ObjectOutputStream out = new ObjectOutputStream(fos);
		    	out.writeObject(game);
		    	out.close();
	    	fos.close();
	    	
			SharedPreferences.Editor editor = userSettings.edit();
			editor.putInt("num_states", numberStates+1);
			editor.commit();
			Log.i(TAG, "Success saving state"+numberStates);
    	} catch (FileNotFoundException e) {
			displayError(context, "Sorry there was an Error when trying to save state");
			Log.i(TAG, e.toString());
		} catch (IOException e) {
			displayError(context, "Sorry there was an Error when trying to save state");
			Log.i(TAG, e.toString());
		}
    }
    
    /**
     * Restores the state of a game.
     * 
     * @param context The activity calling restore
     * @param stateIndex Index of state to restore
     */
    public static void restoreState(Context context, int stateIndex){
    	String fileName = "saveState"+stateIndex;
    	try {
			FileInputStream fis = context.openFileInput(fileName);
				ObjectInputStream in = new ObjectInputStream(fis);
				game = (Game)in.readObject();
				in.close();
			fis.close();
		} catch (FileNotFoundException e) {
			displayError(context, "Sorry there was an Error when trying to restore state");
			Log.i(TAG, e.toString());
		} catch (StreamCorruptedException e) {
			displayError(context, "Sorry there was an Error when trying to restore state");
			Log.i(TAG, e.toString());
		} catch (IOException e) {
			displayError(context, "Sorry there was an Error when trying to restore state");
			Log.i(TAG, e.toString());
		} catch (ClassNotFoundException e) {
			displayError(context, "Sorry there was an Error when trying to restore state");
			Log.i(TAG, e.toString());
		}	
    }
}
