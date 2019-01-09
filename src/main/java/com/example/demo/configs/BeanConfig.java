package com.example.demo.configs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.utils.JsonParser;
import com.example.demo.websocket.APIWebSocketManager;

@Configuration
public class BeanConfig implements ApplicationContextAware {

	private static ApplicationContext context;

	@Bean
	public JsonParser jsonParser() {
		return new JsonParser();
	}

	@Bean
	public APIWebSocketManager apiWebSocketManager() {
		return new APIWebSocketManager();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		context = applicationContext;
	}

	public static <T> T getBean(Class<T> beanClass) {
		return context.getBean(beanClass);
	}

}
