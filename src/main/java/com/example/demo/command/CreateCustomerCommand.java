package com.example.demo.command;

import com.example.demo.entity.request.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerCommand {
    private UUID customerId;
    private Customer customer;
}
