package com.joak.spring.amqp.controller.event;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joak.spring.amqp.service.event.EventService;
import com.joak.spring.amqp.webmodel.event.EventRequest;

@RestController
@RequestMapping(EventController.URL)
public class EventController {

	public static final String URL = "/events";

	@Inject
	private EventService service;

	@PostMapping
	public void create(@RequestBody EventRequest request) {

		service.create(request);
	}

}
