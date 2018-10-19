package com.decorator;

/**
 *  具体装饰类 A
 * @author Administrator
 *
 */
public class DecoratorComponentA extends Decorator{
	
	public DecoratorComponentA(){}
	
	public DecoratorComponentA(Component component){
		super(component);
	}
	
	@Override
	public void build() {
		System.out.println("A类装饰"); 
		this.component.build(); 
	}
	
	
	
	
}
