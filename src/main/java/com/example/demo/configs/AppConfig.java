package com.example.demo.configs;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

	/**
	 * Base API URL where the front-end do its requests
	 */
	@NotBlank
	private String apiBaseUrl;

	/**
	 * Cross-Origin URL allowed in DEV mode
	 */
	@NotBlank
	private String corsUrl;

	/**
	 * Ignored by Spring Security
	 */
	private Set<String> publicUrls = new TreeSet<>();

}
