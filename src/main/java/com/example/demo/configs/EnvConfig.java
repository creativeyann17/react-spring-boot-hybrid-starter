package com.example.demo.configs;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class EnvConfig {

	public static final String DEV = "dev";

	@Autowired
	private Environment environment;

	public boolean isDEV() {
		return isEnv(DEV);
	}

	private boolean isEnv(String profil) {
		return Optional.ofNullable(environment.getActiveProfiles()).map(envs -> Arrays.asList(envs).contains(profil)).orElse(false);
	}

}
