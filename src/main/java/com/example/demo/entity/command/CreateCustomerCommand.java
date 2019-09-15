package com.example.demo.entity.command;

import com.example.demo.entity.request.Customer;
import com.example.demo.service.KafkaProducerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerCommand {
    @TargetAggregateIdentifier
    private String customerId;

    KafkaProducerService kafkaProducerService;

    private Customer customer;
}
