package com.stackflow.oms.api.mapper;

import com.stackflow.oms.api.dto.CustomerDTO;
import com.stackflow.oms.domain.Customer;

public class CustomerMapper {
    public static CustomerDTO toDTO(Customer c) {
        return new CustomerDTO(c.id, c.firstName, c.lastName, c.email);
    }
    public static Customer toEntity(CustomerDTO d) {
        Customer c = new Customer();
        c.id = d.id();
        c.firstName = d.firstName();
        c.lastName = d.lastName();
        c.email = d.email();
        return c;
    }
}