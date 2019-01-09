package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${app.api-base-url}")
public class APIController {

	@GetMapping("/version")
	public Version getVersion() {
		return new Version();
	}

	public static class Version {
		public String version = "0.0.1";
	}

}
