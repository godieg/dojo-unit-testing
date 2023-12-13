package com.dojo.unittest.exercises.exc2.adapter;

import com.dojo.unittest.exercises.exc2.domain.model.Customer;

import java.util.Map;
import java.util.Optional;

public class CustomerGateway {

    private static final Map<String, Customer> customers = Map.of(
      "1", Customer.builder().id("1").name("Customer 1").build(),
      "2", Customer.builder().id("2").name("Customer 2").build(),
      "3", Customer.builder().id("3").name("Customer 3").build()
    );

    public Customer exists(String id) {
        return Optional
                .ofNullable(customers.get(id))
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

}
