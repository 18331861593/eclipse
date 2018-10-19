package com.example.dao;

import java.util.List;

import com.example.model.Student;

public interface StudentDao {

	public Student addStudent(Student student);

	public long deleteStudent(Long id);

	public Student queryOneStudent(Long id);

	public Student changeStudent(Student student);

	public List<Student> getStudentList();
	
	

}
