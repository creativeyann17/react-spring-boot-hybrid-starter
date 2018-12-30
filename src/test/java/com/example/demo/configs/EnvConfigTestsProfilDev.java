package com.example.demo.configs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(EnvConfig.DEV)
public class EnvConfigTestsProfilDev {

	@Autowired
	private EnvConfig envConfig;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private WebTestClient webClient;

	@Test
	public void isDEV() {
		assertThat(envConfig.isDEV()).isEqualTo(true);
	}

	@Test
	public void swaggerUI() {
		this.webClient.get().uri("/swagger-ui.html").exchange().expectStatus().isOk();
	}

	@Test
	public void cors() {
		this.webClient.get().uri("/api/version").header(HttpHeaders.ORIGIN, appConfig.getCorsUrl()).exchange().expectStatus().isOk();
	}

}