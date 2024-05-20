package com.BarberShop.DTOs.Customer;

import java.util.UUID;

import com.BarberShop.Entities.Customer;

public record CustomerResponseDTO(UUID id,String name, String phone, String email) {
    
    public CustomerResponseDTO(Customer customer){
        this(
            customer.getId(),
            customer.getName(), 
            customer.getPhone(), 
            customer.getEmail()
            );

    }
}
