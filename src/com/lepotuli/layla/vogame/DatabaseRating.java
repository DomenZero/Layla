package com.lepotuli.layla.vogame;

import java.util.ArrayList;
import java.util.List;

import com.lepotuli.layla.model.PlayersData;
import com.lepotuli.layla.model.UserRatingData;

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

/*
 * @author Merkulov Maksim (DomenZero) 
 * <wardomenmax@gmail.com>
 * 
 * Library for Database (dbLayla.db) & all table (rating; players)
 */

public class DatabaseRating {

	// Debug code
	public static final boolean DEBUG=true;
	public static final String LOG_TAG="DatabaseRating";
		
	// Create database
	private static final String DATABASE_NAME="dbLayla.db";
	private static final int DATABASE_VERSION=10;
	
	// Create table
	private static final String RATING_TABLE="rating";
	private static final String PLAYERS_TABLE="players";
	
	// Table Rating fields
	private static final String ID_COLUMN="_id";
	private static final String USER_NAME_COLUMN="user_name";
	private static final String USER_RATING_COLUMN="user_rating";
	private static final String USER_STATUS_COLUMN="user_status";
	
	// Table Players fields
	private static final String USER_LEVEL_COLUMN="user_level";
	private static final String USER_COLOR_COLUMN="user_color";
	private static final String USER_AVATAR_COLUMN="user_avatar";
	private static final String USER_SETTINGS_COLUMN="user_settings";
		
	private static final String[] ALL_TABLES = { RATING_TABLE, PLAYERS_TABLE };
	private static DataBaseHelper DBHelper=null;
	
	// Table Rating syntax create
	private static final String RATING_CREATE="create table rating" +
			"( _id integer primary key autoincrement," +
			"user_name text not null, " +
			"user_rating integer, " +
			"user_status text not null" +
			");";

	// Table Players syntax create
	private static final String PLAYERS_CREATE="create table players" +
			"( _id integer primary key autoincrement," +
			"user_name text not null, " +
			"user_level integer, " +
			"user_color text not null, " +
			"user_avatar text not null, " +
			"user_settings text not null" +
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
			if (DEBUG) {
				// Rec in log
				Log.w(LOG_TAG, "Upgrading database from version "+oldVersion
						+"to"+newVersion+"...");		
				}
			
			// Delete old version
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
		String avatar=sqlEscapeString(uData.getUserAvatar());
		String settings=sqlEscapeString(uData.getUserSettings());
		ContentValues values=new ContentValues();
		
		values.put(USER_NAME_COLUMN, name);
		values.put(USER_LEVEL_COLUMN, level);
		values.put(USER_COLOR_COLUMN, color);
		values.put(USER_AVATAR_COLUMN, avatar);
		values.put(USER_SETTINGS_COLUMN, settings);
		
