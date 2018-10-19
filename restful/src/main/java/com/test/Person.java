package com.test;

import java.util.Date;

public class Person {

	public String name;

	public String sex;

	public Date birthday;

	public String phone;

	public String email;

	@Override
	public String toString() {
		return "Person [name=" + name + ", sex=" + sex + ", birthday=" + birthday + ", phone=" + phone + ", email="
				+ email + "]";
	}

}
