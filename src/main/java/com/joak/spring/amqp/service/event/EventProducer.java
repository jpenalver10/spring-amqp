package com.joak.spring.amqp.service.event;

import javax.inject.Inject;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.joak.spring.amqp.config.EventsQueuesConfig;
import com.joak.spring.amqp.model.event.Event;

//@Service
public class EventProducer {

	@Inject
	private RabbitTemplate rabbit;

	public void send(Event event) {

		rabbit.convertAndSend(EventsQueuesConfig.EVENTS_EXCHANGE_NAME, "", event);
	}

}
