package com.stackflow.oms.api.mapper;

import com.stackflow.oms.api.dto.OrderDTO;
import com.stackflow.oms.domain.Order;

public class OrderMapper {
    public static OrderDTO toDTO(Order o) {
        return new OrderDTO(o.id, o.customerId, o.status, o.amount, o.createdAt);
    }
    public static Order toEntity(OrderDTO d) {
        Order o = new Order();
        o.id = d.id();
        o.customerId = d.customerId();
        o.status = d.status();
        o.amount = d.amount();
        o.createdAt = d.createdAt(); // peut rester null → sera Instant.now() côté entity si tu l’as mis par défaut
        return o;
    }
}