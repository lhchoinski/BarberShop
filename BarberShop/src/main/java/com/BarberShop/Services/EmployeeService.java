package com.BarberShop.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.BarberShop.DTOs.Employee.EmployeeRequestDTO;
import com.BarberShop.DTOs.Employee.EmployeeResponseDTO;
import com.BarberShop.Entities.Employee;
import com.BarberShop.Repositories.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeResponseDTO::new).collect(Collectors.toList());
    }

    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(UUID id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.map(employee -> ResponseEntity.ok(new EmployeeResponseDTO(employee)))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> saveEmployee(EmployeeRequestDTO data) {
        Employee employee = new Employee(data);
        employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> updateEmployee(UUID id, EmployeeRequestDTO data) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.map(employee -> {
            employee.setName(data.name());
            employee.setCpf(data.cpf());
            employee.setEmail(data.email());
            employeeRepository.save(employee);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> deleteEmployee(UUID id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
