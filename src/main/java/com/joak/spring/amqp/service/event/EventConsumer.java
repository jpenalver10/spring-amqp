package com.joak.spring.amqp.service.event;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.joak.spring.amqp.config.EventsQueuesConfig;
import com.joak.spring.amqp.model.event.Event;

@Service
public class EventConsumer {

	@RabbitListener(queues = EventsQueuesConfig.EVENTS_QUEUE_NAME)
    public void consume(Event event,
            @Header(required = false, name = "x-death") Map<String, String> xDeath) {

        System.out.println("received event: " + event.getId());

		// for test dead letter queue configuration
        throw new RuntimeException();
	}

}
