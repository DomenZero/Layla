package com.lepotuli.layla.vogame;

import com.lepotuli.layla.model.UserRatingData;
import com.lepotuli.layla.vogame.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.content.Context;
import android.content.Intent;
import android.gesture.Gesture;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

/*** Fragment UI-panel Settings
Connecting with UserRatingData (there model of table for save & load players)
***/

@SuppressLint("Assert") public class FragmentGameSettings extends Fragment {

	private TextView mSettingsView = null;
	private int mCurrIdx = FragmentGameActivity.UNSELECTED;
	private int mSettingsArrLen = 0;
	//02/05/2015
	private ViewFlipper mFlipper;
	//public TextView mTextView1, mTextView2;
	private TextView mTextView1=null, mTextView2=null;
	private int mCurrentLayoutState=0, mCount=0;
	private GestureDetector mGestureDetector;
	//private Button butStart;
	private int huk=0;


	
	// Returns currently selected item
	public int getShownIndex() {
		return mCurrIdx;
	}

	// Return level
	public int wtisLevel(){
		return mCount;
	}
	
	// Show Settings at position newIndex
	public void showSettingsAtIndex(int newIndex) {
		if (newIndex < 0 || newIndex >= mSettingsArrLen)
			return;
		// Index
		
		mCurrIdx = newIndex;
		
		// Players info string
		mSettingsView.setText(getString(R.string.fragment_game_number)+(newIndex+1)+" "+getString(R.string.fragment_game_name)+" "+FragmentGameActivity.PlayersArray[mCurrIdx]);
		
		// Getting Integer level
		mCount=Integer.parseInt(FragmentGameActivity.SettingsArray[mCurrIdx]);
		


		//03/24 select true previous and next level
		
		mTextView1.setText(String.valueOf(mCount));	
		
		//Der Fruhling 03/02

		mTextView1.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		mTextView2.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		
		// Slide level
		mGestureDetector = new GestureDetector(getActivity(),
				new GestureDetector.SimpleOnGestureListener() {
					@Override
					public boolean onDown(MotionEvent e){
						return true;
					}
					
			
					@Override
					public boolean onFling(MotionEvent e1, MotionEvent e2,
							float velocityX, float velocityY) {
						Log.i("gesture","onFling calling!!!");
						final int SWIPE_MIN_DISTANCE=120;
						final int SWIPE_MAX_OFF_PATH=250;
						final int SWIPE_THRESHOLD_VELOCITY=200;
//						try{
//							if(Math.abs(e1.getY()-e2.getY())>SWIPE_MAX_OFF_PATH)
//								return false;
							if(e1.getX()-e2.getX()>SWIPE_MIN_DISTANCE && Math.abs(velocityX)>SWIPE_THRESHOLD_VELOCITY){
								mCurrentLayoutState = mCurrentLayoutState == 0 ? 1
										: 0;
								switchLayoutStateTo(mCurrentLayoutState);
								Log.i("gesture","Right to Left!");
							}else if(e2.getX()-e1.getX()>SWIPE_MIN_DISTANCE && Math.abs(velocityX)>SWIPE_THRESHOLD_VELOCITY){
								mCurrentLayoutState = mCurrentLayoutState == 0 ? 1
										: 0;
								switchLayoutStateOut(mCurrentLayoutState);
								Log.i("gesture","Left to Right");
							}		
//						} catch (Exception e){
//							
//						}

						return true;//super.onFling(e1, e2, velocityX, velocityY);
					}
				});
		
		mFlipper.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// Touch-touch
				return mGestureDetector.onTouchEvent(event);
				
			}
			

			
			
		});
		
		mFlipper.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(mFlipper.getChildCount()<4)
					return;
				
				mFlipper.setInAnimation(null);
				mFlipper.setOutAnimation(null);
				
				while(mFlipper.getChildCount()>1)
					mFlipper.removeViewAt(0);
				
				mFlipper.setInAnimation(null);
				mFlipper.setOutAnimation(null);
				
				assert mFlipper.getChildCount()==1;
			}
		});
	}

	/***
	 * 
	 * @param switchIn - current layout state
	 * 
	 * Animation flip module
	 */
	
	public void switchLayoutStateIn(int mmm) {
		mFlipper.setInAnimation(inFromRightAnimation());
		mFlipper.setOutAnimation(outToLeftAnimation());
		mCount=Integer.parseInt(FragmentGameActivity.SettingsArray[mmm]);
		mTextView2.setText(String.valueOf(mCount));
		
		//!Test In Animation blink)
//		Animation outtoRight = new TranslateAnimation(
//				Animation.RELATIVE_TO_PARENT, 0.0f,
//				Animation.RELATIVE_TO_PARENT, +1.0f,
//				Animation.RELATIVE_TO_PARENT, 0.0f,
//				Animation.RELATIVE_TO_PARENT, 0.0f);
//		outtoRight.setDuration(500);
		//outtoRight.setStartOffset(20);
		//outtoRight.setRepeatMode(Animation.REVERSE);
		//outtoRight.setRepeatCount(Animation.INFINITE);//setInterpolator(new LinearInterpolator());
		
		//mFlipper.showNext();
		
//		mTextView1.startAnimation(outtoRight);
		
	}
	
	
	public void switchLayoutStateTo(int switchTo) {
		mCurrentLayoutState = switchTo;



		mCount++;

		//push Animation
		mFlipper.setInAnimation(inFromRightAnimation());
		mFlipper.setOutAnimation(outToLeftAnimation());
		
		if (switchTo == 0) {
			mTextView1.setText(String.valueOf(mCount));
		} else {
			mTextView2.setText(String.valueOf(mCount));
		}

		mFlipper.showNext();
	}
	
	public void switchLayoutStateOut(int switchOut) {
		mCurrentLayoutState = switchOut;

		mCount--;
		
		mFlipper.setInAnimation(inFromLeftAnimation());
		mFlipper.setOutAnimation(outToRightAnimation());
		
		if (switchOut == 0) {
			mTextView1.setText(String.valueOf(mCount));
		} else {
			mTextView2.setText(String.valueOf(mCount));
		}

		mFlipper.showPrevious();
	}

	private Animation inFromRightAnimation() {
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(500);
		inFromRight.setInterpolator(new LinearInterpolator());
		return inFromRight;
	}

	private Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(500);
		outtoLeft.setInterpolator(new LinearInterpolator());
		return outtoLeft;
	}
	
	//
	private Animation inFromLeftAnimation() {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromLeft.setDuration(500);
		inFromLeft.setInterpolator(new LinearInterpolator());
		return inFromLeft;
	}

	private Animation outToRightAnimation() {
		Animation outtoRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoRight.setDuration(500);
		outtoRight.setInterpolator(new LinearInterpolator());
		return outtoRight;
	}
   
	/*
	 * 
	 * @ Ended Animation module
	 * 	 
	 * Fragment create module, there managed FragmentGameSettings
	 * 
	 */
	
	// Called to create the content view for this Fragment
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout defined in fragmentgame_settings.xml
		// !!!The last parameter is false because the returned view does not need to be attached to the container ViewGroup
		return inflater.inflate(R.layout.fragmentgame_settings, container, false);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		// Don't destroy Fragment on reconfiguration
		setRetainInstance(true);
		
		// This Fragment adds options to the ActionBar
		setHasOptionsMenu(true);
		
		//02/24
		
