package com.example.demo.configs.securityconfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.CsrfAbstractIntegrationTest;
import com.example.demo.configs.AppConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProfilAll extends CsrfAbstractIntegrationTest {

	@Autowired
	protected AppConfig appConfig;

	@Test
	public void publicUrls() throws Exception {
		for (String url : appConfig.getPublicUrls()) {
			mvc.perform(get(url).header(HttpHeaders.ORIGIN, appConfig.getCorsUrl())).andExpect(status().isOk());
		}
	}

}
