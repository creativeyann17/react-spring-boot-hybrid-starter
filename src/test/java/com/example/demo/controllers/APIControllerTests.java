package com.example.demo.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class APIControllerTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void getVersion() {
		this.webClient.get().uri("/api/version").exchange().expectStatus().isOk().expectBody(String.class).isEqualTo(APIController.VERSION);
	}

}
