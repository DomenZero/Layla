package com.lepotuli.layla.managelist;

public class DataListAdapterSet_History {

	/*
	 * @author Merkulov Maksim (DomenZero) 
	 * <wardomenmax@gmail.com>
	 * 
	 * Adapter data
	 */
	private String name = "";
	private String level = "";


	public DataListAdapterSet_History(String name, String level) {
		this.name = name;
		this.level = level;
//		this.setName(name);
//		this.setLevel(level);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

//	public long getItemId(int position) {
//		return position;
//	}
	



}
