package com.lepotuli.layla.vogame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

//import com.spyralem.layla.managelist.DataListAdapter;
//import com.spyralem.layla.managelist.DataListAdapterSet;
//import com.spyralem.layla.managelist.DataListAdapterSet_History;
//import com.spyralem.layla.managelist.DataListAdapter_History;
//import com.spyralem.layla.vogame.FragmentGamePlayers.ListSelectionListener;

import com.lepotuli.layla.managelist.DataListAdapter;
import com.lepotuli.layla.managelist.DataListAdapterSet;
import com.lepotuli.layla.managelist.DataListAdapterSet_History;
import com.lepotuli.layla.managelist.DataListAdapter_History;
import com.lepotuli.layla.model.PlayersData;
import com.lepotuli.layla.vogame.R;
import com.lepotuli.layla.vogame.FragmentGamePlayers.ListSelectionListener;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

/*
 * @author Merkulov Maksim (DomenZero) 
 * <wardomenmax@gmail.com>
 * lepotuli.com
 * 
 */

//public class FragmentGameHistory extends ListFragment implements OnItemClickListener{
public class FragmentGameHistory extends ListFragment{
    
    // Array of integers points to images stored in /res/drawable/
    int[] flags = new int[]{
    		R.drawable.d2
    };
    
	//--------------------------------Union Block Players
	ListSelectionListener mListener = null;
	int mCurrIdx = -1;

	// Callback interface that allows this Fragment to notify the FragmentGameSettings
	// when user clicks on a ListItem  
//	public interface ListSelectionListener {
//		public void onListSelection(int index);
//	}

	// Called when the user selects an item from the List 
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {

			// Set the ListSelectionListener for communicating with the FragmentGameSettings
			mListener = (ListSelectionListener) activity;
		
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnArticleSelectedListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		// add items to the ActionBar  
		setHasOptionsMenu(true);
		
		// Retain this Fragment across Activity Reconfigurations
		setRetainInstance(true);
	
	}

	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);

		// Set the list adapter for the ListView 
//		setListAdapter(new ArrayAdapter<String>(getActivity(),
//				R.layout.fragmentgame_players, FragmentGameActivity.PlayersArray));
//		setListAdapter(new ArrayAdapter<String>(getActivity(),
//				R.layout.fragmentgame_players, FragmentGameActivity.PlayersArray));
		

		unionBlock();
	}

	// Union Block 
	public void unionBlock(){
		//-------------------------------------union
		// Each row in the list stores country name, currency and flag
		  List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();        
	        int n=FragmentGameActivity.NumberPlayers;
	        Random rand=new Random();
	       
	        for(int i=0;i<n;i++){
	        	HashMap<String, String> hm = new HashMap<String,String>();
	            hm.put("name", getString(R.string.fragment_game_name)+" " + FragmentGameActivity.PlayersArray[i]);
	            hm.put("level",getString(R.string.fragment_game_level)+" " + FragmentGameActivity.SettingsArray[i]);
	            hm.put("avatar", Integer.toString(flags[rand.nextInt(flags.length)]) );            
	            aList.add(hm);        
	        }
	        
	        // Keys used in Hashmap
	        String[] from = { "avatar","name","level" };
	        
	        // Ids of views in listview_layout
	        int[] to = { R.id.avatar,R.id.name,R.id.level};//R.id.txt,R.id.cur};        
	        
	        // Instantiating an adapter to store each items
	        // R.layout.listview_layout defines the layout of each item
	        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.history_activity_main, from, to);       
			
			setListAdapter(adapter);
			
			// If a title has already been selected in the past, reset the selection state now
			if (mCurrIdx != FragmentGameActivity.UNSELECTED) {
				setSelection(mCurrIdx);
			}
	}
	
	// Called when the user selects an item from the List
	@Override
	public void onListItemClick(ListView l, View v, int pos, long id) {
		mCurrIdx = pos;

		// Indicates the selected item has been checked
		getListView().setItemChecked(pos, true);
		
		// temp union
		unionBlock();
		
		// 04/07 Update
//		int mCount=Integer.parseInt(FragmentGameActivity.SettingsArray[mCurrIdx]);
//		
//		FragmentGameSettings.switchLayoutStateOut());//.setText(String.valueOf(mCount));
		
		// Inform the SeettingsViewerActivity that the item in position has been selected
		mListener.onListSelection(pos);
		

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		// Inflate the options Menu using players_menu.xml
		inflater.inflate(R.menu.players_menu, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//final Context context=this;
		
		switch (item.getItemId()) {
		
		case R.id.title_menu_item:
//			Intent intent=new Intent(this, RatingActivity.class);
//			this.startActivity(intent);
			
			// Show a Toast Message. Arrrh! Disable before release
//			Toast.makeText(getActivity().getApplicationContext(),
//					"This action provided by FragmentGamePlayers",
//					Toast.LENGTH_SHORT).show();

			//!Test
			
			// temp union
			unionBlock();
			// that the menu click has been handled, then return value true indicates 
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}