package com.BarberShop.DTOs.Employee;

import java.util.UUID;

import com.BarberShop.Entities.Employee;

public record EmployeeResponseDTO(UUID id, String name, String cpf, String email) {

    public EmployeeResponseDTO(Employee employee) {

        this(
                employee.getId(),
                employee.getName(),
                employee.getCpf(),
                employee.getEmail());
    }
}
