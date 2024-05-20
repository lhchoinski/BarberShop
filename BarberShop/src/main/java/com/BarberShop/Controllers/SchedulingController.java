package com.BarberShop.Controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;
import java.util.Optional;

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

import com.BarberShop.DTOs.Scheduling.SchedulingRequestDTO;
import com.BarberShop.DTOs.Scheduling.SchedulingResponseDTO;
import com.BarberShop.Entities.Scheduling;
import com.BarberShop.Repositories.SchedulingRepository;

@RestController
@RequestMapping("scheduling")
public class SchedulingController {

    @Autowired
    private SchedulingRepository repository;

    // GetAll Schedulings
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<SchedulingResponseDTO> GetAll() {
        List<SchedulingResponseDTO> schedulingList = repository.findAll().stream()
                .map(SchedulingResponseDTO::new)
                .collect(Collectors.toList());

        return schedulingList;
    }

    // Get Scheduling By ID
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<SchedulingResponseDTO> getSchedulingById(@PathVariable UUID id) {
        Optional<Scheduling> optionalScheduling = repository.findById(id);

        if (optionalScheduling.isPresent()) {
            Scheduling scheduling = optionalScheduling.get();
            SchedulingResponseDTO schedulingResponseDTO = new SchedulingResponseDTO(scheduling);
            return ResponseEntity.ok(schedulingResponseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create Scheduling
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void SaveScheduling(@RequestBody SchedulingRequestDTO data) {

        Scheduling schedulingData = new Scheduling(data);
        repository.save(schedulingData);
        return;
    }

    // Update Scheduling
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateScheduling(@PathVariable UUID id, @RequestBody SchedulingRequestDTO data) {
        Optional<Scheduling> optionalScheduling = repository.findById(id);

        if (optionalScheduling.isPresent()) {
            Scheduling scheduling = optionalScheduling.get();

            scheduling.setEmployee(data.employee());
            scheduling.setCustomer(data.customer());
            scheduling.setDate(data.date());

            repository.save(scheduling);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Scheduling
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScheduling(@PathVariable UUID id) {
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
