package com.example.skidrow.activities;

import com.example.skidrow.AppUtil;
import com.example.skidrow.R;
import android.os.Bundle;
import android.app.Activity;
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
import android.widget.TextView;

public class PlayerStatsActivity extends Activity {

	Button saveButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_stats);
        refreshBottomStats();
        fillPlayerStats();
    }
    
    /**
     * Fills the player stats view of the layout with the appropriate values.
     */
    public void fillPlayerStats(){
        TextView playerFighting = (TextView)this.findViewById(R.id.playerFightingLevel);
        TextView playerDealing = (TextView)this.findViewById(R.id.playerDealerLevel);
        TextView playerCommunications = (TextView)this.findViewById(R.id.playerCommunicationsLevel);
        TextView playerDriving = (TextView)this.findViewById(R.id.playerDriverLevel);
        TextView playerCity = (TextView)this.findViewById(R.id.playerCity);
        TextView playerName = (TextView)this.findViewById(R.id.playerName);
        TextView playerHealth = (TextView)this.findViewById(R.id.playerHealth);
        TextView playerMoney = (TextView)this.findViewById(R.id.playerMoney);
        TextView playerGas = (TextView)this.findViewById(R.id.playerGas);
        TextView armour = (TextView)this.findViewById(R.id.armourValue);
        TextView turbo = (TextView)this.findViewById(R.id.turboValue);
        TextView speed = (TextView)this.findViewById(R.id.speedValue);
        TextView gunDamage = (TextView)this.findViewById(R.id.gunDamageValue);
        TextView storage = (TextView)this.findViewById(R.id.storageValue);
        TextView fuelEfficiency = (TextView)this.findViewById(R.id.fuelEfficiencyValue);
        TextView carType = (TextView)this.findViewById(R.id.carTypeValue);
        String[] playerInfo = AppUtil.game.getPlayerStatInfo();
        String[] carInfo = AppUtil.game.getShipInfo();
        
        playerCommunications.setText(playerInfo[0]);
        playerFighting.setText(playerInfo[1]);
        playerDriving.setText(playerInfo[2]);
        playerDealing.setText(playerInfo[3]);
        playerCity.setText(playerInfo[4]);
        playerName.setText(playerInfo[6]);
        playerMoney.setText(playerInfo[7]);
        playerGas.setText(playerInfo[8]);
        armour.setText(carInfo[0]);
        turbo.setText(carInfo[1]);
        speed.setText(carInfo[2]);
        gunDamage.setText(carInfo[3]);
        storage.setText(carInfo[4]);
        fuelEfficiency.setText(carInfo[5]);
        carType.setText(carInfo[6]);
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
}
