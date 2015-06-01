package com.lepotuli.layla.vogame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/*
 * @author Merkulov Maksim (DomenZero) 
 * <wardomenmax@gmail.com>
 * 
 * Instruction. How Layla used?
 */
import com.lepotuli.layla.vogame.R;

public class AboutActivity extends Activity{

	EditText editT;
	AutoCompleteTextView editText;
	private Button Update;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		styleUtils.onActivitySetTheme(this);
			
        setContentView(R.layout.game);
        // Set screen orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        addListenerOnButton();
	}
	
	/*** Punkt 1. Menu ***/
	// Create Options Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.top_menu, menu);
		return true;
	}
	
	// Process clicks on Options Menu items
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		final Context context=this;
		
		switch (item.getItemId()) {
		case R.id.menu_settings2:
			startAnyActivity(context, MainActivity.class);			
//			DatabaseRating.delPlayersDataAll_Players();
			return true;
		case R.id.menu_rating2:
			startAnyActivity(context, RatingActivity.class);
//			Toast.makeText(getApplicationContext(), "you've been helped more",
//					Toast.LENGTH_SHORT).show();
//			DatabaseRating.delPlayersDataAll_Rating();
			return true;
		case R.id.menu_play2:
			startAnyActivity(context, FragmentGameActivity.class);
			return true;
		default:
			return false;
		}
	}
	
	/*** Void Context menu position ***/
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;
		menu.setHeaderIcon(android.R.drawable.btn_star_big_on);
		menu.setHeaderTitle("Position "+info.position);
		

	}
	

	// Menu position selected
	@Override
	public boolean onContextItemSelected(MenuItem item){
		AdapterView.AdapterContextMenuInfo info=
				(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		int menuItemIndex=item.getItemId();
		String[] menuItems={};
		String CmdName=menuItems[menuItemIndex];

		// Check Event Command
		if (menuItems[menuItemIndex].equals(CmdName)) {		
			
			editText=(AutoCompleteTextView) findViewById(R.id.itemEdit);
			editText.setText(menuItems[menuItemIndex]);
			//Toast.makeText(this, "Click 1=", Toast.LENGTH_LONG).show();
		}  
		return true;
		
	}
	
	// Start Activity Punkt Menu
	public void startAnyActivity(final Context context, final Class<?> classic){
	Intent intent=new Intent(context, classic);
	startActivity(intent);
	}
	
	// Button Listener
//    public void addListenerOnButton(){
//    	final Context context=this;
//    	Update=(Button) findViewById(R.id.butdelRating);
//    	Update.setOnClickListener(new OnClickListener() {
//    		@Override
//    		public void onClick(View v){
//    			
//    			DatabaseRating.delPlayersDataAll_Rating();
//    		}
//    	});
//    	
//    	Update=(Button) findViewById(R.id.butdelPlayers);
//    	Update.setOnClickListener(new OnClickListener() {
//    		@Override
//    		public void onClick(View v){
//    			
//    			DatabaseRating.delPlayersDataAll_Players();
//    		}
//    	});
//    }

}
