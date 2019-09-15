package com.example.demo.service;

import com.example.demo.entity.command.AddCustomerAddressCommand;
import com.example.demo.entity.command.CreateCustomerCommand;
import com.example.demo.entity.event.CustmerCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.kafka.clients.producer.Producer;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class KafkaConsumerService {
    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private CommandGateway commandGateway;

    @KafkaListener(topics = "customers", groupId = "demo_group")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        ObjectMapper objectMapper = new ObjectMapper();
        CustmerCreatedEvent cstCreEvt = objectMapper.readValue(message, CustmerCreatedEvent.class);
        commandGateway.send(new AddCustomerAddressCommand(UUID.randomUUID().toString(), cstCreEvt.getCustomer()));
    }
}
