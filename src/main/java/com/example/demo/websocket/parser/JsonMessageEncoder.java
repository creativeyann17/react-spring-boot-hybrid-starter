package com.example.demo.websocket.parser;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.example.demo.configs.BeanConfig;
import com.example.demo.utils.JsonParser;
import com.example.demo.websocket.messages.utils.AbstractWsMessage;

public class JsonMessageEncoder implements Encoder.Text<AbstractWsMessage> {

	private JsonParser jsonParser;

	public JsonMessageEncoder() {
		this.jsonParser = BeanConfig.getBean(JsonParser.class);
	}

	@Override
	public String encode(AbstractWsMessage message) throws EncodeException {
		return jsonParser.toJson(message);
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
