package com.example.demo.configs.securityconfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.configs.EnvConfig;

@ActiveProfiles(EnvConfig.DEV)
public class ProfilDev extends ProfilAll {

	@Test
	public void cors() throws Exception {
		this.mvc.perform(get("/api/version").header(HttpHeaders.ORIGIN, appConfig.getCorsUrl())).andExpect(status().isOk());
	}

	@Test
	public void swaggerUI() throws Exception {
		this.mvc.perform(get("/swagger-ui.html")).andExpect(status().isOk());
	}

	@Test
	public void h2() throws Exception {
		this.mvc.perform(get("/h2-console")).andExpect(status().isNotFound());
	}

	@Test
	public void login() throws Exception {
		this.mvc.perform(post("/api/login").param("username", "GUEST").param("password", "")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "GUEST")
	public void logout() throws Exception {
		this.mvc.perform(post("/api/logout")).andExpect(status().isOk());
	}

}