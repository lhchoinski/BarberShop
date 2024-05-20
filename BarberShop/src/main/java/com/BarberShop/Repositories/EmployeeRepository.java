package com.BarberShop.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BarberShop.Entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {}
