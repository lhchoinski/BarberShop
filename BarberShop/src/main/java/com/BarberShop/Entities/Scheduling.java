package com.BarberShop.Entities;

import java.sql.Date;
import java.util.UUID;

import com.BarberShop.DTOs.Scheduling.SchedulingRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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

    @OneToOne
    private Employee employee;

    @OneToOne
    private Customer customer;

    private Date date;

    public Scheduling(SchedulingRequestDTO data) {

        this.employee = data.employee();
        this.customer = data.customer();
        this.date = data.date();
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
