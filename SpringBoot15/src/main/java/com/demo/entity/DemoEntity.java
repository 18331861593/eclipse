package com.demo.entity;

import java.io.Serializable;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.demo.validator.FlagValidator;

public class DemoEntity implements Serializable {
	
	@NotBlank
	@Length(min = 2, max = 10)
	private String name;

	@Min(value = 1)
	private int age;

	@NotBlank
	@Email
	private String email;
	
	@FlagValidator(values="1,2,3,4")
	private String flag;
	

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private static final long serialVersionUID = -174402762667858334L;

	@Override
	public String toString() {
		return "DemoEntity [name=" + name + ", age=" + age + ", email=" + email + "]";
	}

}
