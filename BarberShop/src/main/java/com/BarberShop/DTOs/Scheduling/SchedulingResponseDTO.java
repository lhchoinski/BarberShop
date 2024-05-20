package com.BarberShop.DTOs.Scheduling;

import java.sql.Date;
import java.util.UUID;

import com.BarberShop.Entities.Customer;
import com.BarberShop.Entities.Employee;
import com.BarberShop.Entities.Scheduling;

public record SchedulingResponseDTO(UUID id,Employee employee,Customer customer,Date date) {
    
    public SchedulingResponseDTO(Scheduling scheduling){

        this(
            scheduling.getId(),
            scheduling.getEmployee(),
            scheduling.getCustomer(),
            scheduling.getDate()
            );
    }
}

