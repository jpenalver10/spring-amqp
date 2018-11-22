package com.joak.spring.amqp.service.event;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.joak.spring.amqp.model.event.CreatedEvent;
import com.joak.spring.amqp.model.event.Event;

@Service
public class MassiveEventService {

	private static final int SLEEP_EVERY_MULTIPLE_OF = 1000;
	private static final int MILLIS_TO_SLEEP = 150;

	private boolean running;

	@Inject
	private EventProducer producer;

	public void start() throws InterruptedException {
		this.running = true;
		massiveLaunch();
		//send();
	}

	public void stop() {
		this.running = false;
	}

	private void massiveLaunch() throws InterruptedException {

		long counter = 0;

		while (running) {

			counter++;
			send();
			sleep(counter);
		}
	}

	private void sleep(long counter) throws InterruptedException {

		if (counter % SLEEP_EVERY_MULTIPLE_OF == 0) {

			Thread.sleep(MILLIS_TO_SLEEP);
		}
	}

	private void send() {

		Event event = new CreatedEvent();

		producer.send(event);
	}

}
