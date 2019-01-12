package com.example.demo.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.configs.BeanConfig;
import com.example.demo.websocket.messages.utils.AbstractWsMessage;
import com.example.demo.websocket.parser.JsonMessageDecoder;
import com.example.demo.websocket.parser.JsonMessageEncoder;

@ServerEndpoint(value = "/api/ws", decoders = JsonMessageDecoder.class, encoders = JsonMessageEncoder.class, configurator = APIWebSocketConfig.class)
public class APIWebSocket {

	private static final Logger log = LoggerFactory.getLogger(APIWebSocket.class);

	@Autowired
	private APIWebSocketManager manager;
	private Session session;

	public APIWebSocket() {
		this.manager = BeanConfig.getBean(APIWebSocketManager.class);
	}

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		manager.add(this);
	}

	@OnMessage
	public void onMessage(Session session, AbstractWsMessage message) {
		log.debug("Session: {} message: {}", session.getUserProperties().get("JSESSIONID"), message);
	}

	@OnClose
	public void onClose(Session session) {
		manager.remove(this);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		log.error(throwable.getMessage());
	}

	public Session getSession() {
		return this.session;
	}

	public APIWebSocketManager getManager() {
		return this.manager;
	}

}
