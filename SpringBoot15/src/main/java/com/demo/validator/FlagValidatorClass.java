package com.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Object>{
	
	/**
	 * 临时变量保存值列表
	 */
	private String values;
	
	@Override
	public void initialize(FlagValidator flagValidator) {
		//将注解中配置的值赋给临时变量
		this.values = flagValidator.values();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String[] valueArr = values.split(",",-1);
		
		boolean isFlag = false;
		for (int i = 0; i < valueArr.length; i++) {
			if(valueArr[i].equals(value)){
				isFlag = true;
				break;
			}
		}
		
		return isFlag;
	}

}
