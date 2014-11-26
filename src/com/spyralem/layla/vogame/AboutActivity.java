package com.spyralem.layla.vogame;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class AboutActivity extends Activity {
    //public String value;
	
	Button button1;
	
	protected void onCreate(Bundle savedInstanceState)
	{
        //Intent intent=getIntent();
        //value = intent.getStringExtra("key");
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
		
        
        
		
	}
}
