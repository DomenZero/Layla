package com.spyralem.layla.managelist;
import java.util.List;

import com.spyralem.layla.vogame.ControlActivity;
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
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


/*
 * @author Merkulov Maksim (DomenZero) 
 * <wardomenmax@gmail.com>
 * 
 * Adapter data's set
 */
public class DataListAdapter_History extends BaseAdapter{


    Context context;
    List<DataListAdapterSet_History> rowItem;

	public DataListAdapter_History(Context context, List<DataListAdapterSet_History> rowItem) {
        this.context = context;
        this.rowItem = rowItem;
	}
	
    @Override
    public int getCount() {

        return rowItem.size();
    }

    @Override
    public Object getItem(int position) {

        return rowItem.get(position);
    }

    @Override
    public long getItemId(int position) {

        return rowItem.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.fragmentgame_history2, null);
        }

        TextView imgIcon = (TextView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);

        DataListAdapterSet_History row_pos = rowItem.get(position);
        // setting the image resource and title
        imgIcon.setText(row_pos.getLevel());
        txtTitle.setText(row_pos.getName());

        return convertView;

    }

//	protected static final String LOG_TAG = DataListAdapter_History.class.getSimpleName();
//	
//	private List<DataListAdapterSet_History> items;
//	private int layoutResourceId;
//	private Context context;
//
//	public DataListAdapter_History(Context context, int layoutResourceId, List<DataListAdapterSet_History> items) {
//		super(context, layoutResourceId, items);
//		this.layoutResourceId = layoutResourceId;
//		this.context = context;
//		this.items = items;
//	}
//
//	// Return the data item at position
//	@Override
//	public DataListAdapterSet_History getItem(int position) {
//		return items.get(position);
//	}
//	
//	// Return the number of items in the Adapter
//	@Override
//	public int getCount() {
//		return items.size();
//	}
//
//	public long getItemID(int position) {
//		return position;
//	}
//	
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//
//        if (convertView == null) {
//            LayoutInflater mInflater = (LayoutInflater) context
//                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//            convertView = mInflater.inflate(R.layout.fragmentgame_history2, null);
//        }
//
//        TextView imgIcon = (TextView) convertView.findViewById(R.id.icon);
//        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
//
//        DataListAdapterSet_History row_pos = items.get(position);
//        // setting the image resource and title
//        imgIcon.setText(row_pos.getLevel());
//        txtTitle.setText(row_pos.getName());
//
//        return convertView;
//		//
////		View row = convertView;
////		PlayersHolder holder = null;
////
////		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
////		row = inflater.inflate(layoutResourceId, parent, false);
////
////		holder = new PlayersHolder();
////		holder.infoPlayersLevel = getItem(position);
////		
////		// 03/26 Test Set players & Level
//////		holder.name = (TextView)row.findViewById(R.id.history_players);
//////		holder.name.setTag(holder.infoPlayersLevel);
//////		
//////		holder.level = (TextView)row.findViewById(R.id.history_level);
//////		holder.level.setTag(holder.infoPlayersLevel);
//////		setNameTextChangeListener(holder,position, row);
////
////		row.setTag(holder);
////
////		setupItem(holder);
////		return row;
//	}
//	
////	private void setupItem(PlayersHolder holder) {
////		holder.name.setText(holder.infoPlayersLevel.getName());
////		holder.level.setText(holder.infoPlayersLevel.getName());
////		//holder.value.setText(String.valueOf(holder.infoPlayers.getValue()));
////	}
//
////	public static class PlayersHolder {
////		DataListAdapterSet_History infoPlayersLevel;
////		TextView name;
////		TextView level;
////	}
	
}


