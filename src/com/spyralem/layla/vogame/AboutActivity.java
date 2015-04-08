package com.spyralem.layla.vogame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
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

public class AboutActivity extends Activity{

	EditText editT;
	AutoCompleteTextView editText;
	private Button Update;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		styleUtils.onActivitySetTheme(this);
	
		
        setContentView(R.layout.game);
        addListenerOnButton();
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
		switch (item.getItemId()) {
		case R.id.menu_settings2:
			Toast.makeText(getApplicationContext(), "you've been helped",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menu_rating2:
			Toast.makeText(getApplicationContext(), "you've been helped more",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menu_play2:
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
	
	// Button Listener
    public void addListenerOnButton(){
    	final Context context=this;
    	Update=(Button) findViewById(R.id.butOver);
    	Update.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v){
    			
    			DatabaseRating.delPlayersData_All();
    		}
    	});
    }

}
