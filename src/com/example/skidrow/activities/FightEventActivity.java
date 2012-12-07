package com.example.skidrow.activities;

import java.text.DecimalFormat;
import java.util.Random;

import com.example.skidrow.AIAgent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FightEventActivity extends Activity {
	
	private enum enemy { PIRATE, TRADER, COP };
	private enemy currentEnemy;
	private AIAgent com;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight_layout);
        
        choseEnemy();
        populateScreen();
    }
    
    private void choseEnemy(){
    	double skillMult;
    	Random rand = new Random();
    	int randNum = 0;//new Random().nextInt(2);
    	if(randNum == 0)
    		currentEnemy = enemy.PIRATE;
    	else if(randNum==1)
    		currentEnemy = enemy.TRADER;
    	else
    		currentEnemy = enemy.COP;
    	
    	String playerDifficulty = AppUtil.game.getPlayerDifficulty();
    	int searchDepth;
    	if(playerDifficulty.equals("Easy")){
    		skillMult = -Math.abs(.5-rand.nextDouble());
    		searchDepth=5;
    	}
    	else if(playerDifficulty.equals("Medium")){
    		skillMult = Math.abs(.5-rand.nextDouble());
    		searchDepth=8;
    	}
    	else{
    		skillMult = 2-rand.nextDouble();
    		searchDepth=10;
    	}
    	double comAttack = AppUtil.game.getPlayerFightInfo()[0] + AppUtil.game.getPlayerFightInfo()[0]*skillMult;
    	double comHealth = AppUtil.game.getPlayerFightInfo()[1] + AppUtil.game.getPlayerFightInfo()[1]*skillMult - Math.abs(.5-rand.nextDouble());
    	double comSpeed = AppUtil.game.getPlayerFightInfo()[2] + AppUtil.game.getPlayerFightInfo()[2]*skillMult - Math.abs(.5-rand.nextDouble());
    	
    	
    	com = new AIAgent(10, searchDepth, comAttack, comHealth, comSpeed);
    }
    
    private void populateScreen(){
    	DecimalFormat df = new DecimalFormat("##.##");
    	TextView title = (TextView)this.findViewById(R.id.encouterText);
    	TextView playerFighting = (TextView)this.findViewById(R.id.playerFighting);
    	TextView playerHealth = (TextView)this.findViewById(R.id.playerHealth);
    	TextView playerSpeed = (TextView)this.findViewById(R.id.playerSpeed);
    	TextView comFighting = (TextView)this.findViewById(R.id.comFighting);
    	TextView comHealth = (TextView)this.findViewById(R.id.comHealth);
    	TextView comSpeed = (TextView)this.findViewById(R.id.comSpeed);
    	Button special = (Button)this.findViewById(R.id.specialButton);
    	
    	String titleText="You have encountered a ";
    	String buttonText;
    	if(currentEnemy==enemy.PIRATE){
    		titleText+="pirate";
    		buttonText="";
    		special.setVisibility(View.INVISIBLE);
    	}
    	else if(currentEnemy==enemy.TRADER){
    		titleText+="trader";
    		buttonText="Trade";
    	}
    	else{
    		titleText+="cop";
    		buttonText="Bribe";
    	}  	
    	title.setText(titleText);
    	special.setText(buttonText);
    	
    	playerFighting.setText(df.format(AppUtil.game.getPlayerFightInfo()[0]));
    	playerHealth.setText(df.format(AppUtil.game.getPlayerFightInfo()[1]));
    	playerSpeed.setText(df.format(AppUtil.game.getPlayerFightInfo()[2]));
    	comFighting.setText(df.format(com.getAttack()));
    	comHealth.setText(df.format(com.getHealth()));
    	comSpeed.setText(df.format(com.getSpeed()));
    }
    
    public void fightOptionSelected(View view){
    	switch(view.getId()){
    		case R.id.fightButton:
    			fight();
    			break;
    		case R.id.fleeButton:
    			flee();
    			break;
    	}
    }
    
    private void fight(){
    	
    }
    
    private void flee(){
    }
}
