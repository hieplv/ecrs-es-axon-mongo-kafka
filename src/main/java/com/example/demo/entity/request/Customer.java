package com.example.demo.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String firstName;
    private String lastName;
    private int age;

    private Address address;
}
