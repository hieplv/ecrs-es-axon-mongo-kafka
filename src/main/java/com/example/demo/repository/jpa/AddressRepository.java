package com.example.demo.repository.jpa;

import com.example.demo.entity.jpa.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
