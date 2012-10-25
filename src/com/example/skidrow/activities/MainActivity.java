package com.example.skidrow.activities;

import com.example.skidrow.AppUtil;
import com.example.skidrow.Game;
import com.example.skidrow.R;
import com.example.skidrow.R.id;
import com.example.skidrow.R.layout;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

/**
 * Main activity class that controls the UI of the program.
 * 
 * @author clayton
 * @version 1.6
 */
@SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi" })
public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.home);
        //Creates a new SkidRow Game       
        
        AppUtil.forceLayout(this);
        AppUtil.game = new Game();
    }
    
    /**
     * This method changes the layout visible to the user.
     * It is called when the user clicks something on the screen which takes them to a new screen.
     * 
     * @param view view clicked by user
     */
    public void changeLayout(View view){
        int viewID = view.getId();
        
        Intent intent;
        switch(viewID){
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
}