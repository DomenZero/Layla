package com.spyralem.layla.vogame;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.DTDHandler;

import com.spyralem.layla.model.PlayersData;
import com.spyralem.layla.model.UserRatingData;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.spyralem.layla.model.UserRatingData;
import com.spyralem.layla.vogame.FragmentGamePlayers.ListSelectionListener;

/***
 * @author Merkulov Maksim (DomenZero) 
 * <wardomenmax@gmail.com>
 * 
 * Activity Fragment UI-panel 
 * 
 * Managed Players & Settings Fragment
 * 
 ***/

public class FragmentGameActivity extends Activity implements ListSelectionListener{

	public static String[] PlayersArray;
	public static String[] SettingsArray;
	
	private final FragmentGamePlayers mPlayersFragment = new FragmentGamePlayers();
	private final FragmentGameHistory mHistoryFragment = new FragmentGameHistory();
	private final RatingActivity mRatingFragment = new RatingActivity();
	
	private final FragmentGameSettings mDetailsFragment = new FragmentGameSettings();
//	private final FragmentGameHistory mHistoryFragment = new FragmentGameHistory();
	
	public static final int UNSELECTED = -1;
	
	private FragmentManager mFragmentManager;
	private FragmentManager mFragmentManager2;

	public static Integer[] IDArray;
	
	public static int NumberPlayers;
	public static int howindex; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		/***Test Permanento)))***/
		DatabaseRating.init(this);
		super.onCreate(savedInstanceState);
		//load style
		styleUtils.onActivitySetTheme(this);
		//setContentView(R.layout.rating);
		
        mainWindow();
        
		setContentView(R.layout.fragmentgame_main);

		// Get a reference to the FragmentManager
		mFragmentManager = getFragmentManager();

		// Start a new FragmentTransaction
		FragmentTransaction fragmentTransaction = mFragmentManager
				.beginTransaction();

		// Add the PlayersFragment to the layout
		// 03/26 Reselect on History
		fragmentTransaction.add(R.id.players_fragment_container, mHistoryFragment);
		//fragmentTransaction.add(R.id.players_fragment_container, mPlayersFragment);

		
		// Commit the FragmentTransaction
		fragmentTransaction.commit();
		
//		//___________________03/24
//		// Get a reference to the FragmentManager
//		mFragmentManager2 = getFragmentManager();
//
//		// Start a new FragmentTransaction
//		FragmentTransaction fragmentTransaction2 = mFragmentManager2
//				.beginTransaction();
//
//		// Add the PlayersFragment to the layout
//		fragmentTransaction.add(R.id.history_fragment_container, mHistoryFragment);
//		
//		// Commit the FragmentTransaction
//		fragmentTransaction.commit();	
//		
	}

	
	// Called when the user selects an item in the PlayersFragment
	@Override
	public void onListSelection(int index) {
		//02/12 clear Fragment Update
		Log.d("Update: ", "ing..."+index+" to ");

		howindex=index;
		
		// Get elements Player
		String log=" Id: "+DatabaseRating.getPlayer(IDArray[index]);//dt.getID()+" User Name: "+dt.getUserName()+" Level: "+dt.getUserLevel()+" Color: "+dt.getUserColor();
		Log.d("User Name", log);
				
		// If the SettingsFragment has not been added, add it now
		if (!mDetailsFragment.isAdded()) {
			
		
			// Start a new FragmentTransaction
			FragmentTransaction fragmentTransaction = mFragmentManager
					.beginTransaction();
					
			// Add the SettingsFragment to the layout
			fragmentTransaction.add(R.id.settings_fragment_container, mDetailsFragment);	
			
			// Add this FragmentTransaction to the backstack
			fragmentTransaction.addToBackStack(null);
			
			// Commit the FragmentTransaction
			fragmentTransaction.commit();
			
			// Force Android to execute the committed FragmentTransaction
			mFragmentManager.executePendingTransactions();			
		}
		
		if (mDetailsFragment.getShownIndex() != index) {

			// Tell the SettingsFragment to show the Settings at position index
			mDetailsFragment.showSettingsAtIndex(index);
		
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Get a reference to the MenuInflater
		MenuInflater inflater = getMenuInflater();
		
		// Inflate the menu using activity_menu.xml
		inflater.inflate(R.menu.activity_menu, menu);
		
		// Return true to display the menu
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.activity_menu_item:

			// Show a Toast Message.
			Toast.makeText(getApplicationContext(),
					"This action provided by the FragmentGameActivity "+PlayersArray[howindex], Toast.LENGTH_SHORT)
					.show();
			
			//02/06 определение номера игрока в листе Следует добавить запись из левел поля
			//обновление, верхнее меню
			DatabaseRating db=new DatabaseRating();
			db.updatePlayersData_byID(DatabaseRating.getPlayer(IDArray[howindex]), mDetailsFragment.wtisLevel(), "Color", IDArray[howindex]); ///Integer.parseInt(FragmentGameActivity.Array[howindex])
			mainWindow();
			
			// return value true indicates that the menu click has been handled
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void mainWindow(){
		Log.d("Reading: ","Reading All data");
		List<PlayersData> data=DatabaseRating.getAllPlayersData();
		int kol=0;
		for (PlayersData dt:data) {
			String log=" Id: "+dt.getID()+" User Name: "+dt.getUserName()+" Level: "+dt.getUserLevel()+" Color: "+dt.getUserColor();
			Log.d("User Name", log);
			kol++;
		}
		 int USER_ROWS=4;
	     int USER_COLUMNS=4;
	        
	     //variable
	     ArrayList<String> strings=new ArrayList<String>();
	     String[] arrmas=new String[kol];
	     String[] levelmas=new String[kol];
	     
	     Integer[] idmas=new Integer[kol];
	     
	     int counter=0;

	     for (PlayersData dt:data) {
	     //for (int i = 0; i < USER_ROWS; i++) {
	    	 TableRow tableRow=new TableRow(this);
	    	 tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
	    			 LayoutParams.WRAP_CONTENT));
	    	//tableRow.setBackgroundResource(R.drawable.ic_launcher);	    	 
	        //In massive Players	
	    	 for (int j = 0; j < 1; j++) {
	    		 strings.add(dt.getUserName());	        		
	    		 Log.d("User Name", dt.getUserName());
	    		 if(counter<arrmas.length){
	    			 //03/05 ^temporarily!!! not good procedure see level by Player name
	    			 //arrmas[counter]=dt.getUserName()+"_____"+String.valueOf(dt.getUserLevel());
	    			 //---
	    			 //arrmas[counter]=dt.getUserName()+"_____"+String.valueOf(dt.getUserLevel());
	    			 arrmas[counter]=dt.getUserName();
	    			 levelmas[counter]=String.valueOf(dt.getUserLevel());
	    			 idmas[counter]=dt.getID();
	    			 counter++;
	    		 }
	    	 }
		}
		/***EndT***/
	     
		// Get the string arrays with the Players & Settings (level)
		PlayersArray = arrmas;
		SettingsArray = levelmas;
		
		// And Id
		IDArray = idmas;
		
		//Number Players
		NumberPlayers=kol;
	}
}
