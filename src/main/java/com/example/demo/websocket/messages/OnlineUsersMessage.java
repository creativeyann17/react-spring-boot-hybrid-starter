package com.example.demo.websocket.messages;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OnlineUsersMessage extends AbstractWebSocketMessage {

	private int count;

	public OnlineUsersMessage(int count) {
		super(SocketMessageType.ONLINE_USERS_COUNT);
		this.count = count;
	}

}
