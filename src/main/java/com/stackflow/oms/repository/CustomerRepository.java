package com.stackflow.oms.repository;

import com.stackflow.oms.domain.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    public boolean emailExists(String email) {
        return count("email", email) > 0;
    }
}
