package com.example.demo.configs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AppConfigTest {

	@Autowired
	private AppConfig appConfig;

	@Test
	public void configurationIsLoaded() {
		assertThat(appConfig.getApiBaseUrl()).isEqualTo("/api");
	}

	@Test
	public void publicUrls() {
		assertThat(appConfig.getPublicUrls().size()).isEqualTo(2);
	}

	@Test
	public void guest() {
		assertThat(appConfig.getGuest()).isNotNull();
		assertThat(appConfig.getGuest().getUsername()).isNotBlank();
		assertThat(appConfig.getGuest().getPassword()).isNotBlank();
	}

}
