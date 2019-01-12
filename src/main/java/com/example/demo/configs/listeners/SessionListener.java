package com.example.demo.configs.listeners;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionListener implements HttpSessionListener {

	private final AtomicInteger activeSessions = new AtomicInteger();

	public int getTotalActiveSession() {
		return activeSessions.get();
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		log.debug("Session created: {}", se.getSession().getId());
		activeSessions.incrementAndGet();
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		log.debug("Session destroyed: {}", se.getSession().getId());
		activeSessions.decrementAndGet();
	}

}
