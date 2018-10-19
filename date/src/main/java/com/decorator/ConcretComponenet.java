package com.decorator;

/**
 * 具体的实现类
 * @author Administrator
 *
 */
public class ConcretComponenet implements Component{

	public void build() {
		System.out.println(" 装饰接口的具体实现");
	}
	
	
}
