package com.lepotuli.layla.managelist;

import java.io.Serializable;


public class DataListAdapterSet_DelSettings implements Serializable{
	/*
	 * @author Merkulov Maksim (DomenZero) 
	 * <wardomenmax@gmail.com>
	 * lepotuli.com
	 * 
	 * Adapter data
	 */
	private static final long serialVersionUID = 1L;
	private String name = "";
	int id;


	public DataListAdapterSet_DelSettings(String name, int id) {
		this.setName(name);
		this.setId(id);
	//	this.setValue(value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getItemId(int position) {
		return position;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
