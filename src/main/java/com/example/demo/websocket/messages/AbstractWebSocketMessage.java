package com.example.demo.websocket.messages;

import lombok.Data;

@Data
public abstract class AbstractWebSocketMessage {

	protected SocketMessageType type;

	public AbstractWebSocketMessage(SocketMessageType type) {
		this.type = type;
	}

}
