package com.example.demo.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.example.demo.websocket.messages.AbstractWebSocketMessage;
import com.google.gson.Gson;

public class APIWebSocketMessageEncoder implements Encoder.Text<AbstractWebSocketMessage> {

	private static Gson gson = new Gson();

	@Override
	public String encode(AbstractWebSocketMessage message) throws EncodeException {
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
