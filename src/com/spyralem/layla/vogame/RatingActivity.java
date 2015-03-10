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

/*
 * @author Merkulov Maksim (DomenZero) 
 * <wardomenmax@gmail.com>
 * 
 * Trivial printed Table 
 */

public class RatingActivity extends Activity {
	Button button2;

	protected void onCreate(Bundle savedInstanceState)
	{
       
		DatabaseRating.init(this);
		super.onCreate(savedInstanceState);
        setContentView(R.layout.rating);
		
        //Reading All contacts
		Log.d("Reading: ","Reading All data");
		List<UserRatingData> data=DatabaseRating.getAllUserData();
		
		for (UserRatingData dt:data) {
			String log=" Id: "+dt.getID()+" User Name: "+dt.getUserName()+" Rating: "+dt.getUserRating()+" Status: "+dt.getUserStatus();
			Log.d("User Name", log);
		}
		
		 // Table
		 int USER_ROWS=4;
	     int USER_COLUMNS=4;	        
	     
	     TableLayout tableLayout=(TableLayout) findViewById(R.id.tableLayout);

	     for (UserRatingData dt:data) {
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
	        
		}
	     
	}
}
