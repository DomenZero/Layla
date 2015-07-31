package com.lepotuli.layla.vogame;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment.SavedState;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.lepotuli.layla.managelist.DataListAdapter;
import com.lepotuli.layla.managelist.DataListAdapterSet;
import com.lepotuli.layla.model.PlayersData;
import com.lepotuli.layla.model.UserRatingData;
import com.lepotuli.layla.vogame.FileManager;
import com.lepotuli.layla.vogame.R;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;


/*
 * @author Merkulov Maksim (DomenZero) 
 * <wardomenmax@gmail.com>
 * lepotuli.com
 * 
 * UI Add/Delete Players from table "players"
 */

public class ControlActivity extends Activity {
	

	protected static final String EXTRA_RES_NUM = "NUM";
	final String LOG_TAG="Log prog";
	
	private DataListAdapter adapter;
	private int num;
	private int nPlayer;

	ListView NewPlayersListView;
	
	String fName="players";
	String pString="";		
	int savePlayers=0;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// style of Layla
		// Set CoachMark
        if (DatabaseRating.determine_Table()==false){        
        	Log.d("Clear: ", "Table= ");
        	AlertDialog.Builder clearDialog = new AlertDialog.Builder(ControlActivity.this);
        	clearDialog.setTitle(this.getString(R.string.extra_attention_control))
        						.setMessage(this.getString(R.string.extra_insert_control))
        						.setIcon(R.drawable.ic_launcher)
        						.setCancelable(false)
        						.setNegativeButton(this.getString(R.string.extra_yes_control), 
        								new DialogInterface.OnClickListener() {
											
											@Override
											public void onClick(DialogInterface dialog, int id) {
												// TODO Auto-generated method stub
												dialog.cancel();												
											}
										});
        	AlertDialog coach=clearDialog.create();
        	coach.show();
        	}
		styleUtils.onActivitySetTheme(this);
		setContentView(R.layout.about);
		

		setupListViewAdapter();
		setupPlayersInListView();
		setupAddButton();	
		
		this.deleteFile(fName);
		//File file = new File(this.getFilesDir(),fName);
		//---
		setupStartGameButton();
	}

	// Delete element from table if pressed button 
//	public void removeAtomPayOnClickHandler(View v) {
//		DataListAdapterSet itemToDel = (DataListAdapterSet)v.getTag();
//
//		pString="";
//
//		// Delete element on UI Form
//		nPlayer--;
//		Toast.makeText(ControlActivity.this, "ClickActivity 2="+itemToDel.getName(), Toast.LENGTH_LONG).show();
//
//		pString=itemToDel.getName();
//
//		//add Players in file
//		try{
//			OutputStreamWriter fOut=new OutputStreamWriter(openFileOutput(fName, ControlActivity.MODE_APPEND));
//			
//			/*** Start open database ***/
//			DatabaseRating.init(ControlActivity.this);
//			Log.d("deliting: ", "deliting...");
//
//			DatabaseRating.delPlayersData(pString);
//			
//			/*** End Open Database ***/
//			fOut.write(pString);
//			fOut.write('\n');
//			fOut.close();
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//		adapter.remove(itemToDel);
//	}

	// Save element IN table if pressed button
	public void saveAtomPayOnClickHandler(View v) {
		DataListAdapterSet itemToSave = (DataListAdapterSet)v.getTag();

		nPlayer--;
		//Toast.makeText(ControlActivity.this, "ClickActivity 2="+itemToSave.getName(), Toast.LENGTH_LONG).show();
		
		pString=itemToSave.getName();

		try{
			OutputStreamWriter fOut=new OutputStreamWriter(openFileOutput(fName, ControlActivity.MODE_APPEND));
			
			/*** Start open database ***/
			DatabaseRating.init(ControlActivity.this);
			Log.d("Insert: ", "Inserting...");

			DatabaseRating.addPlayersData(new PlayersData(pString,1,"Color","Avatar","Settings"));
			/*** End Open Database ***/
			
			fOut.write(pString);
			fOut.write('\n');
			fOut.close();
			
		} catch (Exception e){
			e.printStackTrace();
		}
		adapter.remove(itemToSave);
	}
	
	/*** Private element save/delete/create ***/
	// Created Insert player form
	private void setupListViewAdapter() {

		adapter = new DataListAdapter(this, R.layout.listitem, new ArrayList<DataListAdapterSet>());
		NewPlayersListView = (ListView)findViewById(R.id.EnterPlayers_PlayersList);

		NewPlayersListView.setAdapter(adapter);
		
		// Title ListView
//		TextView titleView= new TextView(this);
//		titleView.setText("Her Oldetris");
//		NewPlayersListView.addHeaderView(titleView);
		
	}

	private void setupPlayersInListView(){
		
		Intent intent=getIntent();
		nPlayer=intent.getIntExtra("NUM", num);
		//Toast.makeText(ControlActivity.this, "ClickActivity 2="+nPlayer, Toast.LENGTH_LONG).show();

		// Insert Number from Activity1 
		for (int i=0;i<nPlayer;i++){
			adapter.insert(new DataListAdapterSet("", 0), 0);
		}
	}

	// Button, there add new form PlayersAdd on Screen
	private void setupAddButton() {
		findViewById(R.id.EnterPlayers_addPlayers).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nPlayer++;
				//Toast.makeText(ControlActivity.this, "ClickActivity 2="+nPlayer, Toast.LENGTH_LONG).show();

				adapter.insert(new DataListAdapterSet("", 0), 0);

			}
		});
	}

	// Start Game =)
	private void setupStartGameButton() {

		findViewById(R.id.EnterPlayers_StartGame).setOnClickListener(new OnClickListener() {
			final Context context=ControlActivity.this;
			@Override
			public void onClick(View v) {
				
				// Save Data
				Save_Data(fName);
				
				// Start GameActivity
				Intent intent=new Intent(context, FragmentGameActivity.class);
				//Toast.makeText(ControlActivity.this, "Click 1="+savePlayers, Toast.LENGTH_LONG).show();
				intent.putExtra(EXTRA_RES_NUM, savePlayers);

				onStop();
				onDestroy();
				startActivity(intent);
			}
		});
	}

	/*** Public vois ***/
	// Write insert Data in file
	public void Save_Data(String fileName){
		Log.d("Players", "Save List");

		StringBuilder text=new StringBuilder();
		try{
			InputStream fIn=openFileInput(fileName);
			if (fIn!=null){
				// Prepare to read players
				InputStreamReader inputreader=new InputStreamReader(fIn);
				BufferedReader bufferedreader=new BufferedReader(inputreader);

				int c;
				String line=null;

				while ((line=bufferedreader.readLine())!=null) {
					text.append(line);
					text.append('\n');
				}
				Log.e(LOG_TAG, "Text: " + text);
			}
		}catch (Exception e){
		}
	}

	/*** Not released Save, if turning screen***/
//	public void writeListToFile() {
//
//	}
//
//	@Override
//	protected void onSaveInstanceState(Bundle outState){
//		super.onSaveInstanceState(outState);
//		ListAdapter values=NewPlayersListView.getAdapter();
//	}
//
//	@Override
//	protected void onRestoreInstanceState(Bundle savedState){
//		super.onRestoreInstanceState(savedState);
//
//	}
//	
	
}
