package com.spyralem.layla.vogame;
import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.spyralem.layla.model.UserRatingData;
import com.spyralem.layla.vogame.GridAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
//import android.content.DialogInterface.OnClickListener;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.IntToString;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class GameActivity extends Activity {

	Button butOver;
	Button butUp;
	//fur Test
	protected static final String EXTRA_RES_ID = "POS";
	
	private ArrayList<Integer> mThumbIdsFlowers = new ArrayList<Integer>(
			Arrays.asList(101,100,110,111));
	
	String[] data={"Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6", "Level 7", "Level 8", "Level 9", "Level 10", "Level 0"};
	String[] playerItems={"Fel-x","Nigarin", "Veta", "Max"};
	GridView gridGame;
	Spinner levelSpinner;
	EditText editT;
	//ArrayAdapter<String> adapter;
	ArrayAdapter<String> adapter2;

	AutoCompleteTextView editText;
	
	int level=0;
	String text;
	int spinnum[]=new int [11];
	String[] sspinnum={};
	
	int num;
	//int nPlayer=4;
	TextView butText;


	AlertDialog.Builder alertDialog;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.game);    
        /***WorkingSave***/
        



        setupViews();

        

  

    	
//        levelSpinner=(Spinner) findViewById(R.id.level);
//        levelSpinner.setAdapter(new SpinnerAdapter(this));
//        
        //find_and_mod();
        

        //--------------
        
        //ArrayAdapter adapter=new ArrayAdapter(this, R.layout.gameitem, R.id.itemText, data);

//        butText=(TextView) findViewById(R.id.butText);        

        
		//Spinner
//		Spinner spinner=(Spinner) findViewById(R.id.level);
//		ArrayAdapter<String> adapterSp=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
//		adapterSp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinner.setAdapter(adapterSp);
		//end Spinner
		
        gridGame.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        		//editT=(EditText) findViewById(R.id.edit_nPlayer);
        		//Toast.makeText(GameActivity.this, "Pos "+position, Toast.LENGTH_LONG).show();
        		//Toast.makeText(getApplicationContext(), ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
        		Toast.makeText(GameActivity.this, "hkgjhghjgjh "+position, Toast.LENGTH_SHORT).show();
        		//butText.setText("haha"); 
			}
		});

        butUp.setOnClickListener(new Button.OnClickListener(){
        	@Override
			public void onClick(View click){
//        		//butText=(TextView) findViewById(R.id.butText);
        		//Act II
                Intent intent=getIntent();
                int nPlayer=intent.getIntExtra("NUM", num);
               
                Toast.makeText(GameActivity.this, "ClickActivity 2="+nPlayer, Toast.LENGTH_LONG).show();
                //--
        		//nPlayer=Integer.parseInt(editT.getText().toString());
        		//Toast.makeText(GameActivity.this, "Please. Click 'Enter' "+nPlayer, Toast.LENGTH_LONG).show();

        		//Toast.makeText(GameActivity.this, nPlayer, Toast.LENGTH_LONG).show();
//        		Toast.makeText(GameActivity.this, "onSaveInstanceState\n "+"save ="+editT.getText().toString(), Toast.LENGTH_LONG).show();
        	}
        });
        
        butOver.setOnClickListener(new Button.OnClickListener(){
        	@Override
        	public void onClick(View click){
        		DatabaseRating.init(GameActivity.this);
        		Log.d("Insert: ", "Inserting...");
        		
        		//DatabaseRating.addUserData(new UserRatingData("LaylaTest",1,"Best"));
        		DatabaseRating.addUserData(new UserRatingData(editText.getText().toString(),1,"Best"));
        		
        		//Input data in table
        		//Log.d("Insert: ", "Inserting...");
        		//DatabaseRating.addUserData(new UserRatingData("Layla",1,"Best"));
        	}
        });
        
	}
	

	
	/*** Spinnera Listener ***/
