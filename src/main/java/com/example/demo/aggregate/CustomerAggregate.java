package com.example.demo.aggregate;

import com.example.demo.command.CreateCustomerCommand;
import com.example.demo.event.CustmerCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Aggregate
public class CustomerAggregate {

    private static final Logger logger = LoggerFactory.getLogger(CustomerAggregate.class);

    @AggregateIdentifier
    private UUID customerId;

    public CustomerAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public void createCommandHandler(CreateCustomerCommand command) {
        AggregateLifecycle.apply(new CustmerCreatedEvent(command.getCustomerId(), command.getCustomer()));
    }

    @EventSourcingHandler
    public void on(CustmerCreatedEvent event) {
//        foodCartId = event.getCustomerId();
//        selectedProducts = new HashMap<>();
//        confirmed = false;
    }
}
