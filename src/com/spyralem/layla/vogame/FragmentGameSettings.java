package com.spyralem.layla.vogame;

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
	private int mCurrentLayoutState, mCount;
	private GestureDetector mGestureDetector;
	private Button butStart;


	
	// Returns currently selected item
	public int getShownIndex() {
		return mCurrIdx;
	}

	// Show the Quote string at position newIndex
	public void showQuoteAtIndex(int newIndex) {
		if (newIndex < 0 || newIndex >= mQuoteArrLen)
			return;
		mCurrIdx = newIndex;
		mQuoteView.setText(FragmentGameActivity.QuoteArray[mCurrIdx]);
		
		
		//02/06 Завершающая стадия редактирования Фрагмента для Бэта
		butStart.setText(FragmentGameActivity.QuoteArray[mCurrIdx]);
		addListenerOnButton();
		
		//02/09
		//mTextView1.setText(String.valueOf(2));
		//mTextView1.setText(FragmentGameActivity.QuoteArray[mCurrIdx]);
		final GestureDetector gesture = new GestureDetector(getActivity(),
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
						try{
							if(Math.abs(e1.getY()-e2.getY())>SWIPE_MAX_OFF_PATH)
								return false;
							if(e1.getX()-e2.getX()>SWIPE_MIN_DISTANCE && Math.abs(velocityX)>SWIPE_THRESHOLD_VELOCITY){
								Log.i("gesture","Right to Left!");
							}else if(e2.getX()-e1.getX()>SWIPE_MIN_DISTANCE && Math.abs(velocityX)>SWIPE_THRESHOLD_VELOCITY){
								Log.i("gesture","Left to Right");
							}		
						} catch (Exception e){
							
						}
						return super.onFling(e1, e2, velocityX, velocityY);
//						if (velocityX < -10.0f) {
//							mCurrentLayoutState = mCurrentLayoutState == 0 ? 1
//									: 0;
//						//	switchLayoutStateTo(mCurrentLayoutState);
//						}
//						else
//						if (mCount>0 & velocityX > 10.0f) {
//							mCurrentLayoutState = mCurrentLayoutState == 0 ? 1
//									: 0;
//							//switchLayoutStateOut(mCurrentLayoutState);
//							}
//						return true;
					}
				});
		
		mQuoteView.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return gesture.onTouchEvent(event);
			}
		});
	}

	//button "Save" Listener
	public void addListenerOnButton(){
    	butStart.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v){
    			Log.d("SaveClick: ", "saving..."+mQuoteView.getText());//+index+" to ");

    			//True Update Settings
//    			DatabaseRating db=new DatabaseRating();
//
//    			db.updatePlayersData_byID(DatabaseRating.getPlayer(IDArray[index]), "0", "Color", IDArray[index]);
    			
    			//Intent intent=new Intent(context, GameActivity.class);
//    			Intent intent=new Intent(context, AboutActivity.class);
//    			Toast.makeText(NumPlayerActivity.this, "Click 1="+mCount, Toast.LENGTH_LONG).show();
//    			intent.putExtra(EXTRA_RES_NUM, mCount);
//    			
//    			onStop();
//    			onDestroy();
//    			startActivity(intent);
    		}
    	});
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

	// Set up some information about the mQuoteView TextView 
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mQuoteView = (TextView) getActivity().findViewById(R.id.playerFlipquoteView);
		mQuoteArrLen = FragmentGameActivity.QuoteArray.length;
		
		//02/06 Завершающая стадия редактирования Фрагмента для Бэта
		butStart = (Button) getActivity().findViewById(R.id.playerFlipbutStart);
		mFlipper = (ViewFlipper) getActivity().findViewById(R.id.playerFlipview_flipper);
		mTextView1 = (TextView) getActivity().findViewById(R.id.playerFliptextView1);
		mTextView2 = (TextView) getActivity().findViewById(R.id.playerFliptextView2);

		//02/09


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
					"This action provided by the QuoteFragment",
					Toast.LENGTH_SHORT).show();
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
