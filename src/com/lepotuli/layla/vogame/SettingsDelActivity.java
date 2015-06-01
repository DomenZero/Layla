package com.lepotuli.layla.vogame;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.Toast;

import com.lepotuli.layla.managelist.DataListAdapterSet_DelSettings;
import com.lepotuli.layla.managelist.DataListAdapter_DelSettings;
import com.lepotuli.layla.model.PlayersData;

public class SettingsDelActivity extends Activity{

	//-------
	public static String[] PlayersArray;
	public static String[] SettingsArray;
	
	public static Integer[] IDArray;
	
	public static int NumberPlayers;
	
	//--------
	protected static final String EXTRA_RES_NUM = "NUM";
	final String LOG_TAG="Log prog";
	
	private DataListAdapter_DelSettings adapter;
	private int num=3;
	private int nPlayer;

	ListView NewPlayersListView;
	
	String fName="players";
	String pString="";		
	int savePlayers=0;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		DatabaseRating.init(this);
		super.onCreate(savedInstanceState);
		// style of Layla
		styleUtils.onActivitySetTheme(this);
		setContentView(R.layout.listusers_delactivity);
		
		mainWindow();
		setupListViewAdapter();
		setupPlayersInListView();
		
		this.deleteFile(fName);
		//File file = new File(this.getFilesDir(),fName);
		//---
	}

	/*** Punkt 1. Menu ***/
	// Create Options Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.top_menu_del, menu);
		return true;
	}
	
	// Process clicks on Options Menu items
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_del_rating:
//			Toast.makeText(getApplicationContext(), "you've been helped",
//					Toast.LENGTH_SHORT).show();
			DatabaseRating.delPlayersDataAll_Rating();
			return true;
		case R.id.menu_del_players:
//			Toast.makeText(getApplicationContext(), "you've been helped more",
//					Toast.LENGTH_SHORT).show();
			DatabaseRating.delPlayersDataAll_Players();
			return true;
		default:
			return false;
		}
	}
	/******** ----*/
	// Delete element from table if pressed button 
	public void removeAtomPayOnClickHandler(View v) {
		DataListAdapterSet_DelSettings itemToDel = (DataListAdapterSet_DelSettings)v.getTag();

		pString="";

		// Delete element on UI Form
		nPlayer--;
//		Toast.makeText(SettingsDelActivity.this, "ClickActivity 2="+itemToDel.getName()+"--- "+itemToDel.getId(), Toast.LENGTH_LONG).show();

		Toast.makeText(SettingsDelActivity.this, this.getString(R.string.word_del_Player)+" "+itemToDel.getName()+" "+this.getString(R.string.word_del_Clear), Toast.LENGTH_LONG).show();
		pString=itemToDel.getName();

		//add Players in file
		try{
			//****************
//			/*** Start open database ***/
//
//			DatabaseRating.addPlayersData(new PlayersData(pString,1,"Color","Avatar","Settings"));
//			/*** End Open Database ***/
//			
//			fOut.write(pString);
//			fOut.write('\n');
//			fOut.close();
//			
//			
			//************
			OutputStreamWriter fOut=new OutputStreamWriter(openFileOutput(fName, ControlActivity.MODE_APPEND));
			
			/*** Start open database ***/
			DatabaseRating.init(SettingsDelActivity.this);
			Log.d("deliting: ", "deliting...");

			DatabaseRating.delPlayersData_byID(itemToDel.getId());
			
			/*** End Open Database ***/
//			fOut.write(pString);
//			fOut.write('\n');
//			fOut.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		adapter.remove(itemToDel);
	}

	/***!TEST READ BD***/
	public void mainWindow(){
		Log.d("Reading: ","Reading All data");
		List<PlayersData> data=DatabaseRating.getAllPlayersData();
		int kol=0;
		for (PlayersData dt:data) {
			String log=" Id: "+dt.getID()+" User Name: "+dt.getUserName()+" Level: "+dt.getUserLevel()+" Color: "+dt.getUserColor()+" Avatar: "+dt.getUserAvatar()+" Settings: "+dt.getUserSettings();
			Log.d("User Name", log);
			kol++;
		}
		 int USER_ROWS=6;
	     int USER_COLUMNS=6;
	        
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
	/***!ENDTEST READ BD***/
	/*** Private element save/delete/create ***/
	// Created Insert player form
	private void setupListViewAdapter() {

		adapter = new DataListAdapter_DelSettings(this, R.layout.listusers_delsettings, new ArrayList<DataListAdapterSet_DelSettings>());
		NewPlayersListView = (ListView)findViewById(R.id.DelPlayers_PlayersList);

		NewPlayersListView.setAdapter(adapter);
	}

	private void setupPlayersInListView(){
		
//		nPlayer=num;
		//Toast.makeText(ControlActivity.this, "ClickActivity 2="+nPlayer, Toast.LENGTH_LONG).show();

		// Insert Number from Activity1 
		for (int i=0;i<NumberPlayers;i++){
			adapter.insert(new DataListAdapterSet_DelSettings(PlayersArray[i], IDArray[i]), 0);
		}
	}
	
	@Override
	public void onBackPressed(){
		finish();
		Intent myintent=new Intent(SettingsDelActivity.this, MainActivity.class);
		startActivity(myintent);
	}
}
