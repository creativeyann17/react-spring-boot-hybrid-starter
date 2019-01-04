package com.example.demo.websocket.parser;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.example.demo.websocket.messages.utils.AbstractWsMessage;
import com.google.gson.Gson;

public class JsonMessageEncoder implements Encoder.Text<AbstractWsMessage> {

	private static Gson gson = new Gson();

	@Override
	public String encode(AbstractWsMessage message) throws EncodeException {
		return gson.toJson(message);
	}

	@Override
	public void init(EndpointConfig endpointConfig) {
		// Custom initialization logic
	}

	@Override
	public void destroy() {
		// Close resources
	}
}
