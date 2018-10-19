package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{
	
	
	//方法命名查询
	List<Note> findByBody(String body);
	//方法命名查询
	List<Note> findByTitleAndBody(String title, String body);
	
	
	//用关键字限制结果数量，用top和first来实现
	List<Note> findFirst10ByTitle(String title);
	
	List<Note> findFirst30ByTitle(String title);
	
	Note findOneById(long id);
	
	@Query("select p from Note p where p.title like %:title%")
    List<Note> anyQuery(@Param("title")String title); 
}
