package com.spyralem.layla.vogame;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import java.util.ArrayList;

import com.spyralem.layla.managelist.DataListAdapter;
import com.spyralem.layla.managelist.DataListAdapterSet;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class AboutActivity extends Activity {

		private DataListAdapter adapter;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.about);
			
			setupListViewAdapter();
			
			setupAddPaymentButton();
		}

		public void removeAtomPayOnClickHandler(View v) {
			DataListAdapterSet itemToRemove = (DataListAdapterSet)v.getTag();
			adapter.remove(itemToRemove);
		}

		private void setupListViewAdapter() {
			adapter = new DataListAdapter(AboutActivity.this, R.layout.listitem, new ArrayList<DataListAdapterSet>());
			ListView atomPaysListView = (ListView)findViewById(R.id.EnterPays_atomPaysList);
			atomPaysListView.setAdapter(adapter);
		}
		
		private void setupAddPaymentButton() {
			findViewById(R.id.EnterPays_addAtomPayment).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					//Inser Num from Activity1 
					for (int i=0;i<4;i++){
						adapter.insert(new DataListAdapterSet("", 0), 0);
					}
					
				}
			});
		}
}
