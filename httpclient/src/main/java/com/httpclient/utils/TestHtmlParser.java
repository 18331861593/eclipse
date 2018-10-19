package com.httpclient.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TestHtmlParser extends QuartzJobBean {
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException{
		DecimalFormat df = new DecimalFormat("#.00");
		long t = System.currentTimeMillis() - 1000 ;
		String time = t + (df.format(Math.random() * 100));
		
		HtmlParser hp = new HtmlParser("http://neihanshequ.com/joke/?is_json=1&app_name=neihanshequ_web&max_time="+time + "&=" + Math.random());
		System.out.println(hp.getHtmlUrl());
		hp.outResult();
	}
	
	
}
