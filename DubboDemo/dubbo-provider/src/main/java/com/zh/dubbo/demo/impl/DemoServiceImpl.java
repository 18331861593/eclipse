package com.zh.dubbo.demo.impl;


import java.util.ArrayList;  
import java.util.List;

import com.zh.dubbo.demo.DemoService;  

public class DemoServiceImpl implements DemoService {  
  public List<String> getPermissions(int id) {  
      List<String> demo = new ArrayList<String>();  
      for (int i = 0; i < id; i++) {
		demo.add("demo " + i);
	}
      return demo;  
  }  
}  
