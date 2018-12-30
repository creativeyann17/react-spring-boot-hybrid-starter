package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIController {

	@GetMapping("/version")
	public String getVersion() {
		return "{ \"version\": \"0.0.1\" }";
	}

}
