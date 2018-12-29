package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class APIController {
	@GetMapping("/version")
	public String getVersion() {
		return "{ \"version\": \"0.0.1\" }";
	}
}
