package com.spyralem.layla.vogame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

//import com.spyralem.layla.managelist.DataListAdapter;
//import com.spyralem.layla.managelist.DataListAdapterSet;
//import com.spyralem.layla.managelist.DataListAdapterSet_History;
//import com.spyralem.layla.managelist.DataListAdapter_History;
//import com.spyralem.layla.vogame.FragmentGamePlayers.ListSelectionListener;

import com.spyralem.layla.managelist.DataListAdapter;
import com.spyralem.layla.managelist.DataListAdapterSet;
import com.spyralem.layla.managelist.DataListAdapterSet_History;
import com.spyralem.layla.managelist.DataListAdapter_History;
import com.spyralem.layla.model.PlayersData;
import com.spyralem.layla.vogame.FragmentGamePlayers.ListSelectionListener;

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

//public class FragmentGameHistory extends ListFragment implements OnItemClickListener{
public class FragmentGameHistory extends ListFragment{
	// Array of strings storing country names
    String[] countries = new String[] {
            "India",
            "Pakistan",
            "Sri Lanka",
            "China",
            "Bangladesh",
            "Nepal",
            "Afghanistan",
            "North Korea",
            "South Korea",
            "Japan"
    };    
    
    // Array of integers points to images stored in /res/drawable/
    int[] flags = new int[]{
    		R.drawable.india,
    		R.drawable.pakistan,
    		R.drawable.srilanka,
    		R.drawable.china,
    		R.drawable.bangladesh,
    		R.drawable.nepal,
    		R.drawable.afghanistan,
    		R.drawable.nkorea,
    		R.drawable.skorea,
    		R.drawable.japan	
    };
    
    // Array of strings to store currencies
    String[] currency = new String[]{
    	"Indian Rupee",
    	"Pakistani Rupee",
    	"Sri Lankan Rupee",
    	"Renminbi",
    	"Bangladeshi Taka",
    	"Nepalese Rupee",
    	"Afghani",
    	"North Korean Won",
    	"South Korean Won",
    	"Japanese Yen"
    };
    
    
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
//		
//		// Each row in the list stores country name, currency and flag
//        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();        
//        
//        for(int i=0;i<10;i++){
//        	HashMap<String, String> hm = new HashMap<String,String>();
//            hm.put("txt", "Country : " + FragmentGameActivity.PlayersArray[i]);
//            hm.put("cur","Currency : " + FragmentGameActivity.SettingsArray[i]);
//            hm.put("flag", Integer.toString(flags[i]) );            
//            aList.add(hm);        
//        }
//        
//        // Keys used in Hashmap
//        String[] from = { "flag","txt","cur" };
//        
//        // Ids of views in listview_layout
//        int[] to = { R.id.flag,R.id.txt,R.id.cur};        
//        
//        // Instantiating an adapter to store each items
//        // R.layout.listview_layout defines the layout of each item
//        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.history_activity_main, from, to);       
//		
//		setListAdapter(adapter);
//		
//		return super.onCreateView(inflater, container, savedInstanceState);		
//	}
	
