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
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
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
        refreshBottomStats();
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
			public void onNothingSelected(AdapterView<?> arg0) {
			}       	
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
    	refreshBottomStats();
    	
    	TextView whoGoods = (TextView)this.findViewById(R.id.whoGoods);
    	ListView goodsList = (ListView)this.findViewById(R.id.goodsList);
    	TextView playerMoney = (TextView)this.findViewById(R.id.playerMoney);
    	TextView marketMoney = (TextView)this.findViewById(R.id.marketMoney);
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
    	
    	marketMoney.setText("$"+Integer.toString(AppUtil.game.getMarketMoney()));
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
    	int x;
    	for (x=0; x<good.length(); x+=1){
    		char ltr = good.charAt(x);
    		if(ltr == '('){
    			break;
    		}
    		drugt += ltr;
    	}
    	final String drug = drugt;
    	String quant = "";
    	for(x=x+1; x<good.length(); x+=1){
    		char ltr = good.charAt(x);
    		if(ltr == ')'){
    			break;
    		}
    		quant += ltr;
    	}
    	final int goodQuant = Integer.parseInt(quant);
    	String goodPriceStr = "";
    	boolean record = false;
    	for(x=x+1;x<good.length();x+=1){
    		char ltr = good.charAt(x);
    		if(ltr=='$'){
    			record=true;
    		}
    		else if(record && ltr!=' '){
    			goodPriceStr+=ltr;
    		}
    	}
    	final int goodPrice = Integer.parseInt(goodPriceStr);
    	 	
    	String transactionType;
    	if(person == Person.MARKET){ 
    		transactionType = "Buy"; 
    	}
    	else{ 
    		transactionType = "Sell"; 
    	}
    	
    	//Setting all the appropriate listeners for the dialog layout
    	LayoutInflater inflater = this.getLayoutInflater();
    	final RelativeLayout layout = (RelativeLayout)inflater.inflate(R.layout.cutsom_popup, null);
    	final TextView goodCost = (TextView)layout.findViewById(R.id.goodCost);
    		goodCost.setText("$"+goodPriceStr);
    	final TextView quantity = (TextView)layout.findViewById(R.id.quantityValue);
    	final TextView playerMoneyView = (TextView)layout.findViewById(R.id.playerMoney);
    		playerMoneyView.setText(AppUtil.game.getPlayerStatInfo()[7]);
    	final TextView marketMoneyView = (TextView)layout.findViewById(R.id.marketMoney);
    		marketMoneyView.setText("$"+Integer.toString(AppUtil.game.getMarketMoney()));
    	final TextView availableCargo = (TextView)layout.findViewById(R.id.availableCargo);
    		availableCargo.setText(Integer.toString(AppUtil.game.getCargoSpaceFromGame()));
    	SeekBar slider = (SeekBar)layout.findViewById(R.id.buySellQuantity);
    	slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				int buySellQuant = (progress*goodQuant)/100;
				String playerMoneyStr = AppUtil.game.getPlayerStatInfo()[7];
				int playerMoney = Integer.parseInt((String) playerMoneyStr.subSequence(1, playerMoneyStr.length()));
				int marketMoney = AppUtil.game.getMarketMoney();				//Set the textview to the current quantity to be bought
				quantity.setText(Integer.toString(buySellQuant));
				
				//Calculate the future money values
				if(person == Person.MARKET){
					playerMoney = playerMoney - buySellQuant * goodPrice;
					marketMoney = marketMoney + buySellQuant * goodPrice;
				}
				else{
					playerMoney = playerMoney + buySellQuant * goodPrice;
					marketMoney = marketMoney - buySellQuant * goodPrice;
				}
				
				playerMoneyView.setText("$"+Integer.toString(playerMoney));
				marketMoneyView.setText("$"+Integer.toString(marketMoney));
			}
		});
    	//Building the dialog box to display to the user
    	String transType = "Buy";
    	if(person==Person.PLAYER){ transType = "Sell"; }
        AlertDialog.Builder popup = new AlertDialog.Builder(this);
        popup.setView(layout)
        	.setTitle(transactionType+" " +drug)
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
				
			})
			.setPositiveButton(transType, new DialogInterface.OnClickListener() {
				
				
				
				public void onClick(DialogInterface dialog, int which) {
					buySellGoods(drug, quantity.getText().toString());
					
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
    		case R.id.repairShopView:
                intent = new Intent(this, RepairShopActivity.class);
                break;
    		default:
    			intent = new Intent(this, PlayerStatsActivity.class);
    			break;
    	}
    	startActivity(intent);
    }
    
    private void buySellGoods(String good, String quantity){
    	String error;

    	if(!quantity.equals("") && !quantity.equals("0")){
	    	if(person == Person.MARKET){
	    		error = AppUtil.game.marketToPlayer(good, Integer.parseInt(quantity));
	    	}
	    	else{
	    		error = AppUtil.game.playerToMarket(good, Integer.parseInt(quantity));
	    	}
	    	
	    	if(error!=null){ AppUtil.displayError(this, error); }
	    	//refresh info on screen
	    	populateScreen();
    	}
    
    	else {
    		AppUtil.displayError(this, "You have not picked a quantity");
    	}
    }
    
    @Override
    /**
     * This method renders menu options for this activity
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.market, menu);
        return true;
    }
    
    //@Override
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
    	View parent = (View)this.findViewById(R.id.market_view);
    	Bitmap screen = Bitmap.createBitmap(parent.getWidth(), parent.getHeight(), Bitmap.Config.ARGB_8888);
    	Canvas canvas = new Canvas(screen);
    	parent.draw(canvas);
    	AppUtil.saveState(this, screen);

    }
    
}
