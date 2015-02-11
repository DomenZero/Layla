package com.spyralem.layla.vogame;

import com.spyralem.layla.vogame.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{
	Button button1;
	Button button0;
	Button button3;
	Button button2;
	Button button4;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        addListenerOnButton();

        //button1=(Button) findViewById(R.id.button1);
        // Set main.xml as user interface layout

        //@SuppressWarnings("unused")
		//OnClickListener button1=new OnClickListener() {
			
			//@Override
			//public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			//}
		//};
	}
    public void addListenerOnButton(){
    	final Context context=this;

    	
    	button4=(Button) findViewById(R.id.button4);
    	button4.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View arg0){
//    			Intent intent=new Intent(context, GameActivity.class);
//    			startActivity(intent);
    			Intent intent=new Intent(context, FragmentGameActivity.class);
    			startActivity(intent);
    			
    		}
    	});
    	
    	
      	button3 = (Button) findViewById(R.id.button3);
    	button3.setOnClickListener(new OnClickListener() {
    		DatabaseRating db;
    		@Override
    		public void onClick(View arg0){
    			
    			Intent intent=new Intent(context, NumPlayerActivity.class);
    			startActivity(intent);
    		//	db=new DatabaseRating(this, "dbLayla", null, 1);
    		//	SQLiteDatabase sdb;
    		//	sdb=db.getReadableDatabase();
    		}
    	});
    	
    	button2 = (Button) findViewById(R.id.button2);
    	button2.setOnClickListener(new OnClickListener() {
    		DatabaseRating db;
    		@Override
    		public void onClick(View arg0){
    			Intent intent=new Intent(context, RatingActivity.class);
    			startActivity(intent);
    		}
    	});
    	
    	button1=(Button) findViewById(R.id.button1);
    	button1.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View arg0){
    			Intent intent=new Intent(context, AboutActivity.class);
    			startActivity(intent);
    		}
    	});
    	
    	button0 = (Button) findViewById(R.id.button0);
    	button0.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View arg0){
    			Intent intent=new Intent(Intent.ACTION_MAIN);
    			intent.addCategory(Intent.CATEGORY_HOME);
    			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    			
    			startActivity(intent);
    			finish();
    			//onDestroy();
    		}
    	});
    }
    public void onClickAbout(View v)
    {
    	onStop();
    	onDestroy();
    	Intent myintent=new Intent(MainActivity.this, AboutActivity.class);
		//myintent.putExtra("key", value);
    	
    	MainActivity.this.startActivity(myintent);
    	
    }
    
    public void onClickE(View v)
    {
    
    }
    
    
}