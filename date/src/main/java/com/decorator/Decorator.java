package com.decorator;

/**
 * 装饰类
 * 
 * @author Administrator
 *
 */
public class Decorator implements Component {

	public Component component;

	public Decorator() {
	}

	public Decorator(Component component) {
		this.component = component;
	}

	public void build() {
		this.component.build(); 
	} 


}
