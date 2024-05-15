package com.BarberShop.Entities;

import java.util.UUID;
import com.BarberShop.DTOs.Employee.EmployeeRequestDTO;
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
@Table(name = "employees")
@Entity(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID id;

    private String name;

    private String cpf;

    private String email;

    public Employee(EmployeeRequestDTO data){

        this.name = data.name();
        this.cpf = data.cpf();
        this.email = data.email();
    }
}
