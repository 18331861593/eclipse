package com.example.springbootjpathymeleaf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootjpathymeleaf.entity.User;
import com.example.springbootjpathymeleaf.repository.UserRepository;
import com.example.springbootjpathymeleaf.service.UserService;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
    	System.out.println("=====================");
    	System.out.println(user);
    	System.out.println("=====================");
        userRepository.save(user);
    }

    @Override
    public void edit(User user) {
    	userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

}
