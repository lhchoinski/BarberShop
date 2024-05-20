package com.BarberShop.Entities;

import java.sql.Date;
import java.util.UUID;

import com.BarberShop.DTOs.Scheduling.SchedulingRequestDTO;

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
@Table(name = "schedulings")
@Entity(name = "schedulings")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID id;

    private Employee employeeId;

    private Customer customerId;

    private Date date;

    public Scheduling(SchedulingRequestDTO data) {

        this.employeeId = data.employeeId();
        this.customerId = data.customerId();
        this.date = data.date();
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
