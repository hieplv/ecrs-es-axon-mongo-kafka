package com.example.demo.event;

import com.example.demo.entity.request.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustmerCreatedEvent {
    private UUID customerId;
    private Customer customer;
}