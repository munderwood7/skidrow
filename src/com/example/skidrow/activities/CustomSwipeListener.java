package com.example.skidrow.activities;

import com.example.skidrow.AppUtil;

import android.content.Context;
import android.content.Intent;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * This class decides the motion of the save states. It handles gestures and onclicks
 * 
 * @author Clayton
 *
 */
public class CustomSwipeListener implements View.OnTouchListener{

	//The sensitivity at which the book image moves
	private final float MOVE_SENSITIVITY = 15;
	private final float MAX_DELTA_Y = 200;
	private Context context;
	private boolean click = true;
	float startX=0;
	float startY=0;
	float prevX=0;
	float prevY=0;
	float origPositionX=0;
	float origPositionY=0;

	public CustomSwipeListener(Context context){
		this.context = context;
	}

	public boolean onTouch(View book, MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			startY = event.getY();
			origPositionX = book.getX();
			origPositionY = book.getY();
			prevX = startX;
			prevY = startY;
			break;
		case MotionEvent.ACTION_MOVE:
			float deltaX = event.getX() - prevX;
			float deltaY = event.getY() - prevY;
			
			if(Math.abs(deltaY)>MOVE_SENSITIVITY && MAX_DELTA_Y > Math.abs(book.getY()-origPositionY)){
				//prevents the parent from taking over the motion event
				book.getParent().requestDisallowInterceptTouchEvent(true);
				//Tells program it is not a click event
				click = false;
				
				float alpha = 1-Math.abs(book.getY()-origPositionY)/MAX_DELTA_Y;
				book.setX(book.getX());
				if(deltaY>0){
					book.setY(book.getY()+MOVE_SENSITIVITY);
				}
				else{
					book.setY(book.getY()-MOVE_SENSITIVITY);
				}
				book.setAlpha(alpha);
				
				book.setAlpha(book.getAlpha()+(float)0.2);
				
				prevX = event.getX();
				prevY = event.getY();
			}
			break;
		case MotionEvent.ACTION_UP:
			if(click){
				AppUtil.restoreState(context, (Integer)book.getTag());
				context.startActivity(new Intent(context, PlayerStatsActivity.class));
			}
			else if(MAX_DELTA_Y-Math.abs(book.getY()-origPositionY)<50){
				AppUtil.deleteState(context, (Integer)book.getTag());
				book.setVisibility(View.GONE);
				context.startActivity(new Intent(context, CntinueActivity.class));
			}
			else{
				book.setAlpha(1);
				book.setY(origPositionY);
				book.setX(origPositionX);
			}
			break;
		default:
			book.setAlpha(1);
			book.setY(origPositionY);
			book.setX(origPositionX);
		}
		return true;
	}
}
