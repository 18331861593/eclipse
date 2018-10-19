package com.nhdz.utils;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nhdz.service.GroupService;
import com.nhdz.service.UserService;
import com.nhdz.utils.entity.Group;
import com.nhdz.utils.entity.User;

@Component
public class TestHtmlParser {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;
	

//	@Scheduled(cron="0/10 * * * * ?")
	public void executeInternal() throws Exception{
		DecimalFormat df = new DecimalFormat("#.00");
		long t = System.currentTimeMillis() - 1000 ;
		String time = t + (df.format(Math.random() * 100));
		
		HtmlParser hp = new HtmlParser("http://neihanshequ.com/joke/?is_json=1&app_name=neihanshequ_web&max_time="+time + "&=" + Math.random());
		List<Group> list = hp.getHrefList();
		User user = list.get(1).getUser();
		System.out.println("===============================");
		if(null == userService.getById(user.getUser_id()))
			System.out.println(userService.insert(user));
		System.out.println("===============================");
	}
	
}
