package com.BarberShop.Entities;

import java.util.UUID;
import com.BarberShop.DTOs.Customer.CustomerRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "customers")
@Entity(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID id;

    private String name;

    private String phone;

    private String email;

    public Customer(CustomerRequestDTO data) {

        this.name = data.name();
        this.phone = data.phone();
        this.email = data.email();
    }

}
