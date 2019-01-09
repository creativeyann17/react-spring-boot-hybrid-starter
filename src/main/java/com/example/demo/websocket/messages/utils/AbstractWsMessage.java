package com.example.demo.websocket.messages.utils;

import com.example.demo.configs.BeanConfig;
import com.example.demo.utils.JsonParser;

import lombok.Data;

@Data
public abstract class AbstractWsMessage {

	protected WsMessageType type;

	public AbstractWsMessage(WsMessageType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		JsonParser jsonParser = BeanConfig.getBean(JsonParser.class);
		return jsonParser.toJson(this);
	}

}
