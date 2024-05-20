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

import com.BarberShop.DTOs.Scheduling.SchedulingRequestDTO;
import com.BarberShop.DTOs.Scheduling.SchedulingResponseDTO;
import com.BarberShop.Services.SchedulingService;

@RestController
@RequestMapping("scheduling")
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<SchedulingResponseDTO> getAllSchedulings() {
        return schedulingService.getAllSchedulings();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<SchedulingResponseDTO> getSchedulingById(@PathVariable UUID id) {
        return schedulingService.getSchedulingById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<?> saveScheduling(@RequestBody SchedulingRequestDTO data) {
        return schedulingService.saveScheduling(data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateScheduling(@PathVariable UUID id, @RequestBody SchedulingRequestDTO data) {
        return schedulingService.updateScheduling(id, data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScheduling(@PathVariable UUID id) {
        return schedulingService.deleteScheduling(id);
    }
}
