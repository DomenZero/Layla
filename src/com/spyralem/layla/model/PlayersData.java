package com.spyralem.layla.model;

/*
 * @author Merkulov Maksim (DomenZero) 
 * <wardomenmax@gmail.com>
 * 
 * Model for Table "Players" - there Settings user save/update/delete
 */

public class PlayersData {
	
	// Private variable
	int _id;
	String _user_name;
	Integer _user_level;
	String _user_color;
	
	// Empty constructor
	public PlayersData(){
		
	}

	// Constructors
	public PlayersData(int id, String name, Integer level, String color){
		this._id=id;
		this._user_name=name;
		this._user_level=level;
		this._user_color=color;
	}
	
	public PlayersData(String name, Integer level, String color){
		this._user_name=name;
		this._user_level=level;
		this._user_color=color;
	}
	
	// Getting ID
	public int getID() {
		return this._id;		
	}
	
	// Setting ID
	public void setID(int id){
		this._id=id;
	}
	
	// Getting USER NAME
	public String getUserName() {
		return this._user_name;		
	}
	
	// setting USER NAME
	public void setUserName(String name){
		this._user_name=name;
	}
	
	// Getting USER LEVEL
	public Integer getUserLevel() {
		return this._user_level;		
	}
	
	// setting USER LEVEL
	public void setUserLevel(Integer level){
		this._user_level=level;
	}
	
	// Getting USER COLOR
	public String getUserColor() {
		return this._user_color;		
	}
	
	// setting USER COLOR
	public void setUserColor(String color){
		this._user_color=color;
	}
	
	@Override
	public String toString(){
		return "PlayersInfo [name="+_user_name+", level="+_user_level+", color="+_user_color+"]";
	}
}
