package com.example.demo.configs;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.configs.listeners.SessionListener;

@Configuration
public class BeanConfig implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		context = applicationContext;
	}

	public static <T> T getBean(Class<T> beanClass) {
		return context.getBean(beanClass);
	}

	@Bean
	public ServletListenerRegistrationBean<SessionListener> sessionListener() {
		return new ServletListenerRegistrationBean<SessionListener>(new SessionListener());
	}

}
