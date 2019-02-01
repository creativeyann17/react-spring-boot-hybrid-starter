package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class JsonParser {

	@Autowired
	private Gson gson;

	public String toJson(Object o) {
		return this.gson.toJson(o);
	}

	public <T> T fromJson(String json, Class<T> c) {
		return this.gson.fromJson(json, c);
	}

}
