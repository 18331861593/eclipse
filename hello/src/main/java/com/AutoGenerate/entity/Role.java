package com.AutoGenerate.entity;

public class Role {
	
	private Integer id;
	private String	name;
	private String mname;
	
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role(int id, String name, String mname) {
		super();
		this.id = id;
		this.name = name;
		this.mname = mname;
	}
	public Role() {
		super();
	}
	
}