//	
//	public class SpinnerAdapter extends BaseAdapter
//	{
//
//		private Context context;
//		
//		public SpinnerAdapter(Context c)
//		{
//			context=c;
//		}
//
//		@Override
//		public int getCount() {
//			// TODO Auto-generated method stub
//			return nPlayer;
//		}
//
//		@Override
//		public Object getItem(int position) {
//			// TODO Auto-generated method stub
//			return position;
//		}
//
//		@Override
//		public long getItemId(int position) {
//			// TODO Auto-generated method stub
//			return position;
//		}
//		
//		private void find_and_mod(){
//	    	
//	    	ArrayList<String> allLevel=new ArrayList<String>();
//	    	//ArrayAdapter<String> levelAdapter=new ArrayList<String>();
//	    	for (int i = 0; i < data.length; i++) {
//	    		allLevel.add(data[i]);        		
//	    	}
//	    	ArrayAdapter<String> levelAdapter=new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,allLevel);
//	    	levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//	    	levelSpinner.setAdapter(levelAdapter);
//	    }
//
//		@Override
//		public View getView(int arg0, View arg1, ViewGroup arg2) {
//			// TODO Auto-generated method stub
//			return null;
//		}	
//	}
	

//	public void onItemSelected(AdapterView<?> levelAdapterView, View v, int position, long id){
//		Toast.makeText(GameActivity.this, "level "+levelAdapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
//	}
//	
//	public void onNothingSelected(AdapterView<?>argo) {
//		
//	}
	


//	/*** Game Over Dialog menu ***/
//	public void onGameOverDialogMenu(){
//
//		String title="This is End Result?";
//		String message="Game over there you are select 'OK'";
//		String butOK="OK";
//		String butNext="Next";
//		
//		alertDialog=new AlertDialog.Builder(GameActivity.this);
//		alertDialog.setTitle(title);
//		alertDialog.setMessage(message);
//		alertDialog.setPositiveButton(butOK, new OnClickListener() {
//			public void onClick(DialogInterface dialog, int position) {
//				// TODO Auto-generated method stub
//				Toast.makeText(GameActivity.this, "Player Result Save. New Game?", Toast.LENGTH_LONG).show();
//			}
//		});
//		
//		alertDialog.setNegativeButton(butNext, new OnClickListener() {
//			public void onClick(DialogInterface dialog, int position) {
//				// TODO Auto-generated method stub
//				Toast.makeText(GameActivity.this, "Good. Select next Winner", Toast.LENGTH_LONG).show();
//			}
//		});
//		
//		alertDialog.setCancelable(true);
//		alertDialog.setCancelListener(new OnCancelListener(){
//			public void onCancel(DialogInterface dialog) {
//				Toast.makeText(GameActivity.this, "Oh( Good. Select next Winner", Toast.LENGTH_LONG).show();
//			}
//		});
//		
//	}
//	public boolean onCreateOptionsMenu1(Menu menu){
//		getMenuInflater().inflate(R.layout.game, null);
//		return true;
//	}
	
