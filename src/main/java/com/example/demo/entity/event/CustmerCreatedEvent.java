package com.example.demo.entity.event;

import com.example.demo.entity.request.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustmerCreatedEvent {
    private String customerId;
    private Customer customer;
}
