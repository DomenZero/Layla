package com.spyralem.layla.vogame;

import java.util.List;

import com.spyralem.layla.model.PlayersData;
import com.spyralem.layla.model.UserRatingData;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RatingActivity extends Activity {
	Button button2;

	protected void onCreate(Bundle savedInstanceState)
	{
        //Intent intent=getIntent();
        //value = intent.getStringExtra("key");
		/**super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        
        int USER_ROWS=4;
        int USER_COLUMNS=4;
        
        TableLayout tableLayout=(TableLayout) findViewById(R.id.tableLayout);
        
        for (int i = 0; i < USER_ROWS; i++) {
			
        	TableRow tableRow=new TableRow(this);
        	tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
        			LayoutParams.WRAP_CONTENT));
        	//tableRow.setBackgroundResource(R.drawable.ic_launcher);
        	
        	for (int j = 0; j < USER_COLUMNS; j++) {
				ImageView imageView=new ImageView(this);
				imageView.setImageResource(R.drawable.ic_launcher);
				
				tableRow.addView(imageView,j);
        		
			}
        	tableLayout.addView(tableRow, i);
        	
		}**/
	
//		DatabaseRating.init(this);
//		super.onCreate(savedInstanceState);
//        setContentView(R.layout.rating);
//		
//		//Input data in table
//		//Log.d("Insert: ", "Inserting...");
//		//DatabaseRating.addUserData(new UserRatingData("Layla",1,"Best"));
//		
////		//Reading All contacts
//		Log.d("Reading: ","Reading All data");
//		List<PlayersData> data=DatabaseRating.getAllPlayersData();
//		
//		for (PlayersData dt:data) {
//			String log=" Id: "+dt.getID()+" User Name: "+dt.getUserName()+" Rating: "+dt.getUserLevel()+" Status: "+dt.getUserColor();
//			Log.d("User Name", log);
//			
////   		 	TextView textView=(TextView)findViewById(R.id.teView);
////   		 	textView.setText(" Id: "+dt.getID()+" User Name: "+dt.getUserName()+" Rating: "+dt.getUserRating()+" Status: "+dt.getUserStatus());
//		}
//		
//		//table
//		 int USER_ROWS=4;
//	     int USER_COLUMNS=4;
//	        
//	     
//	     TableLayout tableLayout=(TableLayout) findViewById(R.id.tableLayout);
//
//	     for (PlayersData dt:data) {
//	     //for (int i = 0; i < USER_ROWS; i++) {
//	    	 TableRow tableRow=new TableRow(this);
//	    	 tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
//	    			 LayoutParams.WRAP_CONTENT));
//	    	 //tableRow.setBackgroundResource(R.drawable.ic_launcher);	    	 
//	        	
//	    	 for (int j = 0; j < 1; j++) {
//	    		 TextView teView=new TextView(this);
//	    		 teView.setTextSize(18);
//	    		 teView.setTextColor(Color.parseColor("#7FFFD4"));
//	    		 teView.setText(" Id: "+dt.getID()+" User Name: "+dt.getUserName()+" Rating: "+dt.getUserLevel()+" Status: "+dt.getUserColor());
//	    		 tableRow.addView(teView);
//	        		
//	    	 }
//	        	
//	    	 tableLayout.addView(tableRow, 0);
//	        	
//	    // }
//		}
//	     
//	}
//		
////		DatabaseRating.init(this);
////		super.onCreate(savedInstanceState);
////        setContentView(R.layout.rating);
////		
////        Log.d("Insert: ", "Inserting...");
////		DatabaseRating.addPlayersData(new PlayersData("LaylaOnlyTest",1,"Color"));
////		
////		Log.d("Reading: ","Reading All data");
////		List<PlayersData> data=DatabaseRating.getAllPlayersData();
////		
////		for (PlayersData dt:data){
////			String log="Id: "+dt.getID()+" ,Name: "+dt.getUserName()+" ,Level: "+dt.getUserLevel()+" ,Color: "+dt.getUserColor();
////			Log.d("Name: ", log);
////		}
//        //Intent intent=getIntent();
////        //value = intent.getStringExtra("key");
////		/**super.onCreate(savedInstanceState);
////        setContentView(R.layout.game);
////        
////        int USER_ROWS=4;
////        int USER_COLUMNS=4;
////        
////        TableLayout tableLayout=(TableLayout) findViewById(R.id.tableLayout);
////        
////        for (int i = 0; i < USER_ROWS; i++) {
////			
////        	TableRow tableRow=new TableRow(this);
////        	tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
////        			LayoutParams.WRAP_CONTENT));
////        	//tableRow.setBackgroundResource(R.drawable.ic_launcher);
////        	
////        	for (int j = 0; j < USER_COLUMNS; j++) {
////				ImageView imageView=new ImageView(this);
////				imageView.setImageResource(R.drawable.ic_launcher);
////				
////				tableRow.addView(imageView,j);
////        		
////			}
////        	tableLayout.addView(tableRow, i);
////        	
////		}**/
//	
		DatabaseRating.init(this);
		super.onCreate(savedInstanceState);
        setContentView(R.layout.rating);
		
		//Input data in table
		//Log.d("Insert: ", "Inserting...");
		//DatabaseRating.addUserData(new UserRatingData("Layla",1,"Best"));
		
//		//Reading All contacts
		Log.d("Reading: ","Reading All data");
		List<UserRatingData> data=DatabaseRating.getAllUserData();
		
		for (UserRatingData dt:data) {
			String log=" Id: "+dt.getID()+" User Name: "+dt.getUserName()+" Rating: "+dt.getUserRating()+" Status: "+dt.getUserStatus();
			Log.d("User Name", log);
			
//   		 	TextView textView=(TextView)findViewById(R.id.teView);
//   		 	textView.setText(" Id: "+dt.getID()+" User Name: "+dt.getUserName()+" Rating: "+dt.getUserRating()+" Status: "+dt.getUserStatus());
		}
		
		//table
		 int USER_ROWS=4;
	     int USER_COLUMNS=4;
	        
	     
	     TableLayout tableLayout=(TableLayout) findViewById(R.id.tableLayout);

	     for (UserRatingData dt:data) {
	     //for (int i = 0; i < USER_ROWS; i++) {
	    	 TableRow tableRow=new TableRow(this);
	    	 tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
	    			 LayoutParams.WRAP_CONTENT));
	    	 //tableRow.setBackgroundResource(R.drawable.ic_launcher);	    	 
	        	
	    	 for (int j = 0; j < 1; j++) {
	    		 TextView teView=new TextView(this);
	    		 teView.setTextSize(18);
	    		 teView.setTextColor(Color.parseColor("#7FFFD4"));
	    		 teView.setText(" Id: "+dt.getID()+" User Name: "+dt.getUserName()+" Rating: "+dt.getUserRating()+" Status: "+dt.getUserStatus());
	    		 tableRow.addView(teView);
	        		
	    	 }
	        	
	    	 tableLayout.addView(tableRow, 0);
	        	
	    // }
		}
	     
	}
}
