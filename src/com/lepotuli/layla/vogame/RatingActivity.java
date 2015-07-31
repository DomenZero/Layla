package com.lepotuli.layla.vogame;

import java.util.Iterator;
import java.util.List;

import com.lepotuli.layla.model.PlayersData;
import com.lepotuli.layla.model.UserRatingData;
import com.lepotuli.layla.vogame.R;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/*
 * @author Merkulov Maksim (DomenZero) 
 * <wardomenmax@gmail.com>
 * lepotuli.com
 * 
 * Trivial printed Table 
 */

public class RatingActivity extends Activity {
	Button button2;

	protected void onCreate(Bundle savedInstanceState) {

		DatabaseRating.init(this);
		super.onCreate(savedInstanceState);
		styleUtils.onActivitySetTheme(this);

		setContentView(R.layout.rating);
		// Set screen orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		TableLayout contentTable = (TableLayout) findViewById(R.id.rating_tableLayout);
		TableRow.LayoutParams rowParams = new TableRow.LayoutParams();
		// Wrap-up the content of the row
		rowParams.height = LayoutParams.WRAP_CONTENT;
		rowParams.width = LayoutParams.WRAP_CONTENT;
		// The simplified version of the table of the picture above will have
		// two columns
		// FIRST COLUMN
		TableRow.LayoutParams col1Params = new TableRow.LayoutParams();
		// SECOND COLUMN
		TableRow.LayoutParams col2Params = new TableRow.LayoutParams();

		// Wrap-up the content of the row
		col1Params.height = LayoutParams.WRAP_CONTENT;
		col1Params.width = LayoutParams.WRAP_CONTENT;

		// 2 Wrap-up the content of the row
		col2Params.height = LayoutParams.WRAP_CONTENT;
		col2Params.width = LayoutParams.WRAP_CONTENT;

		// Set the gravity to center the gravity of the column
		col1Params.gravity = Gravity.CENTER;

		// 2 Set the gravity to center the gravity of the column
		col2Params.gravity = Gravity.CENTER;

		TextView col1 = new TextView(this);
		TextView col2 = new TextView(this);
		TableRow row = new TableRow(this);

		// col1.setText(contentName);
		col1.setText(this.getString(R.string.name));
		row.addView(col1, col1Params);

		// col2.setText(contentRating);
		col2.setText(this.getString(R.string.rating));
		row.addView(col2, col2Params);
		contentTable.addView(row, rowParams);

		// -------------------------
		TableLayout tableLayout = (TableLayout) findViewById(R.id.rating_tableLayout);

		// Temp Information
		tableLayout = AddRowInTable(tableLayout);// , "Name", "Rating");
	}

	// function add row in Rating Table ]
	private TableLayout AddRowInTable(TableLayout tableLayout) {// , String
																// contentName,
																// String
																// contentRating){

		// Reading All contacts
		Log.d("Reading: ", "Reading All data");
		List<UserRatingData> data = DatabaseRating.getAllUserData();

		for (UserRatingData dt : data) {
			String log = " Id: " + dt.getID() + " User Name: "
					+ dt.getUserName() + " Rating: " + dt.getUserRating()
					+ " Status: " + dt.getUserStatus();
			Log.d("User Name", log);
		}

		for (UserRatingData dt : data) {
			TableRow row = new TableRow(this);

			// tableRow.setLayoutParams(new
			// LayoutParams(LayoutParams.MATCH_PARENT,
			// LayoutParams.WRAP_CONTENT));
			// //tableRow.setBackgroundResource(R.drawable.ic_launcher);
			// -----------------------------------------------
			TableRow.LayoutParams rowParams = new TableRow.LayoutParams();
			// Wrap-up the content of the row
			rowParams.height = LayoutParams.WRAP_CONTENT;
			rowParams.width = LayoutParams.WRAP_CONTENT;
			
			// The simplified version of the table of the picture above will
			// have two columns
			// FIRST COLUMN
			TableRow.LayoutParams col1Params = new TableRow.LayoutParams();
			// SECOND COLUMN
			TableRow.LayoutParams col2Params = new TableRow.LayoutParams();

			// Wrap-up the content of the row
			col1Params.height = LayoutParams.WRAP_CONTENT;
			col1Params.width = LayoutParams.WRAP_CONTENT;

			// 2 Wrap-up the content of the row
			col2Params.height = LayoutParams.WRAP_CONTENT;
			col2Params.width = LayoutParams.WRAP_CONTENT;

			// Set the gravity to center the gravity of the column
			col1Params.gravity = Gravity.CENTER;

			// 2 Set the gravity to center the gravity of the column
			col2Params.gravity = Gravity.CENTER;

			// ---------------------------------------

			for (int j = 0; j < 1; j++) {
				TextView col1 = new TextView(this);
				TextView col2 = new TextView(this);

				// col1.setText(contentName);
				col1.setText(dt.getUserName());
				row.addView(col1, col1Params);
				col1.setBackgroundColor(R.style.TableStandartTheme);

				
				// col2.setText(contentRating);
				col2.setText(String.valueOf(dt.getUserRating()));
				row.addView(col2, col2Params);
				

				// TextView teView=new TextView(this);
				// teView.setTextSize(18);
				// teView.setTextColor(Color.parseColor("#7FFFD4"));
				// teView.setText(" Id: "+dt.getID()+" User Name: "+dt.getUserName()+" Rating: "+dt.getUserRating()+" Status: "+dt.getUserStatus());

				// tableRow.addView(teView);
			}
			tableLayout.addView(row, rowParams);

			// tableLayout.addView(tableRow, 0);

		}

		return tableLayout;
	}

}
