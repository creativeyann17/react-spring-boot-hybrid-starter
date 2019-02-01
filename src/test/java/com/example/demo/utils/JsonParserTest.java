package com.example.demo.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.websocket.messages.OnlineUsersMessage;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { JsonParser.class, Gson.class })
public class JsonParserTest {

	private static final OnlineUsersMessage msg = new OnlineUsersMessage(1);
	private static final String msgJson = "{\"count\":1,\"type\":\"ONLINE_USERS_COUNT\"}";

	@Autowired
	private JsonParser jsonParser;

	@Test
	public void instanceIsNotNull() {
		assertThat(jsonParser).isNotNull();
	}

	@Test
	public void toJson() {
		OnlineUsersMessage msg = new OnlineUsersMessage(1);
		assertThat(jsonParser.toJson(msg)).isEqualTo(msgJson);
	}

	@Test
	public void fromJson() {
		assertThat(jsonParser.fromJson(msgJson, OnlineUsersMessage.class).getCount()).isEqualTo(msg.getCount());
	}

}
