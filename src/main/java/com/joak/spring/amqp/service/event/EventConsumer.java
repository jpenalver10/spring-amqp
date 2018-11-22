package com.joak.spring.amqp.service.event;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.joak.spring.amqp.config.EventsQueuesConfig;
import com.joak.spring.amqp.model.event.Event;

@Service
public class EventConsumer {

	@RabbitListener(queues = EventsQueuesConfig.EVENTS_QUEUE_NAME)
	public void consume(Event event) {

		//System.out.println("received event: " + event.getId());

		// for test dead letter queue configuration
		//throw new RuntimeException();
	}

}
