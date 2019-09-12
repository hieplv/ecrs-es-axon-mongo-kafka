package com.example.demo.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String zipcode;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
}
