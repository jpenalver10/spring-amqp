package com.joak.spring.amqp.model.event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.joak.spring.amqp.domain.event.EventType;

public abstract class Event implements Serializable {

	private static final long serialVersionUID = -201547753031454352L;

	private String id = UUID.randomUUID().toString();

	private EventType type;

	private LocalDateTime ocurredOn = LocalDateTime.now();

	private String event;

	protected Event() {
	}

	protected Event(EventType type, String event) {
		this.id = UUID.randomUUID().toString();
		this.type = type;
		this.ocurredOn = LocalDateTime.now();
		this.event = event;
	}

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
		return "Event [id=" + id + ", type=" + type + ", ocurredOn=" + ocurredOn + ", event=" + event + "]";
	}

}
