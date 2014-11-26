package com.spyralem.layla.vogame;
import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.spyralem.layla.model.UserRatingData;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
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



public class GameActivity extends Activity {

	Button butOver;
	Button butUp;
	
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
	
	int nPlayer=4;
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
        		Toast.makeText(GameActivity.this, "Pos "+position, Toast.LENGTH_LONG).show();
        		//Toast.makeText(getApplicationContext(), ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
        		//Toast.makeText(GameActivity.this, "hkgjhghjgjh "+position, Toast.LENGTH_SHORT).show();
        		//butText.setText("haha"); 
			}
		});

        butUp.setOnClickListener(new Button.OnClickListener(){
        	@Override
			public void onClick(View click){
//        		//butText=(TextView) findViewById(R.id.butText);
        		nPlayer=Integer.parseInt(editT.getText().toString());
        		Toast.makeText(GameActivity.this, "Please. Click 'Enter' "+nPlayer, Toast.LENGTH_LONG).show();

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
        gridGame=(GridView) findViewById(R.id.gridGame);
        gridGame.setAdapter(new PlayerAdapter(this));
        registerForContextMenu(gridGame);
        
        editT=(EditText) findViewById(R.id.edit_nPlayer);
        butUp=(Button) findViewById(R.id.butUp);
        butOver=(Button) findViewById(R.id.butOver);
        
	}
//	
//	static class ViewHolder{
//		public EditText editText;
//	}
//	//Create Adapter
	public class PlayerAdapter extends BaseAdapter
	{
		private Context context;
		private int r, g, b;

		//color
		private int[] colors=new int[]{0x30FF0000, 0x300000FF};
		
		public PlayerAdapter(Context c)
		{
			context=c;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return nPlayer;
		}
		
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		//spinner selected
		private int getIndex(Spinner spinner,String myString){
			int index=0;
			
			for(int i=0;i<spinner.getCount();i++){
				if(spinner.getItemAtPosition(i).equals(myString)){
					index=i;
				}
			}
			return index;
		}
		
		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			//View grView;
			
			//little
			View grView=convertView;

			
			if (convertView==null) {
				
				grView=new View(context);
				grView=inflater.inflate(R.layout.gameitem, null);
				
				TextView textView=(TextView) grView.findViewById(R.id.itemText);
				textView.setText("Player " +position );
			    textView.setTextColor(Color.parseColor("#7000D4"));
				
		    	//EditText editText=(EditText) grView.findViewById(R.id.itemEdit);
		    	
		    	//Автозаполнение, подсказка ввода
				
				ArrayAdapter<String> editAdapter=new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, data);
				
				
				editText=(AutoCompleteTextView) grView.findViewById(R.id.itemEdit);
				editText.setThreshold(4);
				editText.setAdapter(editAdapter);
				
				editText.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> editAd, View v, int position, long id){

						
						Toast.makeText(context, (CharSequence)editAd.getItemAtPosition(position), Toast.LENGTH_LONG).show();
					}			
				});
			    
			    /*** Spinner create ***/
				ArrayList<String> allLevel=new ArrayList<String>();
		    	//ArrayAdapter<String> levelAdapter=new ArrayList<String>();
		    	for (int i = 0; i < data.length; i++) {
		    		allLevel.add(data[i]);        		
		    	}
		    	levelSpinner=(Spinner) grView.findViewById(R.id.level);
		    	ArrayAdapter<String> levelAdapter=new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,allLevel);
		    	levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    	
		    	levelSpinner.setAdapter(levelAdapter);
		    	//e
		    	levelSpinner.setSelection(getIndex(levelSpinner,"Level 1"));

		    	levelSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    		
		    		@Override
		    		public void onItemSelected(AdapterView<?> adapter, View v, int position, long id){
		    			String item=adapter.getItemAtPosition(position).toString();
		    			
		    			if (item=="Level 10"){
		    				Toast.makeText(GameActivity.this, " "+item+" "+editText.getText().toString(), Toast.LENGTH_SHORT).show();	
		            		DatabaseRating.init(GameActivity.this);
		            		Log.d("Insert: ", "Inserting...");
		            		
		            		//DatabaseRating.addUserData(new UserRatingData("LaylaTest",1,"Best"));
		            		DatabaseRating.addUserData(new UserRatingData(editText.getText().toString(),1,"In Level"));
		    				
		    			}
		    		}
		    		@Override
		    		public void onNothingSelected(AdapterView<?> adapter){
		    			
		    		}
				});

		    	

				//color
				Random rnd=new Random();
				r=rnd.nextInt(255+position);
				g=rnd.nextInt(255+position);
				b=rnd.nextInt(255+position);
				grView.setBackgroundColor(Color.rgb(r, g, b));
				
//				levelSpinner.setOnClickListener(new Button.OnClickListener(){
//		        	@Override
//		        	public void onClick(View click){
//		        		if (levelSpinner.getSelectedItemPosition()==8)
//		        			Toast.makeText(GameActivity.this, " "+position, Toast.LENGTH_SHORT).show();
//		        	}
//		        });
//				int col_i=1;
//				//position/2==1 - row; position%2==1 - col
//				if(position==0){
//					col_i=0;
//				}
//				if(position==1){
//					col_i=1;
//				}
//				int colorPos=position%colors.length;
//				grView.setBackgroundColor(colors[col_i]);
				
//				editText.setOnClickListener(new OnClickListener() {
//					
//			        //find_and_mod()
//
//					
//					@Override
//					public void onClick(View v) {
//						// TODO Auto-generated method stub
////						CharSequence constraint=editText.getText();
////						editAdapter.getFilter().filter(constraint);
////						editText.showDropDown();
//						Toast.makeText(GameActivity.this, " "+position, Toast.LENGTH_LONG).show();
//												
//					}
//				});
//				

				textView.setOnClickListener(new OnClickListener() {
//					
					@Override
					public void onClick(View v) {
//						// TODO Auto-generated method stub
						//grView.setC
						Toast.makeText(GameActivity.this, " "+position, Toast.LENGTH_SHORT).show();//textView.setBackgroundColor(Color.parseColor("#7000D4"));
					}
				});
				
				
			}else{
				
				grView=(View) convertView;
			
			}
			
			return grView;
		}
		
		
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
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