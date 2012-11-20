package com.example.skidrow.activities;
import com.example.skidrow.AppUtil;
import com.example.skidrow.R;
import com.example.skidrow.R.id;
import com.example.skidrow.R.layout;
import com.example.skidrow.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

public class NewGameActivity extends Activity {
	//Tag for the logcat
    protected static final String TAG = "New Game";
    //True if we want to debug false otherwise
    private boolean D=true;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game);
        
        AppUtil.forceLayout(this);
        setListeners();
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
     * Method used to wipe all data from the player create screen.
     * The clearable inputs are as follows: nameInput(TextView), difficultyLevel(Spinner), communicationsSkillsInput(EditText), 
     * fighterSkillsInput(EditText), driverSkillsInput(EditText), and dealerSkillsInput(EditText).
     * 
     * @param view Button clicked by user to clear data. Or can be null if called from within the program
     */
    public void clearData(View view){
    	//Dirty but efficient way to wipe the screen
        this.setContentView(R.layout.new_game);
    }
    
    /**
     * This is the method called when the user tries to create the player. The input that is needed to create the player is as follows: name, difficulty, 
     * communication skill, fighter skill, driver skill, and dealer skill. 
     * Those inputs correspond to the following android views respectively: nameInput(TextView), difficultyLevel(Spinner), communicationsSkillsInput(EditText), 
     * fighterSkillsInput(EditText), driverSkillsInput(EditText), and dealerSkillsInput(EditText).
     * The restrictions are such that the total combined skill points must be exactly equal to 16. Also the player name must be of legal characters. 
     * 
     * @param view Button clicked by the user
     */
    public void createPlayer(View view){
        EditText playerName = (EditText) this.findViewById(R.id.nameInput);
        Spinner difficulty = (Spinner)this.findViewById(R.id.difficultyLevel);
        String difficLevel = String.valueOf(difficulty.getSelectedItem());
        int[] skill=getArrayOfSkillPoints();
        int sum=sumSkillPoints();
        if (playerName.getText().toString().equals(""))
        {
        	AppUtil.displayError(this, "You have not entered a player name. Please enter a name to continue.");
        }
        if(ensureSkillPointsArePositiveIntegers()){
            if(sum==16 && !playerName.getText().toString().equals("")){
                AppUtil.game.createGame(playerName.getText().toString(), skill[0], skill[1], skill[2], skill[3],difficLevel);
                Intent intent = new Intent(this, PlayerStatsActivity.class);
                //Clears the activity stack
            	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            else if(sum>16){
            	AppUtil.displayError(this, "You have currently allocated "+sum+ " points. You only have 16 points.");
            }
            else{
                if(16-sum>1) AppUtil.displayError(this, "You have currently allocated "+sum+ " points. You have " + (16-sum) +" points left.");
                else if(16-sum==1)  AppUtil.displayError(this, "You have currently allocated "+sum+ " points. You have " + (16-sum) +" point left.");
            }
        }
        else{
        	AppUtil.displayError(this, "You have a negative number in a skill field. Please make it a positive number or a zero.");
        }
        
    }
    
    /**
     * Returns an array of four ints that represent communication skills, fighter skills, and driver skills respectively 
     * @return skill an array of four ints that represent communication skills, fighter skills, and driver skills respectively
     * @author apavia3
     */
    public int[] getArrayOfSkillPoints(){
        int[] skill=new int[4];
        EditText communicationSkills = (EditText)this.findViewById(R.id.communicationSkillsInput);
        EditText fighterSkills = (EditText)this.findViewById(R.id.fighterSkillsInput);
        EditText driverSkills = (EditText)this.findViewById(R.id.driverSkillsInput);
        EditText dealerSkills = (EditText)this.findViewById(R.id.dealerSkillsInput);
        if(communicationSkills.getText().toString().equals("")){
            skill[0] = 0;
        }
        else{
            skill[0] =Integer.parseInt(communicationSkills.getText().toString());
        }
        if(fighterSkills.getText().toString().equals("")){
            skill[1] = 0;
        }
        else{
            skill[1] = Integer.parseInt(fighterSkills.getText().toString());
        }
        if(driverSkills.getText().toString().equals("")){
            skill[2] = 0;
        }
        else{
            skill[2] = Integer.parseInt(driverSkills.getText().toString());
        }
        if( dealerSkills.getText().toString().equals("")){
            skill[3] = 0;
        }
        else{
            skill[3] = Integer.parseInt(dealerSkills.getText().toString());
        }
        
        return skill;
        
    }
    /**
     * Returns the sumation of the skill points entered by the user 
     * @return sum the sumation fo the skill points entered by the user
     * @author apavia3
     */
    public int sumSkillPoints(){
        int sum=0;
        int[] skill=getArrayOfSkillPoints();
        for(int i=0;i<skill.length;i++) {
            sum=sum+skill[i];
        }
        return sum;
        
    }
    /**
     * Checks that all the numbers in the skill points fields are not negative integers
     * @return A boolean that will be true if all the integers in the skill points are positive, false otherwise.
     * @author apavia3
     */
    public boolean ensureSkillPointsArePositiveIntegers(){
        boolean flag=true;
        int[] skill=getArrayOfSkillPoints();
        for(int i=0;i<skill.length;i++) {
            if(skill[i]<0){
                flag=false;
            }
        }
        return flag;    
    }
    
    /**
     *  The action listener for the EditText widget, to listen for the next key
     *  @author apavia3
     */
    private TextView.OnEditorActionListener mWriteListener =
        new TextView.OnEditorActionListener() {
        public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
            // If the action is a key-up event on the return key, send the message
            updateAvailablePoints();
            if(D) Log.i(TAG, "END onEditorAction");
            
            return false;
        }
    };
    /**
     * Set listeners that are not applied to buttons
     * @author apavia3
     */
    public void setListeners(){
        EditText communicationSkills = (EditText)this.findViewById(R.id.communicationSkillsInput);
        EditText fighterSkills = (EditText)this.findViewById(R.id.fighterSkillsInput);
        EditText driverSkills = (EditText) findViewById(R.id.driverSkillsInput);
        EditText dealerSkills = (EditText) findViewById(R.id.dealerSkillsInput);
        if(communicationSkills!=null) communicationSkills.setOnEditorActionListener(mWriteListener);
        if(fighterSkills!=null) fighterSkills.setOnEditorActionListener(mWriteListener);
        if(driverSkills!=null) driverSkills.setOnEditorActionListener(mWriteListener);
        if(dealerSkills!=null) dealerSkills.setOnEditorActionListener(mWriteListener);
    }
    
   /**
    * Updates the available points every time the user click on next or done on the touch key board
    * @author apavia3
    */
    public void updateAvailablePoints(){
        final TextView availablePoints = (TextView )findViewById(R.id.availablePoints);
        if(availablePoints!=null){
            availablePoints.setText(String.valueOf(Math.max(16-sumSkillPoints(), 0)));
        }    
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
}
