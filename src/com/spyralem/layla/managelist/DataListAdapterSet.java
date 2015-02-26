package com.spyralem.layla.managelist;

import java.io.Serializable;

public class DataListAdapterSet implements Serializable {
	
	private String name = "";


	public DataListAdapterSet(String name, double value) {
		this.setName(name);
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
	


}