//	/*** Void Game Over menu ***/
//	public void onGameOverMenu(ContextMenu menu, View v,
//			ContextMenuInfo menuInfo) {
//		AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;
//		menu.setHeaderIcon(android.R.drawable.btn_star_big_on);
//		menu.setHeaderTitle("Position "+info.position);
//		
//		String[] menuItems=playerItems;
//		
//		for (int i = 0; i < menuItems.length; i++) {
//			menu.add(Menu.NONE,i,i,menuItems[i]);
//		}
//	}
	/*** Punkt 1. Menu ***/
	// Create Options Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.top_menu, menu);
		return true;
	}
	
	// Process clicks on Options Menu items
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings2:
			Toast.makeText(getApplicationContext(), "you've been helped",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menu_rating2:
			Toast.makeText(getApplicationContext(), "you've been helped more",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menu_play2:
			return true;
		default:
			return false;
		}
	}
	
	/*** Void Context menu position ***/
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;
		menu.setHeaderIcon(android.R.drawable.btn_star_big_on);
		menu.setHeaderTitle("Position "+info.position);
		
		String[] menuItems=playerItems;
		
		for (int i = 0; i < menuItems.length; i++) {
			menu.add(Menu.NONE,i,i,menuItems[i]);
		}
	}
	

	//menu position selected
	@Override
	public boolean onContextItemSelected(MenuItem item){
		AdapterView.AdapterContextMenuInfo info=
				(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		int menuItemIndex=item.getItemId();
		String[] menuItems=playerItems;
		String CmdName=menuItems[menuItemIndex];

		//
//		View grView;
//		grView=new View(GameActivity.this);
//		
//		editText=(AutoCompleteTextView) grView.findViewById(R.id.itemEdit);
		
		//check Event Command
		if (menuItems[menuItemIndex].equals(CmdName)) {		
			
			editText=(AutoCompleteTextView) findViewById(R.id.itemEdit);
			editText.setText(menuItems[menuItemIndex]);
			Toast.makeText(GameActivity.this, "Click 1=", Toast.LENGTH_LONG).show();
		}  
		return true;
		
	}
	
	/***Setup Views ***/

	public void setupViews() {
        //Act II
        Intent intent=getIntent();
        int nPlayer=intent.getIntExtra("NUM", num);
       
        Toast.makeText(GameActivity.this, "ClickActivity 2="+nPlayer, Toast.LENGTH_LONG).show();
        //--
        
        gridGame=(GridView) findViewById(R.id.gridGame);
        gridGame.setAdapter(new GridAdapter(this,mThumbIdsFlowers,nPlayer));
        registerForContextMenu(gridGame);
        
     // Set an setOnItemClickListener on the GridView
     		gridGame.setOnItemClickListener(new OnItemClickListener() {
     			public void onItemClick(AdapterView<?> parent, View v,
     					int position, long id) {
     				
     				//Create an Intent to start the ImageViewActivity
     				Intent intent = new Intent(GameActivity.this,
     						AdapterViewActivity.class);
     				
     				// Add the ID of the thumbnail to display as an Intent Extra
     				intent.putExtra(EXTRA_RES_ID, (int) id);
     				
     				// Start the ImageViewActivity
     				startActivity(intent);
     			}
     		});
        
        
        editT=(EditText) findViewById(R.id.edit_nPlayer);
        butUp=(Button) findViewById(R.id.butUp);
        butOver=(Button) findViewById(R.id.butOver);
        
	}
	
	private void settingsGrid() {
		gridGame.setBackgroundColor(Color.parseColor("#7000D4"));
	}

	public void onClick(View v){
//		//butText=(TextView) findViewById(R.id.butText);
//		//butText.setText(editT.getText().toString());
		Toast.makeText(GameActivity.this, "onSaveInstanceState\n "+"save ="+editT.getText().toString(), Toast.LENGTH_LONG).show();
	}
		
	private void howNumEdit(){
		
	}
	@Override
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
//		String sSaved=editT.getText().toString();
//		outState.putString("saved_state", sSaved);
//		
		
		
//		String sSaved=editT.getText().toString();
//		outState.putString("saved_state", sSaved);
//		Toast.makeText(GameActivity.this, "onSaveInstanceState\n "+"save ="+sSaved, Toast.LENGTH_LONG).show();
//		
		//EditText editText=(EditText) findViewById(R.id.editText1);
		
		//CharSequence text=editText.getText();
		
		//outState.putCharSequence("text", text);
		//outState.putString("text", text);
		//outState.putSerializable(key, value)("text", editText);
		//outState.putInt("Level", level);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedState){
		super.onRestoreInstanceState(savedState);
//		String sSaved=savedState.getString("saved_state");
//		
//		if (sSaved==null) {
//			Toast.makeText(GameActivity.this, " no onRestoreInstance saved", Toast.LENGTH_LONG).show();
//			
//		}else {
//			Toast.makeText(GameActivity.this, "onRestoreInstance "+sSaved, Toast.LENGTH_LONG).show();
//			editT.setText(sSaved);
//		}
		
		//text=savedState.getString("text");
		//final EditText editText=(EditText) findViewById(R.id.editText1);
		//CharSequence text=savedState.getCharSequence("text");
		//editText.setText(text);
		
	}
}