package com.example.demo.jpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.User;

public interface UserJpa extends 
			JpaRepository<User, Long>, 
			JpaSpecificationExecutor<User>,
			Serializable {
	
	

}