//		switchLayoutStateIn(mCurrentLayoutState);
		//push Animation
		//mTextView2.setText(String.valueOf(mCount));
		//mTextView1.setText(String.valueOf(mCount));
//		switchLayoutStateTo(mCurrentLayoutState);
	}

	// Set information about the Settings
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mSettingsView = (TextView) getActivity().findViewById(R.id.playerFlipquoteView);
		mSettingsArrLen = FragmentGameActivity.SettingsArray.length;
		
		//butStart = (Button) getActivity().findViewById(R.id.playerFlipbutStart);
		mFlipper = (ViewFlipper) getActivity().findViewById(R.id.playerFlipview_flipper);
		mTextView1 = (TextView) getActivity().findViewById(R.id.playerFliptextView1);
		mTextView2 = (TextView) getActivity().findViewById(R.id.playerFliptextView2);

		// Cancelled mFlipper info
		mFlipper.invalidate();
		
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCurrIdx = FragmentGameActivity.UNSELECTED;
	}

	public void onMegadeath(int mmm) {
		super.onDetach();
		switchLayoutStateIn(mmm);
		mCurrIdx = FragmentGameActivity.UNSELECTED;
		mFlipper.invalidate();
		if(mFlipper.getChildCount()<4)
			return;
		
		mFlipper.setInAnimation(null);
		mFlipper.setOutAnimation(null);
		
		while(mFlipper.getChildCount()>1)
			mFlipper.removeViewAt(0);
		
		mFlipper.setInAnimation(null);
		mFlipper.setOutAnimation(null);
		
		assert mFlipper.getChildCount()==1;
		
		
		

	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		// Inflate the options Menu settings_menu.xml
		inflater.inflate(R.menu.settings_menu, menu);
	
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {

		// Show Toast Messages.
		// Return value true indicates that the menu click has been handled

		case R.id.detail_menu_item_main:
			Toast.makeText(getActivity().getApplicationContext(),
					R.string.text_winner,
					Toast.LENGTH_SHORT).show();
			/*** Start open database ***/
			
			// GetPlayers 
			// If Winner has in table, that +1, else create new Winner 
			if ((DatabaseRating.getPlayerFromRating(FragmentGameActivity.PlayersArray[mCurrIdx]))!= null)
			{
				int level=DatabaseRating.getLevelFromRating(FragmentGameActivity.PlayersArray[mCurrIdx])+1;
				DatabaseRating.updateLevelData_byID(FragmentGameActivity.PlayersArray[mCurrIdx], level, "Winner"); 
				Log.d("Read: ", "Level= "+level);
			}
			else
			{
				Log.d("Insert: ", "Inserting... "+FragmentGameActivity.PlayersArray[mCurrIdx]);
				DatabaseRating.addUserData(new UserRatingData(FragmentGameActivity.PlayersArray[mCurrIdx], 1,"Winner"));
			}
			
    		/*** End Open Database ***/
    		return true;

    	// Rating Button - have action = start Activity
		case R.id.detail_menu_item_secondary:
//			Toast.makeText(getActivity().getApplicationContext(),
//					"This action is also provided by the SettingsFragment. Not action ",
//					Toast.LENGTH_SHORT).show();
			Intent intent=new Intent(getActivity(), RatingActivity.class);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
