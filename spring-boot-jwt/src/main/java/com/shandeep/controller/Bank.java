package com.shandeep.controller;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bank 
{
	private String name;
	private int id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
