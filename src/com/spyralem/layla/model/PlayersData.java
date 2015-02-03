package com.spyralem.layla.model;

public class PlayersData {
	
	//private var
	int _id;
	String _user_name;
	Integer _user_level;
	String _user_color;
	
	//empty constructor
	public PlayersData(){
		
	}

	//constructors
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
	
	//getting ID
	public int getID() {
		return this._id;		
	}
	
	//setting ID
	public void setID(int id){
		this._id=id;
	}
	
	//getting USER NAME
	public String getUserName() {
		return this._user_name;		
	}
	
	//setting USER NAME
	public void setUserName(String name){
		this._user_name=name;
	}
	
	//getting USER LEVEL
	public Integer getUserLevel() {
		return this._user_level;		
	}
	
	//setting USER LEVEL
	public void setUserLevel(Integer level){
		this._user_level=level;
	}
	
	//getting USER COLOR
	public String getUserColor() {
		return this._user_color;		
	}
	
	//setting USER COLOR
	public void setUserColor(String color){
		this._user_color=color;
	}
	
	@Override
	public String toString(){
		return "PlayersInfo [name="+_user_name+", level="+_user_level+", color="+_user_color+"]";
	}
}
