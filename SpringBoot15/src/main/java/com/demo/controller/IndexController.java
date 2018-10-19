package com.demo.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.assertj.core.error.ErrorMessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.DemoEntity;

@RestController
public class IndexController {
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/validator")
	public String validator(@Valid DemoEntity entity,BindingResult result){
		if(result.hasErrors()){
			StringBuffer msg = new StringBuffer();
			List<FieldError> fieldErrors = result.getFieldErrors();
			Locale currentLocale = LocaleContextHolder.getLocale();
			for (FieldError fieldError : fieldErrors) {
				String errorMsg = messageSource.getMessage(fieldError, currentLocale);
				msg.append(fieldError.getField() + "   " + errorMsg+",");
			}
			return msg.toString();
		}
		return entity.toString();
	}
	
	
	
}
