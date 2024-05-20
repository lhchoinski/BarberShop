package com.BarberShop.DTOs.Scheduling;

import java.sql.Date;

import com.BarberShop.Entities.Customer;
import com.BarberShop.Entities.Employee;

public record SchedulingRequestDTO(
    Employee employeeId, 
    Customer customerId, 
    Date date
) {}
