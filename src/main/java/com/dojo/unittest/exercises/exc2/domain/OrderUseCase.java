package com.dojo.unittest.exercises.exc2.domain;

import com.dojo.unittest.exercises.exc2.adapter.CustomerGateway;
import com.dojo.unittest.exercises.exc2.adapter.OrderRepository;
import com.dojo.unittest.exercises.exc2.adapter.UserGateway;
import com.dojo.unittest.exercises.exc2.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@RequiredArgsConstructor
public class OrderUseCase {

    private final OrderRepository orderRepository;
    private final UserGateway userGateway;
    private final CustomerGateway customerGateway;

    public Order create(Order order) {

        if (Objects.isNull(order)) {
            throw new IllegalArgumentException("Invalid Order");
        }

        var user = order.getCreatedBy();
        var customer = order.getCustomer();

        if (Objects.isNull(user)
                || StringUtils.isEmpty(user.getEmail())
                || !userGateway.exists(user.getEmail())) {
            throw new IllegalArgumentException("Invalid User");
        }

        if (Objects.isNull(customer)
                || StringUtils.isEmpty(customer.getId())) {
            throw new IllegalArgumentException("Invalid Customer");
        }

        var customerValid = customerGateway.exists(customer.getId());

        if (!Objects.isNull(customerValid)) {
            return orderRepository.create(order.toBuilder().customer(customerValid).build());
        } else {
            throw new RuntimeException("The order could not create");
        }
    }


}
