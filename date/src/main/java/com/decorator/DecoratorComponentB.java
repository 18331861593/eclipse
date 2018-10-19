package com.decorator;

public class DecoratorComponentB extends Decorator {
	private String s;

	public DecoratorComponentB() {
		super();
	}

	public DecoratorComponentB(Component component) {
		super(component);
	}

	public DecoratorComponentB(Component component, String s) {
		super(component);
		this.s = s;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	@Override 
	public void build() { 
	System.out.println("B类实现装饰方法" + this.s); 
	this.component.build(); 
	}

}
