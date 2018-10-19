package com.example.springbootjpathymeleaf.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springbootjpathymeleaf.entity.User;
import com.example.springbootjpathymeleaf.service.UserService;

@Controller
public class UserController {
	
	@Resource
	UserService userService;
	
	@RequestMapping("/")
	public String index(){
		return "redirect:/list";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<User> list = userService.getUserList();
		model.addAttribute("users", list);
        return "user/list";
	}
	

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(User user,BindingResult result) {
    	System.out.println(user);
        userService.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model,Long id) {
        User user=userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(User user,BindingResult result) {
        userService.edit(user);
        return "redirect:/list";
    }


    @RequestMapping("/delete")
    public String delete(Long id) {
        userService.delete(id);
        return "redirect:/list";
    }
	
}
