package io.github.tiagodesouza.dogaodelivery.service;

import io.github.tiagodesouza.dogaodelivery.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Order order);

    List<Order> findAllOrders(int page, int size);

    Optional<Order> findOrderById(Long id);

    void delete(Long id);
}
