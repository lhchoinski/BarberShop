package com.BarberShop.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.BarberShop.DTOs.Scheduling.SchedulingResponseDTO;
import com.BarberShop.Repositories.SchedulingRepository;

@RestController
@RequestMapping("scheduling")
public class SchedulingController {
    
     @Autowired
    private SchedulingRepository repository;

    // GetAll Employees
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<SchedulingResponseDTO> GetAll() {
        List<SchedulingResponseDTO> schedulingList = repository.findAll().stream()
                .map(SchedulingResponseDTO::new)
                .collect(Collectors.toList());

        return schedulingList;
    }

}
