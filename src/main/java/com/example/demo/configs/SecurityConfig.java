package com.example.demo.configs;

import java.nio.file.Paths;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.configs.restsecurity.RESTAuthenticationEntryPoint;
import com.example.demo.configs.restsecurity.RESTAuthenticationFailureHandler;
import com.example.demo.configs.restsecurity.RESTAuthenticationSuccessHandler;
import com.example.demo.configs.restsecurity.RESTCsrfTokenResponseHeaderBindingFilter;
import com.example.demo.configs.restsecurity.RESTLogoutSuccessHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String GUEST_ROLE = "GUEST";

	private Logger log = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	private EnvConfig envConfig;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private RESTLogoutSuccessHandler httpLogoutSuccessHandler;

	@Autowired
	private RESTAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private RESTAuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private RESTAuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private RESTCsrfTokenResponseHeaderBindingFilter csrfTokenResponseHeaderBindingFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		final String loginUrl = Paths.get(appConfig.getApiBaseUrl(), "/login").toString();
		final String logoutUrl = Paths.get(appConfig.getApiBaseUrl(), "/logout").toString();

		// login / logout
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		http.formLogin().loginProcessingUrl(loginUrl).successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler);
		http.logout().logoutUrl(logoutUrl).logoutSuccessHandler(httpLogoutSuccessHandler);

		// DEV and no-DEV
		if (envConfig.isDEV()) {
			http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
		} else {
			// CORS
			http.cors().disable();
			// URLs
			http.authorizeRequests().antMatchers("/", "/static/**", loginUrl, logoutUrl).permitAll();
			http.authorizeRequests().antMatchers(Paths.get(appConfig.getApiBaseUrl(), "/**").toString()).authenticated();
			http.authorizeRequests().anyRequest().denyAll();
			// CSRF
			http.csrf().ignoringAntMatchers(loginUrl);
			http.addFilterAfter(csrfTokenResponseHeaderBindingFilter, CsrfFilter.class);
		}
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser(appConfig.getGuest().getUsername()).password(appConfig.getGuest().getPassword())
				.roles(GUEST_ROLE);
	}

	@Override
	public void configure(final WebSecurity web) {
		appConfig.getPublicUrls().stream().forEach(url -> web.ignoring().antMatchers(url));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
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