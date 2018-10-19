package com.xmyself.log4j;


//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	
	public static void main(String[] args) {
		Test t = new Test();
		t.test();
		
	}
	
}

class Test {
	Logger log = LoggerFactory.getLogger(Test.class);
	public  void test(){
		log.info("hello, my name is {}", "chengyi");
	}
}
