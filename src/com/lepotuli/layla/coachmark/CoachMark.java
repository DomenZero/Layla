package com.lepotuli.layla.coachmark;

import java.util.prefs.Preferences;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

public class CoachMark {

	public void onCoachMark(Activity activity){
		// Help on any Activity
		final Context context=activity;
		final Dialog dialog=new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		// Add any coaching layout 
		//		dialog.setContentView(); 
		dialog.setCanceledOnTouchOutside(true);
		
////		View coachView=dialog.findViewById(R.id.);
//		coachView.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				dialog.dismiss();
//				preferences.edit().putBoolean("CO", true).commit();
//			}
//		});
//		dialog.show();
	}
}
