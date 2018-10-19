package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.StudentDao;
import com.example.model.Student;

@RestController()
public class StudentController {

	@Autowired
	private StudentDao studentDaoImpl;

	@RequestMapping(value = "/students", method = RequestMethod.GET)
	@ResponseBody
	public List<Student> getStudentList() {
		List<Student> students = studentDaoImpl.getStudentList();
		return students;
	}

	@RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
	public ResponseEntity<Student> queryOneStudent(@PathVariable("id") long id) {
		Student student = studentDaoImpl.queryOneStudent(id);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deleteStudent(@PathVariable("id") long id) {
		long showId = studentDaoImpl.deleteStudent(id);
		return new ResponseEntity<Long>(showId, HttpStatus.OK);
	}

	@RequestMapping(value = "/students/{id}", method = RequestMethod.POST)
	public ResponseEntity<Student> addStudent(@PathVariable("id") long id) {
		Student student = new Student(id, "rex", "male");
		student = studentDaoImpl.addStudent(student);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Student> changeStudent(@PathVariable("id") long id) {
		Student student = new Student(id, "leona", "female");
		student = studentDaoImpl.changeStudent(student);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
}