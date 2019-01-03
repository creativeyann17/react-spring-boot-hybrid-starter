package com.example.demo.websocket;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.CsrfAbstractIntegrationTest;

public class APIWebSocketTests extends CsrfAbstractIntegrationTest {

	@Autowired
	private APIWebSocket socket;

	@Test
	public void onOpen() throws Exception {
		socket.onOpen(null);
	}
}
