package com.example.demo.websocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.websocket.messages.OnlineUsersMessage;
import com.example.demo.websocket.messages.utils.AbstractWsMessage;

public class APIWebSocketManager {

	private final Logger log = LoggerFactory.getLogger(APIWebSocketManager.class);
	private final Set<APIWebSocket> sockets = new CopyOnWriteArraySet<>();

	public void add(APIWebSocket socket) {
		this.sockets.add(socket);
		this.broadcastOnlineUsersCount();
	}

	public void remove(APIWebSocket socket) {
		this.sockets.remove(socket);
		this.broadcastOnlineUsersCount();
	}

	private void broadcastOnlineUsersCount() {
		broadcast(new OnlineUsersMessage(sockets.size()));
	}

	private void broadcast(AbstractWsMessage message) {
		for (APIWebSocket socket : sockets) {
			synchronized (socket) {
				try {
					socket.getSession().getBasicRemote().sendObject(message);
				} catch (Exception e) {
					log.error("Fail to broadcast message type {} to {} reason {}", message.getType(), socket.getSession().getId(), e.getMessage());
				}
			}
		}
	}

}
