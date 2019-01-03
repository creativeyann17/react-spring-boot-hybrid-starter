package com.example.demo.websocket;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.example.demo.websocket.messages.AbstractWebSocketMessage;
import com.google.gson.Gson;

public class APIWebSocketMessageDecoder implements Decoder.Text<AbstractWebSocketMessage> {

	private static Gson gson = new Gson();

	@Override
	public AbstractWebSocketMessage decode(String s) throws DecodeException {
		return gson.fromJson(s, AbstractWebSocketMessage.class);
	}

	@Override
	public boolean willDecode(String s) {
		return (s != null);
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
