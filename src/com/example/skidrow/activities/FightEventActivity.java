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
import com.example.skidrow.activities.MarketActivity.Person;

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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class FightEventActivity extends Activity {
	
	private enum enemy { PIRATE, TRADER, COP };
	private enemy currentEnemy;
	private String enemeyStr;
	private boolean fightMode = false;
	private boolean comTurn=true;
	boolean compSkipTurn=false;
	boolean personSkipTurn=false;
	int errorCode=0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight_layout);
        
        choseEnemy();
        populateScreen();
    }
    
    private void choseEnemy(){
    	ImageView image = (ImageView)this.findViewById(R.id.imageView1);
    	double skillMult;
    	Random rand = new Random();
    	int randNum = 1;//new Random().nextInt(2);
    	if(randNum == 0){
    		currentEnemy = enemy.PIRATE;
    		enemeyStr = "Dealer";
    		image.setImageResource(R.drawable.dealer);
    	}
    	else if(randNum==1){
    		currentEnemy = enemy.TRADER;
    		enemeyStr = "Trader";
    	}
    	else{
    		currentEnemy = enemy.COP;
    		enemeyStr = "Copper";
    		image.setImageResource(R.drawable.cop);
    	}
    	
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
    		skillMult = rand.nextDouble();
    		searchDepth=10;
    	}
    	double comAttack = AppUtil.game.getPlayerFightInfo()[0] + AppUtil.game.getPlayerFightInfo()[0]*skillMult;
    	double comHealth = AppUtil.game.getPlayerFightInfo()[1] + AppUtil.game.getPlayerFightInfo()[1]*skillMult - Math.abs(.5-rand.nextDouble());
    	double comSpeed = AppUtil.game.getPlayerFightInfo()[2] + AppUtil.game.getPlayerFightInfo()[2]*skillMult - Math.abs(.5-rand.nextDouble());
    	
    	
    	AppUtil.game.com = new AIAgent(10, searchDepth, comAttack, comHealth, comSpeed);
    	
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
    		titleText+="dealer";
    		buttonText="";
    		special.setVisibility(View.INVISIBLE);
    	}
    	else if(currentEnemy==enemy.TRADER){
    		titleText+="trader";
    		buttonText="Trade";
    	}
    	else{
    		titleText+="copper";
    		buttonText="Bribe";
    	}  	
    	title.setText(titleText);
    	special.setText(buttonText);
    	
    	playerFighting.setText(df.format(AppUtil.game.getPlayerFightInfo()[0]));
    	playerHealth.setText(df.format(AppUtil.game.getPlayerFightInfo()[1]));
    	playerSpeed.setText(df.format(AppUtil.game.getPlayerFightInfo()[2]));
    	comFighting.setText(df.format(AppUtil.game.com.getAttack()));
    	comHealth.setText(df.format(AppUtil.game.com.getHealth()));
    	comSpeed.setText(df.format(AppUtil.game.com.getSpeed()));
    }
    
    public void fightOptionSelected(View view){
    	if(fightMode){
    		boolean alive=true;
    		switch(view.getId()){
	    		case R.id.fightButton:
	    			alive = AppUtil.game.playerDoAttack(0);
	    			showMove("you", enemeyStr, 0);
	    			break;
	    		case R.id.fleeButton:
	    			alive = AppUtil.game.playerDoAttack(1);
	    			personSkipTurn=true;
	    			showMove("you", enemeyStr, 1);
	    			break;
	    		case R.id.fortifyAttackButton:
	    			alive = AppUtil.game.playerDoAttack(2);
	    			showMove("you", enemeyStr, 2);
	    			break;
	    		case R.id.fleeDurringBattleButton:
	    			fleeDurringBattle();
	    			showMove("you", enemeyStr, 3);
	    			break;
	    		case R.id.specialButton:
	    			if(comTurn)
	    				comTakeTurn(0);
	    			else
	    				refreshFightScreen();
	    			break;
	    		default:
	    			
    		}
    		if(!alive){
    			errorCode=0;
    			showMessage2("You Won!", "Congratulations you have won");
    		}
    	}
    	else{
	    	switch(view.getId()){
	    		case R.id.fightButton:
	    			fight();
	    			break;
	    		case R.id.fleeButton:
	    			flee();
	    			break;
	    		case R.id.specialButton:
	    			specialCase();
	    			break;
	    	}
    	}
    }
    
    private void specialCase(){
    	if(currentEnemy==enemy.COP){
    		 errorCode=-1;
    		 
    		 int bribe =0;
    		 this.showMessage2("Bribe", "Copper requests a " + bribe +"\nDo you accept?");
    	}
    }
    
    private void refreshFightScreen(){
    	Button smallAttack = (Button)this.findViewById(R.id.fightButton);
    	Button largeAttack = (Button)this.findViewById(R.id.fleeButton);
    	Button flee = (Button)this.findViewById(R.id.fleeDurringBattleButton);
    	Button fortify = (Button)this.findViewById(R.id.fortifyAttackButton);
    	Button special = (Button)this.findViewById(R.id.specialButton);
    	TextView message = (TextView)this.findViewById(R.id.encouterText);    	
    
    	smallAttack.setVisibility(View.VISIBLE);
    	largeAttack.setVisibility(View.VISIBLE);
    	flee.setVisibility(View.VISIBLE);
    	fortify.setVisibility(View.VISIBLE);
    	special.setVisibility(View.INVISIBLE);
    	message.setText("");
    	comTurn = true;
    }
    
    private void showMove(String person, String other, int attack){
    	DecimalFormat df = new DecimalFormat("##.##");
    	Button smallAttack = (Button)this.findViewById(R.id.fightButton);
    	Button largeAttack = (Button)this.findViewById(R.id.fleeButton);
    	Button flee = (Button)this.findViewById(R.id.fleeDurringBattleButton);
    	Button fortify = (Button)this.findViewById(R.id.fortifyAttackButton);
    	Button special = (Button)this.findViewById(R.id.specialButton);
    	TextView message = (TextView)this.findViewById(R.id.encouterText);
    	TextView playerFighting = (TextView)this.findViewById(R.id.playerFighting);
    	TextView playerHealth = (TextView)this.findViewById(R.id.playerHealth);
    	TextView playerSpeed = (TextView)this.findViewById(R.id.playerSpeed);
    	TextView comFighting = (TextView)this.findViewById(R.id.comFighting);
    	TextView comHealth = (TextView)this.findViewById(R.id.comHealth);
    	TextView comSpeed = (TextView)this.findViewById(R.id.comSpeed);
    	
    	String attackType;
    	if(attack==0)
    		attackType=person+" used small attack on "+other;
    	else if(attack==1)
    		attackType=person+ " used large attack on "+other;
    	else if(attack==2)
    		attackType=person+ " fortify on "+person;
    	else
    		attackType=person+" attempted, but failed, to flee from "+other;
    	
    	message.setText(attackType);
    	smallAttack.setVisibility(View.GONE);
    	largeAttack.setVisibility(View.GONE);
    	flee.setVisibility(View.GONE);
    	fortify.setVisibility(View.GONE);
    	special.setVisibility(View.VISIBLE);
    	special.setText("Ok");
    	playerFighting.setText(df.format(AppUtil.game.getPlayerFightInfo()[0]));
    	playerHealth.setText(df.format(AppUtil.game.getPlayerFightInfo()[1]));
    	playerSpeed.setText(df.format(AppUtil.game.getPlayerFightInfo()[2]));
    	comFighting.setText(df.format(AppUtil.game.com.getAttack()));
    	comHealth.setText(df.format(AppUtil.game.com.getHealth()));
    	comSpeed.setText(df.format(AppUtil.game.com.getSpeed()));
    }
    
    private void comTakeTurn(int moveType){
    	int attack = (int)AppUtil.game.miniMaxTree("com", 0, moveType)[1];
    	if(!AppUtil.game.comDoAttack(attack)){
    		errorCode=1;
    		this.showMessage2("You died!", "Sorry, but you have just died");
    	}
    	comTurn=false;
    	showMove(enemeyStr, "you", attack);
    }
    
    private void fleeDurringBattle(){
    	int prob;
    	if(currentEnemy == enemy.COP){
    		prob = 1;
    	}
    	else{
    		prob = 3;
    	}
    	if(new Random().nextInt(10)<prob){
    		errorCode=0;
    		this.showMessage2("You Escaped!", "Congratulations, you escaped");
    	}
    }
    
    private void fight(){
    	Button smallAttack = (Button)this.findViewById(R.id.fightButton);
    	Button largeAttack = (Button)this.findViewById(R.id.fleeButton);
    	Button flee = (Button)this.findViewById(R.id.fleeDurringBattleButton);
    	Button fortify = (Button)this.findViewById(R.id.fortifyAttackButton);
    	Button special = (Button)this.findViewById(R.id.specialButton);
    	
    	smallAttack.setText("Small Attack");
    	largeAttack.setText("Large Attack");
    	flee.setText("Flee");
    	flee.setVisibility(View.VISIBLE);
    	fortify.setText("Fortify");
    	fortify.setVisibility(View.VISIBLE);
    	special.setVisibility(View.INVISIBLE);
    	this.fightMode = true;
    }
    
    private void finishActivity(){
    	if(errorCode==0)
    		this.finish();
    	else if(errorCode==1){
    		this.startActivity(new Intent(this, MainActivity.class));
    	}
    }
    
    public void showMessage2(String title,final String message){
 		LayoutInflater inflater = this.getLayoutInflater();
 		final LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.message_popup, null);
 		AlertDialog.Builder popup2 = new AlertDialog.Builder(this, 0);
 		final TextView content = (TextView)layout.findViewById(R.id.message_content);
 		content.setText(message);
 		popup2.setView(layout).setTitle(title).setNegativeButton("Ok", new DialogInterface.OnClickListener(){
			
	    	 public void onClick(DialogInterface dialog, int which) {
	    		finishActivity();
					}
					
			 });
 		popup2.create().show();
 	}
    
    private void flee(){
    	double playerSpeed = AppUtil.game.getPlayerFightInfo()[2];
    	double comSpeed = AppUtil.game.com.getSpeed();
    	
    	if(playerSpeed>comSpeed){
    		this.finish();
    	}
    	else{
    		int prob;
    		if(AppUtil.game.getPlayerDifficulty().equals("Medium"))
    			prob = 5;
    		else
    			prob = 3;
    		
    		if(new Random().nextInt(10)<prob){
    			this.finish();
    		}
    		else{
    			errorCode=2;
    			this.showMessage2("No Luck!", "Sorry you did not escape");
    		}
    	}
    		
    }
}
