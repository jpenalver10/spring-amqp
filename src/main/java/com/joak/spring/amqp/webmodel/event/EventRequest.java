package com.joak.spring.amqp.webmodel.event;

import java.time.LocalDateTime;
import java.util.UUID;

import com.joak.spring.amqp.domain.event.EventType;

public class EventRequest {

	private String id = UUID.randomUUID().toString();

	private EventType type;

	private LocalDateTime ocurredOn = LocalDateTime.now();

	private String event;

	public String getId() {
		return id;
	}

	public EventType getType() {
		return type;
	}

	public LocalDateTime getOcurredOn() {
		return ocurredOn;
	}

	public String getEvent() {
		return event;
	}

	@Override
	public String toString() {
		return "EventRequest [id=" + id + ", type=" + type + ", ocurredOn=" + ocurredOn + ", event=" + event + "]";
	}

}
