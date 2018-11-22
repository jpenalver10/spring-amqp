package com.joak.spring.amqp.controller.event;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joak.spring.amqp.service.event.MassiveEventService;

@RestController
@RequestMapping(MassiveEventController.URL)
public class MassiveEventController {

	public static final String URL = EventController.URL + "/massive";

	@Inject
	private MassiveEventService service;

	@PostMapping("/start")
	public void start() throws InterruptedException {

		service.start();
	}

	@PostMapping("/stop")
	public void stop() {

		service.stop();
	}

}
