package com.example.demo.aggregate;

import com.example.demo.entity.command.CreateCustomerCommand;
import com.example.demo.entity.event.CustmerCreatedEvent;
import com.example.demo.service.KafkaProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Aggregate
public class CustomerAggregate {

    private static final Logger logger = LoggerFactory.getLogger(CustomerAggregate.class);

    @AggregateIdentifier
    private String customerId;

    public CustomerAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command) {
        AggregateLifecycle.apply(new CustmerCreatedEvent(command.getCustomerId(), command.getKafkaProducerService(), command.getCustomer()));
    }

    @EventSourcingHandler
    public void on(CustmerCreatedEvent event) throws JsonProcessingException {
        customerId = event.getCustomerId();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(event);
        event.getKafkaProducerService().sendMessage(json,"customer");

//        foodCartId = event.getCustomerId();
//        selectedProducts = new HashMap<>();
//        confirmed = false;
    }
}
