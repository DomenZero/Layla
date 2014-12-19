package com.spyralem.layla.managelist;

import java.util.List;

import com.spyralem.layla.vogame.AboutActivity;
import com.spyralem.layla.vogame.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DataListAdapter extends ArrayAdapter<DataListAdapterSet> {

	protected static final String LOG_TAG = DataListAdapter.class.getSimpleName();
	
	private List<DataListAdapterSet> items;
	private int layoutResourceId;
	private Context context;

	public DataListAdapter(Context context, int layoutResourceId, List<DataListAdapterSet> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.items = items;
	}

	// Return the data item at position
	@Override
	public DataListAdapterSet getItem(int position) {
		return items.get(position);
	}
	
	// Return the number of items in the Adapter
	@Override
	public int getCount() {
		return items.size();
	}

	public long getItemID(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View row = convertView;
		PlayersHolder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new PlayersHolder();
		holder.infoPlayers = getItem(position);
		holder.removePaymentButton = (ImageButton)row.findViewById(R.id.atomPay_removePay);
		holder.removePaymentButton.setTag(holder.infoPlayers);
		
		//-------
		holder.addPaymentButton = (ImageButton)row.findViewById(R.id.atomPay_savePay);
		holder.addPaymentButton.setTag(holder.infoPlayers);
		//-------
		
		holder.name = (TextView)row.findViewById(R.id.atomPay_name);
		setNameTextChangeListener(holder,position, row);

		
//		holder.value = (TextView)row.findViewById(R.id.atomPay_value);
//		setValueTextListeners(holder);

		
		row.setTag(holder);

		setupItem(holder);
		return row;
	}
	
	private void setupItem(PlayersHolder holder) {
		holder.name.setText(holder.infoPlayers.getName());
		//holder.value.setText(String.valueOf(holder.infoPlayers.getValue()));
	}

	public static class PlayersHolder {
		DataListAdapterSet infoPlayers;
		TextView name;
		//TextView value;
		ImageButton removePaymentButton;
		ImageButton addPaymentButton;
	}
	
	private void setNameTextChangeListener(final PlayersHolder holder, final int position, View row) {
		
		holder.name.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				holder.infoPlayers.setName(s.toString());

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { 
				//Log.e(LOG_TAG, "error reading double value: " + s.toString());
			}

			@Override
			public void afterTextChanged(Editable s) { 			
			}
			
		});
		

//		holder.name.setOnTouchListener(new OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View row, MotionEvent arg1) {
//				// TODO Auto-generated method stub
//				Log.e(LOG_TAG, "Kill Bill: " + position);
//				return false;
//			}
//			
//		});
		
	}
//
//	private void setValueTextListeners(final AtomPaymentHolder holder) {
//		holder.value.addTextChangedListener(new TextWatcher() {
//
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//				try{
//					holder.atomPayment.setValue(Double.parseDouble(s.toString()));
//				}catch (NumberFormatException e) {
//					Log.e(LOG_TAG, "error reading double value: " + s.toString());
//				}
//			}
//
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
//
//			@Override
//			public void afterTextChanged(Editable s) { }
//		});
//	}
	
//	//------------------------------------
//	private void getValueTextListeners(final PlayersHolder holder) {
//		holder.value.setOnTouchListener(new OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
//				Log.e(LOG_TAG, "!!!!!!!!!!!!!!! " + holder.value.toString());
//				return false;
//			}
//		});
//		}


}