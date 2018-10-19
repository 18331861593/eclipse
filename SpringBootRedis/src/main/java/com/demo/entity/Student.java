package com.demo.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Student implements Serializable {
	private static final long serialVersionUID = -638858789383946672L;

	@Id
	@GeneratedValue
	@Column(name = "student_no")
	private Long studentNo;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "sex")
	private String sex;

	public Long getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(Long studentNo) {
		this.studentNo = studentNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
