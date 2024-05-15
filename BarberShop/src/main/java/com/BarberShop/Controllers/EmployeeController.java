package com.BarberShop.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.BarberShop.DTOs.Employee.EmployeeRequestDTO;
import com.BarberShop.DTOs.Employee.EmployeeResponseDTO;
import com.BarberShop.Entities.Employee;
import com.BarberShop.Repositories.EmployeeRepository;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    // GetAll Employees
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<EmployeeResponseDTO> GetAll() {
        List<EmployeeResponseDTO> employeeList = repository.findAll().stream()
                .map(EmployeeResponseDTO::new)
                .collect(Collectors.toList());

        return employeeList;
    }

    // Get Employee By ID
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable UUID id) {
        Optional<Employee> optionalEmployee = repository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO(employee);
            return ResponseEntity.ok(employeeResponseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create Employees
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void SaveEmployee(@RequestBody EmployeeRequestDTO data) {

        Employee employeeData = new Employee(data);
        repository.save(employeeData);
        return ;
    }

    // Update Employee
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable UUID id, @RequestBody EmployeeRequestDTO data) {
        Optional<Employee> optionalEmployee = repository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();

            employee.setName(data.name());
            employee.setCpf(data.cpf());
            employee.setEmail(data.email());

            repository.save(employee);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Employee
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable UUID id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar o customer: " + e.getMessage());
        }
    }

}
