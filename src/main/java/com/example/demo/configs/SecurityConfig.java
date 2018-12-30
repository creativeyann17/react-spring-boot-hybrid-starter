package com.example.demo.configs;

import java.nio.file.Paths;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private Logger log = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	private EnvConfig envConfig;

	@Autowired
	private AppConfig appConfig;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (envConfig.isDEV()) {
			http.cors().and().headers().frameOptions().sameOrigin().and().authorizeRequests().antMatchers("/**").permitAll().anyRequest().denyAll();
		} else {
			http.cors().disable().authorizeRequests()
					.antMatchers("/", "/manifest.json", "/favicon.ico", "/static/**", Paths.get(appConfig.getApiBaseUrl(), "/**").toString()).permitAll().anyRequest()
					.denyAll();
		}
		http.csrf().disable();
	}

	@Bean
	@Profile(EnvConfig.DEV)
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList(appConfig.getCorsUrl()));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "HEAD", "PUT", "DELETE"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration(Paths.get(appConfig.getApiBaseUrl(), "/**").toString(), configuration);
		log.debug("CORS enabled: {}", source.getCorsConfigurations().keySet());
		return source;
	}
}