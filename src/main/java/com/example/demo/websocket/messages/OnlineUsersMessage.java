package com.example.demo.websocket.messages;

import com.example.demo.websocket.messages.utils.AbstractWsMessage;
import com.example.demo.websocket.messages.utils.WsMessageType;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class OnlineUsersMessage extends AbstractWsMessage {

	private int count;

	public OnlineUsersMessage(int count) {
		super(WsMessageType.ONLINE_USERS_COUNT);
		this.count = count;
	}

}