	//--------------------------------Union Block Plyers
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
	            hm.put("txt", "Name : " + FragmentGameActivity.PlayersArray[i]);
	            hm.put("cur","Level : " + FragmentGameActivity.SettingsArray[i]);
	            hm.put("flag", Integer.toString(flags[rand.nextInt(n-1)]) );            
	            aList.add(hm);        
	        }
	        
	        // Keys used in Hashmap
	        String[] from = { "flag","txt","cur" };//"txt","cur" };//
	        
	        // Ids of views in listview_layout
	        int[] to = { R.id.flag,R.id.txt,R.id.cur};//R.id.txt,R.id.cur};        
	        
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
//	
//
//	String[] menutitles;
//	String[] menuIcons;
//
//	DataListAdapter_History adapter;
//	private List<DataListAdapterSet_History> rowItems;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//            Bundle savedInstanceState) {
//
//        return inflater.inflate(R.layout.fragmentgame_history, null, false);
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//
//        super.onActivityCreated(savedInstanceState);
//
//      menutitles = FragmentGameActivity.PlayersArray;
//      menuIcons = FragmentGameActivity.SettingsArray;
////        menutitles = getResources().getStringArray(R.array.titl);
////        menuIcons = getResources().obtainTypedArray(R.array.icons);
//
//      rowItems = new ArrayList<DataListAdapterSet_History>();
//
//    for (int i = 0; i < menutitles.length; i++) {
//  	DataListAdapterSet_History items = new DataListAdapterSet_History(menutitles[i], menuIcons[i]);
//
//      rowItems.add(items);
//  }
//
//    setListAdapter(new DataListAdapter_History(getActivity(), rowItems));
//    getListView().setOnItemClickListener(this);
//     
//        //getListView().setOnItemClickListener(this);
//
//    }
//
//    public void onItemClick(AdapterView<?> parent, View view, int position,
//            long id) {
//
//        Toast.makeText(getActivity(), menutitles[position], Toast.LENGTH_SHORT)
//                .show();
//
//    }
//}
	///---------------------------------------------------
//	ListSelectionListener mListener = null;
//	int mCurrIdx = -1;
//
//	String[] menutitles;
//	String[] menuIcons;
//	DataListAdapter_History adapter;
//	private List<DataListAdapterSet_History> rowItems;
//	
//	private TextView mPlayersView = null;
//	
//	// Callback interface that allows this Fragment to notify the FragmentGameSettings
//	// when user clicks on a ListItem  
//	public interface ListSelectionListener {
//		public void onListSelection(int index);
//	}
//
//	// Called when the user selects an item from the List 
//	@Override
//	public void onAttach(Activity activity) {
//		super.onAttach(activity);
//		try {
//
//			// Set the ListSelectionListener for communicating with the FragmentGameSettings
//			mListener = (ListSelectionListener) activity;
//		
//		} catch (ClassCastException e) {
//			throw new ClassCastException(activity.toString()
//					+ " must implement OnArticleSelectedListener");
//		}
//	}
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//
//		
//		// add items to the ActionBar  
//		setHasOptionsMenu(true);
//		
//		// Retain this Fragment across Activity Reconfigurations
//		setRetainInstance(true);
//	
//	}
//
//	@Override
//	public void onActivityCreated(Bundle savedState) {
//		super.onActivityCreated(savedState);
//
//		
//		// Arrays on Data Table
//        menutitles = FragmentGameActivity.PlayersArray;
//        menuIcons = FragmentGameActivity.SettingsArray;
//
//        ArrayList<String> strings=new ArrayList<String>();
//        rowItems = new ArrayList<DataListAdapterSet_History>();
//
//        for (int i = 0; i < menutitles.length; i++) {
//        	DataListAdapterSet_History items = new DataListAdapterSet_History(menutitles[i], menuIcons[i]);
//
//            rowItems.add(items);
//        }
//
//       // DataListAdapter_History adapter = new DataListAdapter_History(getActivity(), mCurrIdx, rowItems);
//       // setListAdapter(adapter);
////        getListView().setOnItemClickListener(this);
//		// Set the list adapter for the ListView 
//        adapter = new DataListAdapter_History(getActivity(), R.layout.fragmentgame_history, rowItems);
//        
//		setListAdapter(adapter);
//        
//		//adapter=new DataListAdapter_History(getActivity(), , rowItems);
//			
//		//setListAdapter(adapter);
//
//		
//		// If a title has already been selected in the past, reset the selection state now
//		if (mCurrIdx != FragmentGameActivity.UNSELECTED) {
//			setSelection(mCurrIdx);
//		}
//	}
//
//	// This metod created View
////	@Override
////	public View onCreatView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
////		return inflater.inflate(R.layout.fragmentgame_history, null);
////	}
////	public View onCreatView(LayoutInflater inflater, ViewGroup container, Bundle savedState){
////		return inflater.inflate(R.layout.fragmentgame_history, null);
////	}
//	// Called when the user selects an item from the List
//	@Override
//	public void onListItemClick(ListView l, View v, int pos, long id) {
//		mCurrIdx = pos;
//
//		// Indicates the selected item has been checked
//		getListView().setItemChecked(pos, true);
//		
//		// Inform the SeettingsViewerActivity that the item in position has been selected
//		mListener.onListSelection(pos);
//	}
//
//	@Override
//	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
//		// Inflate the options Menu using players_menu.xml
//		inflater.inflate(R.menu.players_menu, menu);
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		//final Context context=this;
//		
//		switch (item.getItemId()) {
//		
//		case R.id.title_menu_item:
////			Intent intent=new Intent(this, RatingActivity.class);
////			this.startActivity(intent);
//			
//			// Show a Toast Message. Arrrh! Disable before release
//			Toast.makeText(getActivity().getApplicationContext(),
//					"This action provided by FragmentGamePlayers",
//					Toast.LENGTH_SHORT).show();
//
//			// that the menu click has been handled, then return value true indicates 
//			return true;
//		
//		default:
//			return super.onOptionsItemSelected(item);
//		}
//	}
//}

