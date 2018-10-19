package com.example.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.dao.StudentDao;
import com.example.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	private static List<Student> students;
	{
		students = new ArrayList<Student>();
		students.add(new Student(101, "Mike", "male"));
		students.add(new Student(102, "Huang", "male"));
		students.add(new Student(103, "Mao", "female"));
	}

	public Student addStudent(Student student) {
		// student.setId(System.currentTimeMillis());
		students.add(student);
		return student;
	}

	public long deleteStudent(Long id) {
		for (Student student : students) {
			if (student.getId() == id) {
				students.remove(student);
				return id;
			}
		}
		return id;
	}

	public Student queryOneStudent(Long id) {
		for (Student student : students) {
			if (student.getId() == id) {
				return student;
			}
		}
		return null;
	}

	public Student changeStudent(Student student) {
		for (Student s : students) {
			if (s.getId() == student.getId()) {
				return student;
			}
		}
		return null;
	}

	public List<Student> getStudentList() {
		return students;
	}

}
