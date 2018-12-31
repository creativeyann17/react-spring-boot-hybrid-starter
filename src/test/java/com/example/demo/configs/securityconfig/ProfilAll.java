package com.example.demo.configs.securityconfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demo.configs.AppConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProfilAll {

	@Autowired
	protected AppConfig appConfig;

	@Autowired
	protected WebTestClient webClient;

	@Test
	public void publicUrls() {
		appConfig.getPublicUrls().stream()
				.forEach(url -> webClient.get().uri(url).header(HttpHeaders.ORIGIN, appConfig.getCorsUrl()).exchange().expectStatus().isOk());
	}

}