//
//public class FragmentGameHistory extends Fragment  {
//
//	private DataListAdapter_History adapter;
//	private int num;
//	private int nPlayer;
//
//	ListView NewHistoryListView;
//	
//	ListSelectionListener mListener = null;
//	int mCurrIdx = -1;
//
////	// Callback interface that allows this Fragment to notify the FragmentGameSettings
////	// when user clicks on a ListItem  
////	public interface ListSelectionListener {
////		public void onListSelection(int index);
////	}
//
//	// Called when the user selects an item from the List 
//	@Override
//	public void onAttach(Activity activity) {
//		super.onAttach(activity);
//		try {
//
//			// Set the ListSelectionListener for communicating with the FragmentGameSettings
//			mListener = (ListSelectionListener) activity;
//		
//		} catch (ClassCastException e) {
//			throw new ClassCastException(activity.toString()
//					+ " must implement OnArticleSelectedListener");
//		}
//	}
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//
//		// add items to the ActionBar  
//		setHasOptionsMenu(true);
//		
//		// Retain this Fragment across Activity Reconfigurations
//		setRetainInstance(true);
//	}
//
////	@Override
////	public void onActivityCreated(Bundle savedState) {
////		super.onActivityCreated(savedState);
////
////		// 03/26 Test
////		
////		// Set the list adapter for the ListView 
////		setListAdapter(new ArrayAdapter<String>(getActivity(),
////				R.id.history_players, FragmentGameActivity.PlayersArray));
////
////		
////		// If a title has already been selected in the past, reset the selection state now
////		if (mCurrIdx != FragmentGameActivity.UNSELECTED) {
////			setSelection(mCurrIdx);
////		}
////	}
////
////	// Called when the user selects an item from the List
////	@Override
////	public void onListItemClick(ListView l, View v, int pos, long id) {
////		mCurrIdx = pos;
////
////		// Indicates the selected item has been checked
////		getListView().setItemChecked(pos, true);
////		
////		// Inform the SeettingsViewerActivity that the item in position has been selected
////		mListener.onListSelection(pos);
////	}
////	private void setupListViewAdapter() {
////
////		adapter = new DataListAdapter_History(this, R.layout.fragmentgame_history, new ArrayList<DataListAdapterSet_History>());
////		NewHistoryListView = (ListView)findViewById(R.id.history_level);
////
////		NewHistoryListView.setAdapter(adapter);
////	}
////
////	private void setupPlayersInListView(){
////		
////		Intent intent=getIntent();
////		nPlayer=intent.getIntExtra("NUM", num);
////		//Toast.makeText(ControlActivity.this, "ClickActivity 2="+nPlayer, Toast.LENGTH_LONG).show();
////
////		// Insert Number from Activity1 
////		for (int i=0;i<nPlayer;i++){
////			adapter.insert(new DataListAdapterSet("", 0), 0);
////		}
////	}
//}
