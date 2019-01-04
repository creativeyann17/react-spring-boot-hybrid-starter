package com.example.demo.websocket.messages.utils;

import lombok.Data;

@Data
public abstract class AbstractWsMessage {

	protected WsMessageType type;

	public AbstractWsMessage(WsMessageType type) {
		this.type = type;
	}

}