		db.insert(PLAYERS_TABLE, null, values);
		db.close();	
	}
	
	/*** UserRating Data update function ***/
	public void updateUserRatingData_byID(String name, Integer rating, String status, int id) {
		//Open DB Read/Write
		
		final SQLiteDatabase db=read();
		
		String[] pin=new String[]{ String.valueOf(id)};
		
		ContentValues values=new ContentValues();
		
		//values.put(USER_NAME_COLUMN, name);
		values.put(USER_NAME_COLUMN, name);
		values.put(USER_RATING_COLUMN, rating);
		values.put(USER_STATUS_COLUMN, status);
		//values.put(USER_COLOR_COLUMN, color);

		db.update(RATING_TABLE, values, ID_COLUMN+" like ?", pin);
		db.close();
	}
	
	/*** Players Data update function ***/
	public void updatePlayersData_byID(String nameval, Integer levelval, String color, String avatar, String settings, int id) {
		
		//Open DB Read/Write
		
		final SQLiteDatabase db=read();
		
		String[] pin=new String[]{ String.valueOf(id)};
		ContentValues values=new ContentValues();
		
		//values.put(USER_NAME_COLUMN, name);
		values.put(USER_NAME_COLUMN, nameval);
		values.put(USER_LEVEL_COLUMN, levelval);
		values.put(USER_COLOR_COLUMN, color);
		values.put(USER_AVATAR_COLUMN, avatar);
		values.put(USER_SETTINGS_COLUMN, settings);
		//values.put(USER_COLOR_COLUMN, color);
	
		db.update(PLAYERS_TABLE, values, ID_COLUMN+" like ?", pin);
		db.close();
	}
	
	/*** Players Data update ot 02/17 function ***/
	public static void updatePlayersData_byName(String nameval, Integer rating, String color, int id) {
		
		//Open DB Read/Write
		
		final SQLiteDatabase db=read();
	    int numPlayer=getUserRating(nameval);
		String[] pin=new String[]{ String.valueOf(numPlayer)};
		ContentValues values=new ContentValues();
		
		//values.put(USER_NAME_COLUMN, name);
		values.put(USER_NAME_COLUMN, nameval);
		values.put(USER_RATING_COLUMN, rating);
		values.put(USER_STATUS_COLUMN, color);
		//values.put(USER_COLOR_COLUMN, color);

		db.update(RATING_TABLE, values, ID_COLUMN+"= ?", pin);
		db.close();
	}

	/*** Rating Players Data update function ***/
	public static void updateLevelData_byID(String nameval, Integer levelval, String color) {
		
		//Open DB Read/Write
		
		final SQLiteDatabase db=read();
		
		String[] pin=new String[]{ nameval};
		ContentValues values=new ContentValues();

		//values.put(USER_NAME_COLUMN, name);
//		values.put(USER_NAME_COLUMN, nameval);
		values.put(USER_RATING_COLUMN, levelval);
		values.put(USER_STATUS_COLUMN, color);
		//values.put(USER_COLOR_COLUMN, color);
		
		db.update(RATING_TABLE, values, USER_NAME_COLUMN+" like ?", pin);
		db.close();
	}
	
	/*** User Rating Data getting single contact 02/12***/
	public static Integer getUserRating(String name) {
		SQLiteDatabase db=read();
		
		String[] pin=new String[]{ name};
		String selectQuery="SELECT "+USER_NAME_COLUMN+" FROM "+RATING_TABLE+" WHERE "+ID_COLUMN+"=?";
		
		Cursor c=db.rawQuery(selectQuery, pin);
	
		c.moveToFirst();
		int index=c.getColumnIndex(USER_NAME_COLUMN);
		return index;
	}
	
	/*** Rating Data getting single contact 02/18***/
	public static String getPlayerFromRating(String name) {
		SQLiteDatabase db=read();
		
		String[] pin=new String[]{ name};
		String selectQuery="SELECT "+USER_NAME_COLUMN+" FROM "+RATING_TABLE+" WHERE "+USER_NAME_COLUMN+" = ?";
		Log.i(LOG_TAG, "error");
		Cursor c=db.rawQuery(selectQuery, pin);
		Log.i(LOG_TAG, "error2");
		
		String index=null;
		if (c!=null){
			if (c.moveToFirst()){
				index=c.getString(c.getColumnIndex(USER_NAME_COLUMN));
				c.close();
			}
			else
				Log.i(LOG_TAG, "MoveFirst Error in getPlayerFromRating");
		} else
			Log.i(LOG_TAG, "error");
						
		//String index=String.valueOf(c.getColumnIndex(USER_NAME_COLUMN));
		//return index;//c.getString(c.getColumnIndex(id));
		return index;
	}
	
	/*** Rating Data get Level 02/19***/
	public static Integer getLevelFromRating(String name) {
		SQLiteDatabase db=read();
		
		String[] pin=new String[]{ name};
		String selectQuery="SELECT "+USER_RATING_COLUMN+" FROM "+RATING_TABLE+" WHERE "+USER_NAME_COLUMN+" = ?";
		Log.i(LOG_TAG, "1error");
		Cursor c=db.rawQuery(selectQuery, pin);
		Log.i(LOG_TAG, "1error2");
		
		Integer index=null;
		if (c!=null){
			if (c.moveToFirst()){
				index=c.getInt(c.getColumnIndex(USER_RATING_COLUMN));
				Log.i(LOG_TAG, "Get Level: "+index);
				c.close();
				
			}
			else
				Log.i(LOG_TAG, "MoveFirst Error in getPlayerFromRating" );
		} else
			Log.i(LOG_TAG, "error");
		
		//String index=String.valueOf(c.getColumnIndex(USER_NAME_COLUMN));
		//return index;//c.getString(c.getColumnIndex(id));
		return index;
	}

	/*** Players Data getting single contact 02/02***/
	public static String getPlayer(int id) {
		SQLiteDatabase db=read();
		
		String[] pin=new String[]{ String.valueOf(id)};
		String selectQuery="SELECT "+USER_NAME_COLUMN+" FROM "+PLAYERS_TABLE+" WHERE "+ID_COLUMN+"=?";
		
		Cursor c=db.rawQuery(selectQuery, pin);

		c.moveToFirst();
		int index=c.getColumnIndex(USER_NAME_COLUMN);
		return c.getString(index);
		
	}

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
	
	/*** Players Data del function by ID ***/
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
				data.setUserAvatar(cursor.getString(4));
				data.setUserSettings(cursor.getString(5));
				
				contactList.add(data);
			} while (cursor.moveToNext());
		}
		return contactList;
	}
	
	/*** Return Integer ***/
	private static Integer sqlEscapeInteger(Integer anyInteger) {
		// TODO Auto-generated method stub
		return anyInteger;
	}

	/*** Return anything String ***/
	private static String sqlEscapeString(String anyString) {
		// TODO Auto-generated method stub
		String anyReturn="";
		
		if (null!=anyString) {
			anyReturn=DatabaseUtils.sqlEscapeString(anyString);
			anyReturn=anyReturn.substring(1,anyReturn.length()-1);
		}
		return anyReturn;
	}
	
	// Del All data from rating table
	public static void delPlayersDataAll_Rating() {
		final SQLiteDatabase db=open();
		//db.delete(RATING_TABLE, ID_COLUMN+"="+id, null );
		//db.delete(RATING_TABLE, null, null);
		db.execSQL("delete from "+RATING_TABLE);
		db.close();	
	}
	
	// Del All data from players table
	public static void delPlayersDataAll_Players() {
		final SQLiteDatabase db=open();
		//db.delete(RATING_TABLE, ID_COLUMN+"="+id, null );
		//db.delete(RATING_TABLE, null, null);
		db.execSQL("delete from "+PLAYERS_TABLE);
		db.close();	
	}
	
	// Determine null data in table
	public static boolean determine_Table() {
		String selectQuery="SELECT *FROM "+PLAYERS_TABLE;
		
		final SQLiteDatabase db=open();
		Cursor cursor=db.rawQuery(selectQuery, null);
		if(cursor.getCount()==0)
			return false;
		return true;
	}
	
}
