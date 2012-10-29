package com.example.skidrow.activities;

import com.example.skidrow.AppUtil;
import com.example.skidrow.R;
import com.example.skidrow.R.id;
import com.example.skidrow.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MarketActivity extends Activity {

	private Person person;
	
	public enum Person { PLAYER, MARKET }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market);
        person = Person.MARKET;
        
        Spinner buySell = (Spinner)this.findViewById(R.id.buySellChoice);
        ListView goodsList = (ListView)this.findViewById(R.id.goodsList);
        //Set the listener to see if the user switches to sell or vice versa 
        buySell.setOnItemSelectedListener(new OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				if(position==0){
					person = Person.MARKET;
					populateScreen();
				}
				else{
					person = Person.PLAYER;
					populateScreen();
				}				
			}
			public void onNothingSelected(AdapterView<?> arg0) {}       	
			});
        
        //Set the listener for the goodsList
        goodsList.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
				TextView good = (TextView)view;
				goodsTransaction(good.getText().toString());
			}      
        });
    }
    
    /**
     * This method switches the state of the view based on whether the user is buying or selling.
     * 
     * @param person The person with stats displayed on the screen (Player or Market)
     */
    private void populateScreen(){
    	TextView whoGoods = (TextView)this.findViewById(R.id.whoGoods);
    	ListView goodsList = (ListView)this.findViewById(R.id.goodsList);
    	TextView playerMoney = (TextView)this.findViewById(R.id.playerMoney);
    	String playerInfo[] = AppUtil.game.getPlayerStatInfo();
    	
    	if(person == Person.PLAYER){
    		whoGoods.setText("Your Goods");
    		goodsList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AppUtil.game.getPlayerGoods()));
    	}
    	else{
    		//Gets the name of the current city
    		String[] city = AppUtil.game.getCityInfo(AppUtil.game.getCurrentCity());
    		whoGoods.setText(city[0]+"'s Goods");
    		goodsList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AppUtil.game.getMarketGoods()));
    	}
    	
    	playerMoney.setText(playerInfo[7]);
    }
    
    /**
     * This method sets up and displays the transaction popup to the user
     * 
     * @param good String of good the user wishes to deal with
     */
    private void goodsTransaction(String good){
    	//name of drug without quantity
    	String drugt = "";
    	for (int x=0; x<good.length(); x+=1){
    		char ltr = good.charAt(x);
    		if(ltr == '('){
    			break;
    		}
    		drugt += ltr;
    	}
    	final String drug = drugt;
    	
    	String transactionType;
    	if(person == Person.MARKET){ transactionType = "Buy"; }
    	else{ transactionType = "Sell"; }
    	
    	LayoutInflater inflater = this.getLayoutInflater();
    	final RelativeLayout layout = (RelativeLayout)inflater.inflate(R.layout.cutsom_popup, null);
        AlertDialog.Builder popup = new AlertDialog.Builder(this);
        popup.setView(layout)
        	.setTitle(transactionType+" " +drug)
        	.setPositiveButton("Buy", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					EditText quantity = (EditText)layout.findViewById(R.id.buySellQuantity);
					buySellGoods(drug, quantity.getText().toString());
				}
			})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
				
			});
        popup.create().show();
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
    
    private void buySellGoods(String good, String quantity){
    	String error;
    	if(!quantity.equals("")){
	    	if(person == Person.MARKET){
	    		error = AppUtil.game.marketToPlayer(good, Integer.parseInt(quantity));
	    	}
	    	else{
	    		error = AppUtil.game.playerToMarket(good, Integer.parseInt(quantity));
	    	}
	    	
	    	if(error!=null){ AppUtil.displayError(this, error); }
    	}
    	else{
    		AppUtil.displayError(this, "You have not picked a quantity");
    	}
    }
}
