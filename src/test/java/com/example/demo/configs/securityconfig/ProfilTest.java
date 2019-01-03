package com.example.demo.configs.securityconfig;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

	@Test
	public void login() throws Exception {
		mvc.perform(post("/api/login").param("username", "GUEST").param("password", "")).andExpect(status().isOk());
	}

	@Test
	public void logoutWithoutCsrf() throws Exception {
		mvc.perform(post("/api/logout").with(userGuest())).andExpect(status().isForbidden());
	}

	@Test
	public void logoutWithoutUser() throws Exception {
		mvc.perform(post("/api/logout")).andExpect(status().isForbidden());
	}

	@Test
	public void logoutWithCsrf() throws Exception {
		mvc.perform(post("/api/logout").with(userGuest()).with(csrf())).andExpect(status().isOk());
	}

}