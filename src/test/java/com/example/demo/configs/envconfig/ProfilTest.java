package com.example.demo.configs.envconfig;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class ProfilTest extends ProfilAll {

	@Test
	public void isDEV() {
		assertThat(envConfig.isDEV()).isEqualTo(false);
	}

	@Test
	public void swaggerUI() {
		this.webClient.get().uri("/swagger-ui.html").exchange().expectStatus().isForbidden();
	}

	@Test
	public void h2() {
		this.webClient.get().uri("/h2-console.html").exchange().expectStatus().isForbidden();
	}

}