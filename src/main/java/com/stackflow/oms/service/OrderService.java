package com.stackflow.oms.service;

import com.stackflow.oms.domain.Order;
import com.stackflow.oms.repository.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OrderService {

    @Inject OrderRepository orders;

    @Transactional
    public Order create(Order o) {
        orders.persist(o);
        return o;
    }

    @Transactional
    public Order update(Long id, Order changes) {
        Order o = orders.findById(id);
        if (o == null) return null;
        o.status = changes.status;
        o.amount = changes.amount;
        return o;
    }
}
