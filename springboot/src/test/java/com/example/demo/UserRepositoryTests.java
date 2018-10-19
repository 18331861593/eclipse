package com.example.demo;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void test() throws Exception {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);
		System.out.println(formattedDate);

		/*userRepository.save(new User("aa", "a", "aa123456", "aa@126.com", formattedDate));
		userRepository.save(new User("bb", "b", "bb123456", "bb@126.com", formattedDate));
		userRepository.save(new User("cc", "c", "cc123456", "cc@126.com", formattedDate));*/

		// Assert.assertEquals(9, userRepository.findAll().size());
		/*List<User> list = userRepository.findAll();
		for (User user : list) {
			System.out.println(user);
		}*/
		
		/*System.out.println(userRepository.count());*/
		
		/*System.err.println("查询 id 是 14  ：" + userRepository.exists(14L));*/
		
		/*System.err.println(userRepository.findAll(new PageRequest(1, 10)));*/
		/*System.err.println(userRepository.count());*/
		/*userRepository.delete(14l);*/
		
		/*System.err.println(userRepository.findByUserName("cc"));*/
		
		System.err.println(userRepository.findByUserNameOrEmail("cc", "c"));
		
		
	}

}
