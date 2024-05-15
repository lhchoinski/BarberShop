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

import com.BarberShop.DTOs.Customer.CustomerRequestDTO;
import com.BarberShop.DTOs.Customer.CustomerResponseDTO;
import com.BarberShop.DTOs.Employee.EmployeeRequestDTO;
import com.BarberShop.Entities.Customer;
import com.BarberShop.Entities.Employee;
import com.BarberShop.Repositories.CustomerRepository;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    // GetAll Customers
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<CustomerResponseDTO> GetAll() {
        List<CustomerResponseDTO> customerList = repository.findAll().stream()
                .map(CustomerResponseDTO::new)
                .collect(Collectors.toList());

        return customerList;
    }

    // Get Customer By ID
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable UUID id) {
        Optional<Customer> optionalCustomer = repository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO(customer);
            return ResponseEntity.ok(customerResponseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create Customers
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void SaveCustomer(@RequestBody CustomerRequestDTO data) {

        Customer customerData = new Customer(data);
        repository.save(customerData);
        return;
    }

    // Update Customer
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable UUID id, @RequestBody CustomerRequestDTO data) {
        Optional<Customer> optionalCustomer = repository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            customer.setName(data.name());
            customer.setPhone(data.phone());
            customer.setEmail(data.email());

            repository.save(customer);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Customer
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable UUID id) {
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
