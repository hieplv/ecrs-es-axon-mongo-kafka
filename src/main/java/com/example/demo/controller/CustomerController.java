package com.example.demo.controller;

import com.example.demo.entity.command.CreateCustomerCommand;
import com.example.demo.entity.request.Customer;
import com.example.demo.service.KafkaProducerService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CustomerController {
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private QueryGateway queryGateway;

    @Autowired
    KafkaProducerService kafkaProducerService;

    @RequestMapping(value = "/api/customer/add", method = RequestMethod.POST)
    public Customer addCustomer(@RequestBody Customer customer) {
        commandGateway.send(new CreateCustomerCommand(UUID.randomUUID().toString(), kafkaProducerService, customer));
        return null;
    }
}
