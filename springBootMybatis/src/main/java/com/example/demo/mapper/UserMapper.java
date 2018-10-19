package com.example.demo.mapper;

import java.util.List;

/*import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;*/

import com.example.demo.entity.User;

public interface UserMapper {

//	@Select("select * from user")
//	@Results({ @Result(property = "id", column = "id"), @Result(property = "userName", column = "user_name"),
//			@Result(property = "passWord", column = "pass_word"), @Result(property = "email", column = "email"),
//			@Result(property = "nickName", column = "nick_name"), @Result(property = "regTime", column = "reg_time"), })
	List<User> getAll();
	
	
//	@Select("select * from user where id = #{id}")
//	@Results({ @Result(property = "id", column = "id"), @Result(property = "userName", column = "user_name"),
//		@Result(property = "passWord", column = "pass_word"), @Result(property = "email", column = "email"),
//		@Result(property = "nickName", column = "nick_name"), @Result(property = "regTime", column = "reg_time"), })
	User getOne(Long id);
	
//	@Insert("INSERT INTO user (email, nick_name, pass_word, reg_time, user_name) VALUES (#{email}, #{nickName}, #{passWord}, #{regTime}, #{userName});")
	void insert(User user);
	

//	@Delete("DELETE FROM user WHERE id =#{id}")
	void delete(Long id);

	
}
