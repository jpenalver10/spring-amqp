package com.joak.spring.amqp.service.event;

import org.springframework.stereotype.Service;

import com.joak.spring.amqp.webmodel.event.EventRequest;

@Service
public class EventService {

	public void create(EventRequest request) {
		// TODO persist at some datasource
		System.out.println(request);
	}

}
