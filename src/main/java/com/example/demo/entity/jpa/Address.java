package com.example.demo.entity.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String zipcode;
    private String address1;
    private String address2;
    private String address3;
    private String address4;

    @OneToOne(mappedBy = "address")
    private Customer user;
}
