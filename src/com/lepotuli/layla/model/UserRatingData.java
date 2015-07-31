package com.lepotuli.layla.model;

/*
 * @author Merkulov Maksim (DomenZero) 
 * <wardomenmax@gmail.com>
 * lepotuli.com
 * 
 * Model for Table "Rating" - only rating Winner-user
 */

public class UserRatingData {
	
	// private variable
	int _id;
	String _user_name;
	Integer _user_rating;
	String _user_status;
	
	// empty constructor
	public UserRatingData(){
		
	}

	// constructors
	public UserRatingData(int id, String name, Integer rating, String status){
		this._id=id;
		this._user_name=name;
		this._user_rating=rating;
		this._user_status=status;
	}
	
	public UserRatingData(String name, Integer rating, String status){
		this._user_name=name;
		this._user_rating=rating;
		this._user_status=status;
	}
	
	// getting ID
	public int getID() {
		return this._id;		
	}
	
	// setting ID
	public void setID(int id){
		this._id=id;
	}
	
	// getting USER NAME
	public String getUserName() {
		return this._user_name;		
	}
	
	// setting USER NAME
	public void setUserName(String name){
		this._user_name=name;
	}
	
	// getting USER RATING
	public Integer getUserRating() {
		return this._user_rating;		
	}
	
	// setting USER RATING
	public void setUserRating(Integer rating){
		this._user_rating=rating;
	}
	
	// getting USER STATUS
	public String getUserStatus() {
		return this._user_status;		
	}
	
	// setting USER STATUS
	public void setUserStatus(String status){
		this._user_status=status;
	}
	
	@Override
	public String toString(){
		return "RatingInfo [name="+_user_name+", rating="+_user_rating+", status="+_user_status+"]";
	}
}
