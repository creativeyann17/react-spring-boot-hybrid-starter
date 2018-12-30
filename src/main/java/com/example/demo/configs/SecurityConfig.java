package com.example.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private EnvConfig envConfig;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (envConfig.isDEV()) {
			http.authorizeRequests().antMatchers("/**").permitAll().and().csrf().disable();
		} else {
			http.authorizeRequests().antMatchers("/*", "/static/**", "/api/**").permitAll().anyRequest().denyAll();
		}
		http.csrf().disable();
	}
}