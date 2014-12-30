package com.spyralem.layla.vogame;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.DTDHandler;

import com.spyralem.layla.model.PlayersData;
import com.spyralem.layla.model.UserRatingData;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.spyralem.layla.model.UserRatingData;
import com.spyralem.layla.vogame.FragmentGamePlayers.ListSelectionListener;

public class FragmentGameActivity extends Activity implements ListSelectionListener{

	public static String[] TitleArray;
	public static String[] QuoteArray;
	private final FragmentGamePlayers mTitlesFragment = new FragmentGamePlayers();
	private final FragmentGameSettings mDetailsFragment = new FragmentGameSettings();
	public static final int UNSELECTED = -1;
	private FragmentManager mFragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		/***Test Permanento)))***/
		DatabaseRating.init(this);
		super.onCreate(savedInstanceState);
        setContentView(R.layout.rating);
		
		//Input data in table
		//Log.d("Insert: ", "Inserting...");
		//DatabaseRating.addUserData(new UserRatingData("Layla",1,"Best"));
		
//		//Reading All contacts
		Log.d("Reading: ","Reading All data");
		List<PlayersData> data=DatabaseRating.getAllPlayersData();
		int kol=0;
		for (PlayersData dt:data) {
			String log=" Id: "+dt.getID()+" User Name: "+dt.getUserName()+" Level: "+dt.getUserLevel()+" Color: "+dt.getUserColor();
			Log.d("User Name", log);
			kol++;
//   		 	TextView textView=(TextView)findViewById(R.id.teView);
//   		 	textView.setText(" Id: "+dt.getID()+" User Name: "+dt.getUserName()+" Rating: "+dt.getUserRating()+" Status: "+dt.getUserStatus());
		}
		 int USER_ROWS=4;
	     int USER_COLUMNS=4;
	        
	     //variable
	     ArrayList<String> strings=new ArrayList<String>();
	     String[] arrmas=new String[kol]; 
	     int counter=0;

	     for (PlayersData dt:data) {
	     //for (int i = 0; i < USER_ROWS; i++) {
	    	 TableRow tableRow=new TableRow(this);
	    	 tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
	    			 LayoutParams.WRAP_CONTENT));
	    	 //tableRow.setBackgroundResource(R.drawable.ic_launcher);	    	 
	        //In massiv Title	
	    	 for (int j = 0; j < 1; j++) {
	    		 strings.add(dt.getUserName());	        		
	    		 Log.d("User Name", dt.getUserName());
	    		 if(counter<arrmas.length){
	    			 arrmas[counter]=dt.getUserName();
	    			 counter++;
	    		 }
	    	 
	    	 }
	    	 
	        	
	    // }
		}
		/***EndT***/
		// Get the string arrays with the titles and qutoes
		//TitleArray = getResources().getStringArray(R.array.Titles);
		TitleArray = arrmas;
		QuoteArray = getResources().getStringArray(R.array.Quotes);
		
		setContentView(R.layout.fragmentgame_main);

		// Get a reference to the FragmentManager
		mFragmentManager = getFragmentManager();

		// Start a new FragmentTransaction
		FragmentTransaction fragmentTransaction = mFragmentManager
				.beginTransaction();

		// Add the TitleFragment to the layout
		fragmentTransaction.add(R.id.title_fragment_container, mTitlesFragment);
		
		// Commit the FragmentTransaction
		fragmentTransaction.commit();
	
	}

	// Called when the user selects an item in the TitlesFragment
	@Override
	public void onListSelection(int index) {

		// If the QuoteFragment has not been added, add it now
		if (!mDetailsFragment.isAdded()) {
		
			// Start a new FragmentTransaction
			FragmentTransaction fragmentTransaction = mFragmentManager
					.beginTransaction();
			
			// Add the QuoteFragment to the layout
			fragmentTransaction.add(R.id.quote_fragment_container, mDetailsFragment);
			
			// Add this FragmentTransaction to the backstack
			fragmentTransaction.addToBackStack(null);
			
			// Commit the FragmentTransaction
			fragmentTransaction.commit();
			
			// Force Android to execute the committed FragmentTransaction
			mFragmentManager.executePendingTransactions();
		}
		
		if (mDetailsFragment.getShownIndex() != index) {

			// Tell the QuoteFragment to show the quote string at position index
			mDetailsFragment.showQuoteAtIndex(index);
		
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

			// Show a Toast Message. Toast Messages are discussed in the lesson on user interface classes
			Toast.makeText(getApplicationContext(),
					"This action provided by the QuoteViewerActivity", Toast.LENGTH_SHORT)
					.show();
			
			// return value true indicates that the menu click has been handled
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
