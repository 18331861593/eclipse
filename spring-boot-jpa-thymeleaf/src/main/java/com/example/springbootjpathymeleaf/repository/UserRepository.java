package com.example.springbootjpathymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springbootjpathymeleaf.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	

    User findById(long id);

    void deleteById(Long id);
    
  /*  @Query("update User set userName = ?1, password = ?2, age = ?3 where id = ?4")
	void update(String username, String password, int age, Long id);*/
}
