package com.spyralem.layla.vogame;

import com.spyralem.layla.model.PlayersData;
import com.spyralem.layla.model.UserRatingData;

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
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

public class FragmentGameSettings extends Fragment {

	private TextView mQuoteView = null;
	private int mCurrIdx = FragmentGameActivity.UNSELECTED;
	private int mQuoteArrLen = 0;
	//02/05/2015
	private ViewFlipper mFlipper;
	private TextView mTextView1, mTextView2;
	private int mCurrentLayoutState=0, mCount=0;
	private GestureDetector mGestureDetector;
	//private Button butStart;


	
	// Returns currently selected item
	public int getShownIndex() {
		return mCurrIdx;
	}

	// Return level
	public int wtisLevel(){
		return mCount;
	}
	
	// Show the Quote string at position newIndex
	public void showQuoteAtIndex(int newIndex) {
		if (newIndex < 0 || newIndex >= mQuoteArrLen)
			return;
		mCurrIdx = newIndex;
		//mQuoteView.setText(FragmentGameActivity.SettingsArray[mCurrIdx]);
		mQuoteView.setText("Player №"+(newIndex+1)+"   Name: "+FragmentGameActivity.PlayersArray[mCurrIdx]);
		mCount=Integer.parseInt(FragmentGameActivity.SettingsArray[mCurrIdx]);
		//02/06 Завершающая стадия редактирования Фрагмента для Бэта
		
		mCurrentLayoutState = 0;
		
		//02/12 update Setting Level
		mTextView2.setText(String.valueOf(mCount));
		mTextView1.setText(String.valueOf(mCount));
		
		mGestureDetector = new GestureDetector(getActivity(),
		//final GestureDetector gesture = new GestureDetector(getActivity(),
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
							if(Math.abs(e1.getY()-e2.getY())>SWIPE_MAX_OFF_PATH)
								return false;
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
				// TODO Auto-generated method stub
				return mGestureDetector.onTouchEvent(event);
				
			}
			

			
			
		});
	}

	public void switchLayoutStateIn(int switchIn) {
		mCurrentLayoutState = switchIn;
		mFlipper.setInAnimation(inFromRightAnimation());
		mFlipper.setOutAnimation(outToLeftAnimation());
		mTextView1.setText(String.valueOf(mCount));

		mFlipper.showNext();
	}
	
	
	public void switchLayoutStateTo(int switchTo) {
		mCurrentLayoutState = switchTo;

		//push Animation
//		mFlipper.setInAnimation(inFromRightAnimation());
//		mFlipper.setOutAnimation(outToLeftAnimation());

		mCount++;

		if (switchTo == 0) {
			mTextView1.setText(String.valueOf(mCount));
		} else {
			mTextView2.setText(String.valueOf(mCount));
		}

		mFlipper.showNext();
	}
	
	public void switchLayoutStateOut(int switchOut) {
		mCurrentLayoutState = switchOut;

//		mFlipper.setInAnimation(inFromLeftAnimation());
//		mFlipper.setOutAnimation(outToRightAnimation());

		mCount--;
		
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

	
	// Called to create the content view for this Fragment
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout defined in quote_fragment.xml
		// The last parameter is false because the returned view does not need to be attached to the container ViewGroup
		return inflater.inflate(R.layout.fragmentgame_settings, container, false);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		// Don't destroy Fragment on reconfiguration
		setRetainInstance(true);
		
		// This Fragment adds options to the ActionBar
		setHasOptionsMenu(true);
		
		//02/05/2014
	}

	// Set up some information about the Players Settings
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mQuoteView = (TextView) getActivity().findViewById(R.id.playerFlipquoteView);
		mQuoteArrLen = FragmentGameActivity.SettingsArray.length;
		
		//02/06 Завершающая стадия редактирования Фрагмента для Бэта
//		butStart = (Button) getActivity().findViewById(R.id.playerFlipbutStart);
		mFlipper = (ViewFlipper) getActivity().findViewById(R.id.playerFlipview_flipper);
		mTextView1 = (TextView) getActivity().findViewById(R.id.playerFliptextView1);
		mTextView2 = (TextView) getActivity().findViewById(R.id.playerFliptextView2);

		//02/12 update
		mFlipper.invalidate();
		
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCurrIdx = FragmentGameActivity.UNSELECTED;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		// Inflate the options Menu using quote_menu.xml
		inflater.inflate(R.menu.settings_menu, menu);
	
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		// Show Toast Messages. Toast Messages are discussed in the lesson on user interface classes
		// return value true indicates that the menu click has been handled

		case R.id.detail_menu_item_main:
			Toast.makeText(getActivity().getApplicationContext(),
					"Hi-Hi! Good Gamer! Result saved) ",
					Toast.LENGTH_SHORT).show();
			/*** Start open database ***/
			//02/18 GetPlayers
			if ((DatabaseRating.getPlayerFromRating(FragmentGameActivity.PlayersArray[mCurrIdx]))!= null)
			{
				int level=DatabaseRating.getLevelFromRating(FragmentGameActivity.PlayersArray[mCurrIdx])+1;
				DatabaseRating.updateLevelData_byID(FragmentGameActivity.PlayersArray[mCurrIdx], level, "Winner"); ///Integer.parseInt(FragmentGameActivity.TitleArray[howindex])
				Log.d("Read: ", "Level= "+level);
			}
			else
			{
				Log.d("Insert: ", "Inserting... "+FragmentGameActivity.PlayersArray[mCurrIdx]);
				//DatabaseRating.addUserData(new UserRatingData(FragmentGameActivity.TitleArray[mCurrIdx], mCount,"Winner"));
				DatabaseRating.addUserData(new UserRatingData(FragmentGameActivity.PlayersArray[mCurrIdx], 1,"Winner"));
			}
			
    		/*** End Open Database ***/
    		
			return true;

		case R.id.detail_menu_item_secondary:
			Toast.makeText(getActivity().getApplicationContext(),
					"This action is also provided by the QuoteFragment",
					Toast.LENGTH_SHORT).show();
			

			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
