package com.example.demo.websocket.parser;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.example.demo.configs.BeanConfig;
import com.example.demo.utils.JsonParser;
import com.example.demo.websocket.messages.utils.AbstractWsMessage;

public class JsonMessageDecoder implements Decoder.Text<AbstractWsMessage> {

	private JsonParser jsonParser;

	public JsonMessageDecoder() {
		this.jsonParser = BeanConfig.getBean(JsonParser.class);
	}

	@Override
	public AbstractWsMessage decode(String s) throws DecodeException {
		return jsonParser.fromJson(s, AbstractWsMessage.class);
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
