package com.example.demo.utils;

import com.example.demo.configs.BeanConfig;
import com.google.gson.Gson;

public class JsonParser {

	private Gson gson;

	public JsonParser() {
		this.gson = BeanConfig.getBean(Gson.class);
	}

	public String toJson(Object o) {
		return this.gson.toJson(o);
	}

	public <T> T fromJson(String json, Class<T> c) {
		return this.gson.fromJson(json, c);
	}

}
