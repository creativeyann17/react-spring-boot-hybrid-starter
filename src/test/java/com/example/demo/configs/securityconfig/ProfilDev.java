package com.example.demo.configs.securityconfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Paths;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.configs.EnvConfig;

@ActiveProfiles(EnvConfig.DEV)
public class ProfilDev extends ProfilAll {

	@Test
	public void cors() {
		this.webClient.get().uri(Paths.get(appConfig.getApiBaseUrl(), "/version").toString()).header(HttpHeaders.ORIGIN, appConfig.getCorsUrl()).exchange()
				.expectStatus().isOk();
	}

	@Test
	public void swaggerUI() {
		this.webClient.get().uri("/swagger-ui.html").exchange().expectStatus().isOk();
	}

	@Test
	public void h2() {
		this.webClient.get().uri("/h2-console.html").exchange().expectStatus().isNotFound(); // maybe because it's a test, should be isOk()
	}

	@Test
	public void login() throws Exception {
		mvc.perform(post("/api/login").param("username", "GUEST").param("password", "")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "GUEST")
	public void logout() throws Exception {
		mvc.perform(post("/api/logout")).andExpect(status().isOk());
	}

}