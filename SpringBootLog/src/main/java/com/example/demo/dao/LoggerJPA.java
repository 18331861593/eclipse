package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Logger;

public interface LoggerJPA extends JpaRepository<Logger, Long>{
	
}
