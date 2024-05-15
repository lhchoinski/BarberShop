package com.BarberShop.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BarberShop.Entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {}
