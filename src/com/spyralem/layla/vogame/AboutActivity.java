package com.spyralem.layla.vogame;
import android.app.Activity;
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

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import java.util.ArrayList;

import com.spyralem.layla.managelist.DataListAdapter;
import com.spyralem.layla.managelist.DataListAdapterSet;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;


public class AboutActivity extends Activity {

		private DataListAdapter adapter;
		private int num;
		private int nPlayer;
		ListView NewPlayersListView;
		final String LOG_TAG="Log prog";
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.about);
			
			
			setupListViewAdapter();
			setupPlayersInListView();
		//	for (int i=0;i<4;i++){
			//	adapter.insert(new DataListAdapterSet("", 0), 0);
			//}
			
			
			setupAddPaymentButton();
			
			//---
			setupStartGameButton();
		}

		public void removeAtomPayOnClickHandler(View v) {
			DataListAdapterSet itemToRemove = (DataListAdapterSet)v.getTag();
			
			//Act III —табилизаци€ изменени€ параметра nPlayer на форме
			nPlayer--;
			Toast.makeText(AboutActivity.this, "ClickActivity 2="+itemToRemove, Toast.LENGTH_LONG).show();
			
			adapter.remove(itemToRemove);
		}

		private void setupListViewAdapter() {

	        
			//adapter = new DataListAdapter(AboutActivity.this, R.layout.listitem, new ArrayList<DataListAdapterSet>());
	        adapter = new DataListAdapter(this, R.layout.listitem, new ArrayList<DataListAdapterSet>());
	        NewPlayersListView = (ListView)findViewById(R.id.EnterPays_atomPaysList);
			
	        NewPlayersListView.setAdapter(adapter);
			

			
		}
		
		private void setupPlayersInListView(){
			//Act II ѕолучение параметра nPlayer на форму
	        Intent intent=getIntent();
	        nPlayer=intent.getIntExtra("NUM", num);
	        Toast.makeText(AboutActivity.this, "ClickActivity 2="+nPlayer, Toast.LENGTH_LONG).show();

			//Inser Num from Activity1 
			for (int i=0;i<nPlayer;i++){
				adapter.insert(new DataListAdapterSet("", 0), 0);
			}
			

			
		}
		
		private void setupAddPaymentButton() {
			findViewById(R.id.EnterPays_addAtomPayment).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					//Act III —табилизаци€ изменени€ параметра nPlayer на форме
					nPlayer++;
					Toast.makeText(AboutActivity.this, "ClickActivity 2="+nPlayer, Toast.LENGTH_LONG).show();
					
					adapter.insert(new DataListAdapterSet("", 0), 0);

				}
			});
		}

		//Test !!!
		private void setupStartGameButton() {
			findViewById(R.id.EnterPays_StartGame).setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					DataListAdapterSet itemToRemove = (DataListAdapterSet)v.getTag();
					//DataListAdapterSet itemToRemove = (DataListAdapterSet)v.getTag();
					Log.e(LOG_TAG, "Kill Bill: " + itemToRemove.getName().toString());
			}
		});
		}
		
		//write in file
		public void Save_Data(){
			Log.d("Save", "Save List");
			
			try{
				writeListToFile();
			}catch (Exception e){
				Log.d("Save", "Save process have any error");
			}
		}
		
		public void writeListToFile() {
			
		}
		
//		@Override
//		protected void onSaveInstanceState(Bundle outState){
//			super.onSaveInstanceState(outState);
//			ListAdapter values=NewPlayersListView.getAdapter();
////			outState.
//		}
		
		/*** Action in ListView***/
		
//		
//		@Override
//		protected void onRestoreInstanceState(Bundle savedState){
//			super.onRestoreInstanceState(savedState);
//
//		}
}
