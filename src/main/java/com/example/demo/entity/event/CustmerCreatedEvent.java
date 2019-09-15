package com.example.demo.entity.event;

import com.example.demo.entity.request.Customer;
import com.example.demo.service.KafkaProducerService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustmerCreatedEvent {
    private String customerId;

    @JsonIgnore
    KafkaProducerService kafkaProducerService;

    private Customer customer;
}
