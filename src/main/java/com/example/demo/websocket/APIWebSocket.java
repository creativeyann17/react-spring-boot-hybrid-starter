package com.example.demo.websocket;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.websocket.messages.OnlineUsersMessage;
import com.example.demo.websocket.messages.utils.AbstractWsMessage;
import com.example.demo.websocket.parser.JsonMessageDecoder;
import com.example.demo.websocket.parser.JsonMessageEncoder;

@ServerEndpoint(value = "/api/ws", decoders = JsonMessageDecoder.class, encoders = JsonMessageEncoder.class, configurator = APIWebSocketConfig.class)
public class APIWebSocket {

	private static final Logger log = LoggerFactory.getLogger(APIWebSocket.class);
	private static Set<APIWebSocket> apiWebSockets = new CopyOnWriteArraySet<>();

	private Session session;

	@OnOpen
	public void onOpen(Session session) throws IOException {
		this.session = session;
		apiWebSockets.add(this);
		broadcast(new OnlineUsersMessage(apiWebSockets.size()));
	}

	@OnMessage
	public void onMessage(Session session, AbstractWsMessage message) throws IOException {

	}

	@OnClose
	public void onClose(Session session) throws IOException {
		apiWebSockets.remove(this);
		broadcast(new OnlineUsersMessage(apiWebSockets.size()));
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		log.error(throwable.getMessage());
	}

	private static void broadcast(AbstractWsMessage message) {
		for (APIWebSocket socket : apiWebSockets) {
			synchronized (socket) {
				try {
					socket.session.getBasicRemote().sendObject(message);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}
	}

}
