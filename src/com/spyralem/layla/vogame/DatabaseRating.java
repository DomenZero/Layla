package com.spyralem.layla.vogame;

import java.util.ArrayList;
import java.util.List;

import com.spyralem.layla.model.PlayersData;
import com.spyralem.layla.model.UserRatingData;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseRating {

	//debug code
	public static final boolean DEBUG=true;
	public static final String LOG_TAG="DatabaseRating";
		
	//create database
	private static final String DATABASE_NAME="dbLayla.db";
	private static final int DATABASE_VERSION=5;
	
	//create table
	private static final String RATING_TABLE="rating";
	private static final String PLAYERS_TABLE="players";
	
	//table Rating fields
	private static final String ID_COLUMN="_id";
	private static final String USER_NAME_COLUMN="user_name";
	private static final String USER_RATING_COLUMN="user_rating";
	private static final String USER_STATUS_COLUMN="user_status";
	
	//table Players fields
	private static final String USER_LEVEL_COLUMN="user_level";
	private static final String USER_COLOR_COLUMN="user_color";
		
	private static final String[] ALL_TABLES = { RATING_TABLE, PLAYERS_TABLE };
	private static DataBaseHelper DBHelper=null;
	
	//table Rating syntax create
	private static final String RATING_CREATE="create table rating" +
			"( _id integer primary key autoincrement," +
			"user_name text not null, " +
			"user_rating integer, " +
			"user_status text not null" +
			");";

	//table Players syntax create
	private static final String PLAYERS_CREATE="create table players" +
			"( _id integer primary key autoincrement," +
			"user_name text not null, " +
			"user_level integer, " +
			"user_color text not null" +
			");";
	
	/*** Init DB ***/
	public static void init(Context context) {
		// TODO Auto-generated method stub
		if (DBHelper==null) {
			if (DEBUG)
				Log.i("DatabaseRating", context.toString());
			DBHelper=new DataBaseHelper(context);
	}
	}
	
	/*** Main Database ***/
	private static class DataBaseHelper extends SQLiteOpenHelper{
		public  DataBaseHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			if (DEBUG)
				Log.i(LOG_TAG, "New create");	
			try{
					db.execSQL(RATING_CREATE);
					db.execSQL(PLAYERS_CREATE);
					
			} catch (Exception exception){
				if (DEBUG) 
					Log.i(LOG_TAG, "Exception onCreate() exception");
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			if (DEBUG) {
				//rec in log
				Log.w(LOG_TAG, "Upgrading database from version "+oldVersion
						+"to"+newVersion+"...");		
				}
			//del old version
			for (String table:ALL_TABLES) {
				db.execSQL("DROP TABLE IF EXISTS "+table);
			}
			onCreate(db);
		}
	}
	
	/*** Open DB insert||update||delete ***/
	private static synchronized SQLiteDatabase open() throws SQLException{
		return DBHelper.getWritableDatabase();
	}
	
	private static synchronized SQLiteDatabase read() throws SQLException{
		return DBHelper.getReadableDatabase();
	}
	
	/*** UserRating Data func ***/
	public static void addUserData(UserRatingData uData) {
		
		//Open DB Read/Write
		
		final SQLiteDatabase db=open();
		
		String name=sqlEscapeString(uData.getUserName());
		Integer rating=sqlEscapeInteger(uData.getUserRating());
		String status=sqlEscapeString(uData.getUserStatus());
		ContentValues values=new ContentValues();
		values.put(USER_NAME_COLUMN, name);
		values.put(USER_RATING_COLUMN, rating);
		values.put(USER_STATUS_COLUMN, status);
		db.insert(RATING_TABLE, null, values);
		db.close();	
	}
	
	/*** Players Data insert function ***/
	public static void addPlayersData(PlayersData uData) {
		
		//Open DB Read/Write
		
		final SQLiteDatabase db=open();
		
		String name=sqlEscapeString(uData.getUserName());
		Integer level=sqlEscapeInteger(uData.getUserLevel());
		String color=sqlEscapeString(uData.getUserColor());
		ContentValues values=new ContentValues();
		values.put(USER_NAME_COLUMN, name);
		values.put(USER_LEVEL_COLUMN, level);
		values.put(USER_COLOR_COLUMN, color);
		db.insert(PLAYERS_TABLE, null, values);
		db.close();	
	}
	
	/*** Players Data update function ***/
//	public static int updatePlayersData_byID(String name, Integer level, String color, int id) {
//		
//		//Open DB Read/Write
//		
//		final SQLiteDatabase db=open();
//		
//		ContentValues values=new ContentValues();
//		
//		//values.put(USER_NAME_COLUMN, name);
//		values.put(USER_LEVEL_COLUMN, level);
//		//values.put(USER_COLOR_COLUMN, color);
//		
//		return db.update(PLAYERS_TABLE, values, ID_COLUMN+"= ?"+id, null);
//		//return db.update(PLAYERS_TABLE, values, ID_COLUMN+"=?"+id, new String []{String.valueOf(uData.getID())});
//	}

	/*** Players Data update function ***/
	public void updatePlayersData_byID(String nameval, String levelval, String color, int id) {
		
		//Open DB Read/Write
		
		final SQLiteDatabase db=read();
		
		String[] pin=new String[]{ String.valueOf(id)};
		
		ContentValues values=new ContentValues();
		
		//uData.setUserLevel(7);
		
		//values.put(USER_NAME_COLUMN, name);
		values.put(USER_NAME_COLUMN, nameval);
		values.put(USER_LEVEL_COLUMN, levelval);
		values.put(USER_COLOR_COLUMN, color);
		//values.put(USER_COLOR_COLUMN, color);
//		
//		String[] any=new String[1];
//		any[0]=uData.getUserName();
		
		db.update(PLAYERS_TABLE, values, ID_COLUMN+" like ?", pin);
		db.close();
		//return db.update(PLAYERS_TABLE, values, ID_COLUMN+"=?"+id, new String []{String.valueOf(uData.getID())});
	}

	/*** Players Data getting single contact 02/02***/
	public static String getPlayer(int id) {
		SQLiteDatabase db=read();
		
		String[] pin=new String[]{ String.valueOf(id)};
		String selectQuery="SELECT "+USER_NAME_COLUMN+" FROM "+PLAYERS_TABLE+" WHERE "+ID_COLUMN+"=?";
		
		Cursor c=db.rawQuery(selectQuery, pin);
//		
//		PlayersData data=new PlayersData();
//		data.setID(Integer.parseInt(c.getString(0)));
		
		c.moveToFirst();
		//String index=String.valueOf(c.getColumnIndex(USER_NAME_COLUMN));
		//return index;//c.getString(c.getColumnIndex(id));
		int index=c.getColumnIndex(USER_NAME_COLUMN);
		return c.getString(index);
		
		//		SQLiteDatabase db=read();
//		
//		Cursor cursor=db.query(PLAYERS_TABLE, new String[] {ID_COLUMN, 
//				USER_NAME_COLUMN, USER_LEVEL_COLUMN, USER_COLOR_COLUMN}, ID_COLUMN+"=?", new String[] {String.valueOf(id)},
//				null, null, null, null);
//		if(cursor!=null)
//			cursor.moveToFirst();
//	
//		
//		return data;
//		
	}
//	
	/*** 02/03 ***/
//	public String getPlayer(int id) {
//		SQLiteDatabase db=read();
//		
//		String[] 
//		
//		
//		return c.getString(index);
//	
//	}
	
	/*** Players Data update function ***/
//
//	public static void updatePlayersData_byID(PlayersData uData) {
//		
//		//Open DB Read/Write
//		
//		final SQLiteDatabase db=open();
//		
//		ContentValues values=new ContentValues();
//		
//		//values.put(USER_NAME_COLUMN, name);
//		values.put(USER_NAME_COLUMN, uData.getUserName());
//		values.put(USER_LEVEL_COLUMN, uData.getUserLevel());
//		values.put(USER_COLOR_COLUMN, uData.getUserColor());
//		//values.put(USER_COLOR_COLUMN, color);
//		
//		db.update(PLAYERS_TABLE, values, ID_COLUMN+"= ?", new String []{String.valueOf(uData.getID())});
//		db.close();
//		//return db.update(PLAYERS_TABLE, values, ID_COLUMN+"=?"+id, new String []{String.valueOf(uData.getID())});
//	}
//	
	/*** Players Data getting function ***/
	public static int getPlayersData() {
		
		String countQuery="SELECT * FROM "+PLAYERS_TABLE;
		//Open DB Read/Write
			
		final SQLiteDatabase db=open();
		
		Cursor cursor=db.rawQuery(countQuery, null);
		cursor.close();
		
		//return any count
		return cursor.getCount();
	}
	
	/*** Players Data del function by Name ***/
	public static void delPlayersData(String rowName) {
		
		//Open DB Read/Write
		
		final SQLiteDatabase db=open();
		
		db.delete(PLAYERS_TABLE, USER_NAME_COLUMN+"="+rowName, null);
		db.close();	
	}
	// Players Data del function by ID
	public static void delPlayersData_byID(int id) {
		
		//Open DB Read/Write
		
		final SQLiteDatabase db=open();
		db.delete(PLAYERS_TABLE, ID_COLUMN+"="+id, null );
		db.close();	
	}
	

	/*** Get All User Rating Data ***/
	public static List<UserRatingData> getAllUserData(){
		List<UserRatingData> contactList=new ArrayList<UserRatingData>();
		String selectQuery="SELECT *FROM "+RATING_TABLE;
		
		final SQLiteDatabase db=open();
		Cursor cursor=db.rawQuery(selectQuery, null);
		
		if (cursor.moveToFirst()) {
			do {
				UserRatingData data=new UserRatingData();
				data.setID(Integer.parseInt(cursor.getString(0)));
				data.setUserName(cursor.getString(1));
				data.setUserRating(Integer.parseInt(cursor.getString(2)));
				data.setUserStatus(cursor.getString(3));
				
				contactList.add(data);
			} while (cursor.moveToNext());
		}
		return contactList;
	}
	

	/*** Get All Players Data ***/
	public static List<PlayersData> getAllPlayersData(){
		List<PlayersData> contactList=new ArrayList<PlayersData>();
		String selectQuery="SELECT *FROM "+PLAYERS_TABLE;
		
		final SQLiteDatabase db=open();
		Cursor cursor=db.rawQuery(selectQuery, null);
		
		if (cursor.moveToFirst()) {
			do {
				PlayersData data=new PlayersData();
				data.setID(Integer.parseInt(cursor.getString(0)));
				data.setUserName(cursor.getString(1));
				data.setUserLevel(Integer.parseInt(cursor.getString(2)));
				data.setUserColor(cursor.getString(3));
				
				contactList.add(data);
			} while (cursor.moveToNext());
		}
		return contactList;
	}
	

	/*** return Integer ***/
	private static Integer sqlEscapeInteger(Integer anyInteger) {
		// TODO Auto-generated method stub
		return anyInteger;
	}

	/*** return anything String ***/
	private static String sqlEscapeString(String anyString) {
		// TODO Auto-generated method stub
		String anyReturn="";
		
		if (null!=anyString) {
			anyReturn=DatabaseUtils.sqlEscapeString(anyString);
			anyReturn=anyReturn.substring(1,anyReturn.length()-1);
		}
		return anyReturn;
	}



}
