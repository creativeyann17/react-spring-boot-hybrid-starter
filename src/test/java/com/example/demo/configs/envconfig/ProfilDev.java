package com.example.demo.configs.envconfig;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.configs.EnvConfig;

@ActiveProfiles(EnvConfig.DEV)
public class ProfilDev extends ProfilAll {

	@Test
	public void isDEV() {
		assertThat(envConfig.isDEV()).isEqualTo(true);
	}

	@Test
	public void swaggerUI() {
		this.webClient.get().uri("/swagger-ui.html").exchange().expectStatus().isOk();
	}

	@Test
	public void h2() {
		this.webClient.get().uri("/h2-console.html").exchange().expectStatus().isNotFound(); // maybe because it's a test, should be isOk()
	}

}