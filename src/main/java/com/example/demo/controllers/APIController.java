package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${app.api-base-url}")
public class APIController {

	public static final String VERSION = "{ \"version\": \"0.0.1\" }";

	@GetMapping("/version")
	public String getVersion() {
		return VERSION;
	}

}
