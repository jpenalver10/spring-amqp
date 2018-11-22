package com.joak.spring.amqp.config;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joak.spring.amqp.service.event.EventProducer;


@Configuration
public class EventsQueuesConfig {

	public static final String EVENTS_EXCHANGE_NAME = "events.echange";
	public static final String EVENTS_QUEUE_NAME = "events.queue";
	public static final String DEAD_LETTER_EVENTS_QUEUE_NAME = "events.queue.dlx";

	@Value("${joak.spring.rabbitmq.consumers}")
	private int consumers;

	@Value("${joak.spring.rabbitmq.max-consumers}")
	private int maxConsumers;

	@Inject
	private ConnectionFactory connectionFactory;

	@Inject
	private AmqpAdmin amqpAdmin;

	@Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {

		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);

        factory.setConcurrentConsumers(consumers);
        factory.setMaxConcurrentConsumers(maxConsumers);

        //factory.setPrefetchCount(100);
        factory.setDefaultRequeueRejected(false);

        return factory;
    }

	@PostConstruct
	private void initQueues() {

		Queue queue = QueueBuilder.durable(EVENTS_QUEUE_NAME)
				.withArgument("x-dead-letter-exchange", EVENTS_EXCHANGE_NAME)
                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_EVENTS_QUEUE_NAME)
                .build();
		Exchange exchange = ExchangeBuilder.directExchange(EVENTS_EXCHANGE_NAME).build();
	    Binding binding = new Binding(EVENTS_QUEUE_NAME, Binding.DestinationType.QUEUE, EVENTS_EXCHANGE_NAME, "", null);

	    amqpAdmin.declareQueue(queue);
	    amqpAdmin.declareExchange(exchange);
	    amqpAdmin.declareBinding(binding);

	    Queue deadLetterQueue = QueueBuilder.durable(DEAD_LETTER_EVENTS_QUEUE_NAME)
	    		.withArgument("x-message-ttl", 10000)
	    		.build();
	    Binding deadLetterBinding = new Binding(DEAD_LETTER_EVENTS_QUEUE_NAME, Binding.DestinationType.QUEUE,
	    		EVENTS_EXCHANGE_NAME, DEAD_LETTER_EVENTS_QUEUE_NAME, null);
	    amqpAdmin.declareQueue(deadLetterQueue);
	    amqpAdmin.declareBinding(deadLetterBinding);

	}

	/*@Bean
	public EventConsumer eventConsumer() {
		return new EventConsumer();
	}*/

	@Bean
	public EventProducer eventProducer() {
		return new EventProducer();
	}

}
