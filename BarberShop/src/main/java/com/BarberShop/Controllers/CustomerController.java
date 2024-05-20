package com.BarberShop.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

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
import com.BarberShop.Services.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // GetAll Customers
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<CustomerResponseDTO> GetAll() {
        return customerService.getAllCustomers();
    }

    // Get Customer By ID
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable UUID id) {
        return customerService.getCustomerById(id);
    }

    // Create Customers
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<?> SaveCustomer(@RequestBody CustomerRequestDTO data) {
        return customerService.saveCustomer(data);
    }

    // Update Customer
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable UUID id, @RequestBody CustomerRequestDTO data) {
        return customerService.updateCustomer(id, data);
    }

    // Delete Customer
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable UUID id) {
        return customerService.deleteCustomer(id);
    }
}
