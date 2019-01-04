package com.example.demo.websocket;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.socket.server.standard.ServerEndpointRegistration;

public class APIWebSocketConfig extends ServerEndpointRegistration.Configurator {

	@Override
	public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
		MultiValueMap<String, String> cookies = getCookiesFromRequest(request);
		config.getUserProperties().put("JSESSIONID", cookies.get("JSESSIONID").get(0));
	}

	private MultiValueMap<String, String> getCookiesFromRequest(HandshakeRequest request) {
		MultiValueMap<String, String> cookies = new LinkedMultiValueMap<>();
		for (String cookie : request.getHeaders().get("cookie")) {
			String[] keyValue = cookie.split("=");
			cookies.add(keyValue[0], keyValue[1]);
		}
		return cookies;
	}

}
