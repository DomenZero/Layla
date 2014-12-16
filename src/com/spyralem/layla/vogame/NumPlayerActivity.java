package com.spyralem.layla.vogame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class NumPlayerActivity extends Activity {
	private ViewFlipper mFlipper;
	private TextView mTextView1, mTextView2;
	private int mCurrentLayoutState, mCount;
	private GestureDetector mGestureDetector;
	private Button butStart;
	protected static final String EXTRA_RES_NUM = "NUM";

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_first);

		mCurrentLayoutState = 0;

		mFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
		mTextView1 = (TextView) findViewById(R.id.textView1);
		mTextView2 = (TextView) findViewById(R.id.textView2);

		mTextView1.setText(String.valueOf(mCount));

		mGestureDetector = new GestureDetector(this,
				new GestureDetector.SimpleOnGestureListener() {
					@Override
					public boolean onFling(MotionEvent e1, MotionEvent e2,
							float velocityX, float velocityY) {
						if (velocityX < -10.0f) {
							mCurrentLayoutState = mCurrentLayoutState == 0 ? 1
									: 0;
							switchLayoutStateTo(mCurrentLayoutState);
						}
						else
						if (mCount>0 & velocityX > 10.0f) {
							mCurrentLayoutState = mCurrentLayoutState == 0 ? 1
									: 0;
							switchLayoutStateOut(mCurrentLayoutState);
							}
						return true;
					}
				});
		addListenerOnButton();
	}

    public void addListenerOnButton(){
    	final Context context=this;
    	butStart=(Button) findViewById(R.id.butStart);
    	butStart.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View arg0){
    			
    			//Intent intent=new Intent(context, GameActivity.class);
    			Intent intent=new Intent(context, AboutActivity.class);
    			Toast.makeText(NumPlayerActivity.this, "Click 1="+mCount, Toast.LENGTH_LONG).show();
    			intent.putExtra(EXTRA_RES_NUM, mCount);
    			
    			onStop();
    			onDestroy();
    			startActivity(intent);
    		}
    	});
    }
    
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}

	public void switchLayoutStateTo(int switchTo) {
		mCurrentLayoutState = switchTo;

		mFlipper.setInAnimation(inFromRightAnimation());
		mFlipper.setOutAnimation(outToLeftAnimation());

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

		mFlipper.setInAnimation(inFromLeftAnimation());
		mFlipper.setOutAnimation(outToRightAnimation());

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
}