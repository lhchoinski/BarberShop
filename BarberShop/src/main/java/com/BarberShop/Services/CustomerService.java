package com.BarberShop.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.BarberShop.DTOs.Customer.CustomerRequestDTO;
import com.BarberShop.DTOs.Customer.CustomerResponseDTO;
import com.BarberShop.Entities.Customer;
import com.BarberShop.Repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // List Customer
    public List<CustomerResponseDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(CustomerResponseDTO::new).collect(Collectors.toList());
    }

    // List Customer ByID
    public ResponseEntity<CustomerResponseDTO> getCustomerById(UUID id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.map(customer -> ResponseEntity.ok(new CustomerResponseDTO(customer)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create and Validate Customer
    public ResponseEntity<?> saveCustomer(CustomerRequestDTO data) {

        if (!isValidEmail(data.email())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail inválido");
        }

        Customer customer = new Customer(data);
        customerRepository.save(customer);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> updateCustomer(UUID id, CustomerRequestDTO data) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.map(customer -> {
            customer.setName(data.name());
            customer.setPhone(data.phone());
            customer.setEmail(data.email());
            customerRepository.save(customer);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> deleteCustomer(UUID id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Email Validate
    private boolean isValidEmail(String email) {

        return email.contains("@");
    }

}
