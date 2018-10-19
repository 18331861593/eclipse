package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class FastJsonConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * 修改自动义消息转换器
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// 调用父类配置
		super.configureMessageConverters(converters);
		// 定义 fastConverter 消息转换器
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

		// 创建配置类
		FastJsonConfig fastJsonConfig = new FastJsonConfig();

		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);

		fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);

		fastConverter.setFastJsonConfig(fastJsonConfig);
		converters.add(fastConverter);

	}

}
