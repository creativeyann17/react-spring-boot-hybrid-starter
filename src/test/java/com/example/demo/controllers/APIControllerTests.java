package com.example.demo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.example.demo.CsrfAbstractIntegrationTest;

public class APIControllerTests extends CsrfAbstractIntegrationTest {

	@Test
	public void getVersionWithUser() throws Exception {
		mvc.perform(get("/api/version").with(userGuest())).andExpect(status().isOk());
	}

	@Test
	public void getVersionWithoutUser() throws Exception {
		mvc.perform(get("/api/version")).andExpect(status().isUnauthorized());
	}
}
