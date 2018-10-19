package com.example.demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务1
 * @author Administrator
 *
 */
@Component
public class SchedulerTask {
	
	private int count = 0;
	
	@Scheduled(cron = "*/1 * * * * ?")
	private void process(){
		count ++;
		System.err.println("this is scheduler task runing " + count);
	}
		
			
	
}
