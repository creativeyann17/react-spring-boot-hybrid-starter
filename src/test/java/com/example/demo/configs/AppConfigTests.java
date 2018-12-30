package com.example.demo.configs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppConfigTests {

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

}
