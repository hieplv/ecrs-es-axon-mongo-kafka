package com.example.demo.controller;

import com.example.demo.command.Command;
import com.example.demo.command.CommandDispatcher;
import com.example.demo.entity.request.Customer;
import com.example.demo.repository.mongo.EventStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    CommandDispatcher commandDispatcher;

    @RequestMapping(value = "/api/customer/add", method = RequestMethod.POST)
    public Customer addCustomer(@RequestBody Customer customer) {
        commandDispatcher.notifyObservers(new Command() {
        });
        return null;
    }
}
