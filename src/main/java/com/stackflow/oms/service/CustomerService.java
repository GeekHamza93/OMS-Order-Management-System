package com.stackflow.oms.service;

import com.stackflow.oms.domain.Customer;
import com.stackflow.oms.repository.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;

@ApplicationScoped
public class CustomerService {

    @Inject CustomerRepository customers;

    @Transactional
    public Customer create(Customer c) {
        if (customers.emailExists(c.email)) {
            throw new ValidationException("Email already exists");
        }
        customers.persist(c);
        return c;
    }

    @Transactional
    public Customer update(Long id, Customer changes) {
        Customer c = customers.findById(id);
        if (c == null) return null;

        if (!c.email.equals(changes.email) && customers.emailExists(changes.email)) {
            throw new ValidationException("Email already exists");
        }
        c.firstName = changes.firstName;
        c.lastName  = changes.lastName;
        c.email     = changes.email;
        return c;
    }
}
