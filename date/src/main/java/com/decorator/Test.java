package com.decorator;

public class Test {
	public static void main(String[] args) {
//		 Component component = new DecoratorComponentA((new ConcretComponenet())); 
//		 component.build(); //进行了A类的装饰 
//		 Component component1 = new DecoratorComponentB(component,"a"); 
//		 component1.build(); //在A类装饰的基础上进行了B类的装饰 
		Component component = new DecoratorComponentA(new DecoratorComponentB(new ConcretComponenet(),"a")); 
		component.build(); 
	}
}
