package com.spyralem.layla.vogame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.spyralem.layla.model.UserRatingData;

//
//static class ViewHolder{
//	public EditText editText;
//}
////Create Adapter
public class GridAdapter extends BaseAdapter
{
	private Context context;
	private int r, g, b;
	
	private List<Integer> mThumbIds;

	//color
	private int[] colors=new int[]{0x30FF0000, 0x300000FF};
	
	public GridAdapter(Context c, List<Integer> ids)
	{
		context=c;
		this.mThumbIds = ids;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mThumbIds.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return mThumbIds.get(position);
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
		//View grView=(View) convertView;
		
		if (convertView==null) {
		//if (grView==null) {
			
			grView=new View(context);
			grView=inflater.inflate(R.layout.gameitem, null);
			
			TextView textView=(TextView) grView.findViewById(R.id.itemText);
			textView.setText("Player " +position );
		    textView.setTextColor(Color.parseColor("#7000D4"));
			
	    	//EditText editText=(EditText) grView.findViewById(R.id.itemEdit);
	    	
	    	//Автозаполнение, подсказка ввода
//			
//			ArrayAdapter<String> editAdapter=new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, data);
//			
//			
//			editText=(AutoCompleteTextView) grView.findViewById(R.id.itemEdit);
//			editText.setThreshold(4);
//			editText.setAdapter(editAdapter);
//			
//			editText.setOnItemClickListener(new OnItemClickListener() {
//				public void onItemClick(AdapterView<?> editAd, View v, int position, long id){
//
//					
//					Toast.makeText(context, (CharSequence)editAd.getItemAtPosition(position), Toast.LENGTH_LONG).show();
//				}			
//			});
//		    
//		    /*** Spinner create ***/
//			ArrayList<String> allLevel=new ArrayList<String>();
//	    	//ArrayAdapter<String> levelAdapter=new ArrayList<String>();
//	    	for (int i = 0; i < data.length; i++) {
//	    		allLevel.add(data[i]);        		
//	    	}
//	    	levelSpinner=(Spinner) grView.findViewById(R.id.level);
//	    	ArrayAdapter<String> levelAdapter=new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,allLevel);
//	    	levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//	    	
//	    	levelSpinner.setAdapter(levelAdapter);
//	    	//e
//	    	levelSpinner.setSelection(getIndex(levelSpinner,"Level 1"));
//	    	levelSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
//	    		
//	    		@Override
//	    		public void onItemSelected(AdapterView<?> adapter, View v, int position, long id){
//	    			String item=adapter.getItemAtPosition(position).toString();
//	    			
//	    			if (item=="Level 10"){
//	    				Toast.makeText(GameActivity.this, " "+item+" "+editText.getText().toString(), Toast.LENGTH_SHORT).show();	
//	            		DatabaseRating.init(GameActivity.this);
//	            		Log.d("Insert: ", "Inserting...");
//	            		
//	            		//DatabaseRating.addUserData(new UserRatingData("LaylaTest",1,"Best"));
//	            		DatabaseRating.addUserData(new UserRatingData(editText.getText().toString(),1,"In Level"));
//	    				
//	    			}
//	    		}
//	    		@Override
//	    		public void onNothingSelected(AdapterView<?> adapter){
//	    			
//	    		}
//			});
//
//	    	
//
//			//color
//			Random rnd=new Random();
//			r=rnd.nextInt(255+position);
//			g=rnd.nextInt(255+position);
//			b=rnd.nextInt(255+position);
//			grView.setBackgroundColor(Color.rgb(r, g, b));
//			
////			levelSpinner.setOnClickListener(new Button.OnClickListener(){
////	        	@Override
////	        	public void onClick(View click){
////	        		if (levelSpinner.getSelectedItemPosition()==8)
////	        			Toast.makeText(GameActivity.this, " "+position, Toast.LENGTH_SHORT).show();
////	        	}
////	        });
////			int col_i=1;
////			//position/2==1 - row; position%2==1 - col
////			if(position==0){
////				col_i=0;
////			}
////			if(position==1){
////				col_i=1;
////			}
////			int colorPos=position%colors.length;
////			grView.setBackgroundColor(colors[col_i]);
//			
////			editText.setOnClickListener(new OnClickListener() {
////				
////		        //find_and_mod()
////
////				
////				@Override
////				public void onClick(View v) {
////					// TODO Auto-generated method stub
//////					CharSequence constraint=editText.getText();
//////					editAdapter.getFilter().filter(constraint);
//////					editText.showDropDown();
////					Toast.makeText(GameActivity.this, " "+position, Toast.LENGTH_LONG).show();
////											
////				}
////			});
////			
//
//			textView.setOnClickListener(new OnClickListener() {
////				
//				@Override
//				public void onClick(View v) {
////					// TODO Auto-generated method stub
//					//grView.setC
//					Toast.makeText(GameActivity.this, " "+position, Toast.LENGTH_SHORT).show();//textView.setBackgroundColor(Color.parseColor("#7000D4"));
//				}
//			});
//			
			
		}else{
			
			grView=(View) convertView;
		
		}
		
		return grView;
	}
	
	
}