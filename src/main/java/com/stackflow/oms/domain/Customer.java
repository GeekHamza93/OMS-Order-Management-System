package com.stackflow.oms.domain;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity @Table(name="customers")
public class Customer extends PanacheEntity {
    @NotBlank @Size(max=80)  @Column(name="first_name", nullable=false) public String firstName;
    @NotBlank @Size(max=80)  @Column(name="last_name",  nullable=false) public String lastName;
    @NotBlank @Email @Size(max=180) @Column(name="email", nullable=false, unique=true) public String email;
}