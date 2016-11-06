package com.test.formdata.dto;

import java.io.Serializable;

/**
 * Created by satish.gummadi on 05-11-2014.
 */
public class PropertyBean implements Serializable {
	
	private static final long serialVersionUID = 4854443368926520552L;
	String id;
	String value;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
