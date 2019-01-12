package com.example.demo.websocket;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.websocket.messages.OnlineUsersMessage;
import com.example.demo.websocket.messages.utils.AbstractWsMessage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class APIWebSocketTest {

	@Autowired
	private APIWebSocket socket;

	@MockBean
	private Session session;

	@MockBean
	private Basic basic;

	@Before
	public void setup() {
		Mockito.when(session.getBasicRemote()).thenReturn(basic);
	}

	@Test
	public void managerIsWired() {
		assertThat(socket.getManager()).isNotNull();
	}

	@Test
	public void onOpen() throws Exception {
		socket.onOpen(session);
		assertThat(socket.getSession()).isEqualTo(session);
		Mockito.verify(basic).sendObject(Mockito.any(AbstractWsMessage.class));
	}

	@Test
	public void onClose() throws Exception {
		socket.onClose(session);
	}

	@Test
	public void onMessage() {
		OnlineUsersMessage msg = new OnlineUsersMessage(1);
		socket.onMessage(session, msg);
	}

	@Test
	public void onError() {
		socket.onError(session, new Exception());
	}

	@Test
	public void sendMessageException() throws Exception {
		Mockito.doThrow(new IOException("foo")).when(basic).sendObject(Mockito.any(AbstractWsMessage.class));
		socket.onOpen(session);
	}
}
