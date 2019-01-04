package com.example.demo.websocket.parser;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.example.demo.websocket.messages.utils.AbstractWsMessage;
import com.google.gson.Gson;

public class JsonMessageDecoder implements Decoder.Text<AbstractWsMessage> {

	private static Gson gson = new Gson();

	@Override
	public AbstractWsMessage decode(String s) throws DecodeException {
		return gson.fromJson(s, AbstractWsMessage.class);
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
