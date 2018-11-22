package com.joak.spring.amqp.model.event;

import com.joak.spring.amqp.domain.event.EventType;

public class CreatedEvent extends Event {

	private static final long serialVersionUID = -1327193903340855177L;

	public CreatedEvent() {
		this(null);
	}

	public CreatedEvent(String event) {
		super(EventType.CREATE, event);
	}

}
