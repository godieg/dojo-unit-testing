package com.dojo.unittest.exercises.exc2.adapter;

import com.dojo.unittest.exercises.exc2.domain.model.Order;

import java.util.UUID;

public class OrderRepository {

    public Order create(Order order) {
        return order
                .toBuilder()
                .number(UUID.randomUUID().toString())
                .build();
    }

}
