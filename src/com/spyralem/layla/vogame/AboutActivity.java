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

import android.content.Context;
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

import com.spyralem.layla.managelist.DataListAdapter;
import com.spyralem.layla.managelist.DataListAdapterSet;
import com.spyralem.layla.vogame.FileManager;

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
		String fName="players";
		String pString="";
		protected static final String EXTRA_RES_NUM = "NUM";
		
		int savePlayers=0;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.about);
			
			//
			
			
			setupListViewAdapter();
			setupPlayersInListView();
		//	for (int i=0;i<4;i++){
			//	adapter.insert(new DataListAdapterSet("", 0), 0);
			//}
			
			
			setupAddButton();
//			if (nPlayer==0){
//				setupStartGameButton();
//			}
			
			this.deleteFile(fName);
			File file = new File(this.getFilesDir(),fName);
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

		public void saveAtomPayOnClickHandler(View v) {
			DataListAdapterSet itemToSave = (DataListAdapterSet)v.getTag();

			
			//Act III —табилизаци€ изменени€ параметра nPlayer на форме
			nPlayer--;
			Toast.makeText(AboutActivity.this, "ClickActivity 2="+itemToSave.getName(), Toast.LENGTH_LONG).show();
			//itemToRemove.getName();
			//pString=pString+" "+itemToSave.getName();
			
			pString=itemToSave.getName();
			
			//add Players in file
			//if (nPlayer==0){
			try{
				//OutputStreamWriter fOut=(fName, AboutActivity.MODE_PRIVATE);
				OutputStreamWriter fOut=new OutputStreamWriter(openFileOutput(fName, AboutActivity.MODE_APPEND));
				//fOut.write(pString.getBytes());
				fOut.write(pString);
				fOut.write('\n');
				fOut.close();
			} catch (Exception e){
				e.printStackTrace();
			}
			//}
			//
			adapter.remove(itemToSave);
		}
		
		private void setupListViewAdapter() {

	        
			//adapter = new DataListAdapter(AboutActivity.this, R.layout.listitem, new ArrayList<DataListAdapterSet>());
	        adapter = new DataListAdapter(this, R.layout.listitem, new ArrayList<DataListAdapterSet>());
	        NewPlayersListView = (ListView)findViewById(R.id.EnterPlayers_PlayersList);
			
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
		
		private void setupAddButton() {
			findViewById(R.id.EnterPlayers_addPlayers).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					//Act III —табилизаци€ изменени€ параметра nPlayer на форме
					nPlayer++;
					Toast.makeText(AboutActivity.this, "ClickActivity 2="+nPlayer, Toast.LENGTH_LONG).show();
					
					
					adapter.insert(new DataListAdapterSet("", 0), 0);

				

				}
			});
		}
		
		private void setupStartGameButton() {
			
			findViewById(R.id.EnterPlayers_StartGame).setOnClickListener(new OnClickListener() {
				final Context context=AboutActivity.this;
				@Override
				public void onClick(View v) {
					//Read_Data
					Save_Data(fName);
					//Start GameActivity
	    			Intent intent=new Intent(context, GameActivity.class);
	    			Toast.makeText(AboutActivity.this, "Click 1="+savePlayers, Toast.LENGTH_LONG).show();
	    			intent.putExtra(EXTRA_RES_NUM, savePlayers);
	    			
	    			onStop();
	    			onDestroy();
	    			startActivity(intent);

					
//					//Act IV запуск стартовой формы
//					Toast.makeText(AboutActivity.this, "ClickActivity 2="+nPlayer, Toast.LENGTH_LONG).show();
//	    			onStop();
//	    			onDestroy();
//	    			Intent intent=new Intent(context, AboutActivity.class);
//	    			startActivity(intent);

				}
			});
		}
		
		//write in file
		public void Save_Data(String fileName){
			Log.d("Players", "Save List");
			
			
			StringBuilder text=new StringBuilder();
			try{
				InputStream fIn=openFileInput(fileName);
				if (fIn!=null){
					//prepare to read players
					InputStreamReader inputreader=new InputStreamReader(fIn);
					BufferedReader bufferedreader=new BufferedReader(inputreader);
					
					int c;
					String line=null;
					
					while ((line=bufferedreader.readLine())!=null) {
						//temp = temp+Character.toString((char)c);
						text.append(line);
						text.append('\n');
						savePlayers++;
					}
					Log.e(LOG_TAG, "Text: " + text);
					}
			}catch (Exception e){
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
