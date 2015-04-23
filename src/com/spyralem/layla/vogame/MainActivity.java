package com.spyralem.layla.vogame;

import com.spyralem.layla.vogame.R;

import android.R.drawable;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

/*
 * @author Merkulov Maksim (DomenZero) 
 * <wardomenmax@gmail.com>
 * 
 * In future Del Element or Intro element
 */

public class MainActivity extends Activity
{
	ImageButton button1;
	ImageButton button0;
	ImageButton button3;
	ImageButton button2;
	ImageButton button4;
	ToggleButton buttonHint;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        styleUtils.onActivitySetTheme(this);
        setContentView(R.layout.main);
        addListenerOnButton();

	}
    
    // Created buttons on Start Screen
    public void addListenerOnButton(){
    	final Context context=this;
    	// Animation
    	final Animation animBoom=AnimationUtils.loadAnimation(context, R.anim.anim_boom);
    	
    	// Start game
    	button4=(ImageButton) findViewById(R.id.button4);
    	button4.setImageResource(R.drawable.d3);
    	
    	button4.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v){
    			v.startAnimation(animBoom);
    			// Animation & start next Intent
    			animMotion(animBoom, context, FragmentGameActivity.class);	
    		}
    	});
    	
    	// Options game
       	button3 = (ImageButton) findViewById(R.id.button3);
      	button3.setImageResource(R.drawable.d2);
    	button3.setOnClickListener(new OnClickListener() {
    		DatabaseRating db;
    		@Override
    		public void onClick(View v){
    			v.startAnimation(animBoom);
    			// Animation & start next Intent
    			animMotion(animBoom, context, NumPlayerActivity.class);
    		}
    	});
    	
    	// Rating button
    	button2 = (ImageButton) findViewById(R.id.button2);
    	button2.setImageResource(R.drawable.d2);
    	button2.setOnClickListener(new OnClickListener() {
    		DatabaseRating db;
    		@Override
    		public void onClick(View v){
    			v.startAnimation(animBoom);
    			// Animation & start next Intent
    			animMotion(animBoom, context, RatingActivity.class);
    		}
    	});
    	
    	// About button
    	button1=(ImageButton) findViewById(R.id.button1);
    	button1.setImageResource(R.drawable.d2);
    	button1.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v){
    			v.startAnimation(animBoom);
    			// Animation & start next Intent
    			animMotion(animBoom, context, AboutActivity.class);
    		}
    	});

    	
    	// Exit Button
    	button0 = (ImageButton) findViewById(R.id.button0);
    	button0.setImageResource(R.drawable.d1);
    	button0.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v){
    			v.startAnimation(animBoom);
//    			Intent intent=new Intent(context, ListPlayerWithHistoryActivity.class);
//    			startActivity(intent);
    			styleUtils.changedTheme(MainActivity.this, styleUtils.THEME_STANDART);
//    			Intent intent=new Intent(Intent.ACTION_MAIN);
//    			intent.addCategory(Intent.CATEGORY_HOME);
//    			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//    			
//    			
//    			
//
//    			//startActivity(intent);
//    			finish();
//    			onDestroy();
    		}
    	});
    	
//    	// Hint RadioButton
//    	buttonHint = (ToggleButton) findViewById(R.id.buttonHint);
//    	buttonHint.setOnClickListener(new OnClickListener() {
//    		@Override
//    		public void onClick(View v){
//    			
//    			// Toggle the Backgroung hints on/off
//    			if(buttonHint.isChecked()){
//    				styleUtils.changedTheme(MainActivity.this, styleUtils.THEME_DARK);
//    			} else{
//    				styleUtils.changedTheme(MainActivity.this, styleUtils.THEME_STANDART);
//
//    			}
//    			
//    		}
//    	});

   	
    }
    
 // Animation for all button event on screen
    public void animMotion(Animation animBoom, final Context context, final Class<?> classic){
    	//!Test About Event Animation
    	animBoom.setAnimationListener(new Animation.AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation v) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation v) {
				// TODO Auto-generated method stub
				
			}
			
			public void onAnimationEnd(Animation v) {
				// TODO Auto-generated method stub
    			Intent intent=new Intent(context, classic);
    			startActivity(intent);
			}
		});
    }
    
    public void onClickAbout(View v)
    {
    	onStop();
    	onDestroy();
    	Intent myintent=new Intent(MainActivity.this, ControlActivity.class);
		//myintent.putExtra("key", value);
    	
    	MainActivity.this.startActivity(myintent);
    	
    }
    
}