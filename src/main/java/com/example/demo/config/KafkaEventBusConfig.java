package com.example.demo.config;

import org.apache.kafka.clients.producer.Producer;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.kafka.eventhandling.producer.KafkaPublisher;
import org.axonframework.kafka.eventhandling.producer.KafkaPublisherConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaEventBusConfig {
    @Value("${axon.kafka.default-topic}")
    private String topic;

    @Bean
    public org.axonframework.kafka.eventhandling.producer.ProducerFactory<byte[], byte[]> producerFactory(ProducerFactory factory) {
        return new org.axonframework.kafka.eventhandling.producer.ProducerFactory<byte[], byte[]>() {
            @Override
            public Producer<byte[], byte[]> createProducer() {
                return factory.createProducer();
            }

            @Override
            public void shutDown() {
            }
        };
    }

    @Bean("event-bus")
    EventBus eventBus() {
        return new SimpleEventBus();
    }

    @Bean
    KafkaPublisher<byte[], byte[]> kafkaPublisher(org.axonframework.kafka.eventhandling.producer.ProducerFactory factory, EventBus eventBus) {
        KafkaPublisherConfiguration configuration = KafkaPublisherConfiguration.<String, byte[]>builder()
                .withMessageSource(eventBus)
                .withProducerFactory(factory)
                .withTopic(topic)
                .build();
        KafkaPublisher<byte[], byte[]> publisher = new KafkaPublisher<>(configuration);
//        eventBus.subscribe((events) -> publisher.sendEvents(events));
        publisher.start();
        return publisher;
    }
}