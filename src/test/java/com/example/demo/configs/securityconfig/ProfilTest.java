package com.example.demo.configs.securityconfig;

import java.nio.file.Paths;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class ProfilTest extends ProfilAll {

	@Test
	public void cors() {
		this.webClient.get().uri(Paths.get(appConfig.getApiBaseUrl(), "/version").toString()).header(HttpHeaders.ORIGIN, appConfig.getCorsUrl()).exchange()
				.expectStatus().isUnauthorized();
	}

	@Test
	public void swaggerUI() {
		this.webClient.get().uri("/swagger-ui.html").exchange().expectStatus().isUnauthorized();
	}

	@Test
	public void h2() {
		this.webClient.get().uri("/h2-console.html").exchange().expectStatus().isUnauthorized();
	}

}