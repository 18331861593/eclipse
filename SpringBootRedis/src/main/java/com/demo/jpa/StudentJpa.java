package com.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Student;

public interface StudentJpa extends JpaRepository<Student, Long>{
	
	
	
}
