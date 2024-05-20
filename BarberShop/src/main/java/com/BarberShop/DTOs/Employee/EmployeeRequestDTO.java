package com.BarberShop.DTOs.Employee;

public record EmployeeRequestDTO(
        String name,
        String cpf,
        String email) {
}
