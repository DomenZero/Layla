package com.spyralem.layla.vogame;

import com.spyralem.layla.model.PlayersData;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/*** Fragment UI-panel Players
***/

public class FragmentGamePlayers extends ListFragment{
	ListSelectionListener mListener = null;
	int mCurrIdx = -1;

	// Callback interface that allows this Fragment to notify the FragmentGameSettings
	// when user clicks on a ListItem  
	public interface ListSelectionListener {
		public void onListSelection(int index);
	}

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
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				R.layout.fragmentgame_players, FragmentGameActivity.PlayersArray));

		
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
			Toast.makeText(getActivity().getApplicationContext(),
					"This action provided by FragmentGamePlayers",
					Toast.LENGTH_SHORT).show();

			// that the menu click has been handled, then return value true indicates 
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
