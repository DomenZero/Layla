package com.lepotuli.layla.managelist;

import java.io.Serializable;

public class DataListAdapterSet implements Serializable {
	
	/*
	 * @author Merkulov Maksim (DomenZero) 
	 * <wardomenmax@gmail.com>
	 * lepotuli.com
	 * 
	 * Adapter data
	 */
	private static final long serialVersionUID = 1L;
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
