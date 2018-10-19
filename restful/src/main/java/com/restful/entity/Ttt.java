package com.restful.entity;

public class Ttt {

	@Override
	public String toString() {
		return "Ttt [id=" + id + ", name=" + name + ", telephone=" + telephone + ", descr=" + descr + ", gender="
				+ gender + ", education=" + education + "]";
	}

	private int id;
	
	private String name;
	
	private String telephone;
	
	private String descr;
	
	private String gender;
	
	private String education;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

}
