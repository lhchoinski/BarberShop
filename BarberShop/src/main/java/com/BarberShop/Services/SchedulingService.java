package com.BarberShop.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.BarberShop.DTOs.Scheduling.SchedulingRequestDTO;
import com.BarberShop.DTOs.Scheduling.SchedulingResponseDTO;
import com.BarberShop.Entities.Scheduling;
import com.BarberShop.Repositories.SchedulingRepository;

@Service
public class SchedulingService {

    @Autowired
    private SchedulingRepository schedulingRepository;

    public List<SchedulingResponseDTO> getAllSchedulings() {
        List<Scheduling> schedulings = schedulingRepository.findAll();
        return schedulings.stream()
                .map(SchedulingResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ResponseEntity<SchedulingResponseDTO> getSchedulingById(UUID id) {
        Optional<Scheduling> optionalScheduling = schedulingRepository.findById(id);
        return optionalScheduling.map(scheduling -> ResponseEntity.ok(new SchedulingResponseDTO(scheduling)))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> saveScheduling(SchedulingRequestDTO data) {
        try {
            Scheduling scheduling = new Scheduling(data);
            schedulingRepository.save(scheduling);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar o agendamento: " + e.getMessage());
        }
    }

    public ResponseEntity<?> updateScheduling(UUID id, SchedulingRequestDTO data) {
        Optional<Scheduling> optionalScheduling = schedulingRepository.findById(id);
        if (optionalScheduling.isPresent()) {
            Scheduling scheduling = optionalScheduling.get();
            scheduling.setEmployee(data.employee());
            scheduling.setCustomer(data.customer());
            scheduling.setDate(data.date());
            schedulingRepository.save(scheduling);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> deleteScheduling(UUID id) {
        try {
            if (schedulingRepository.existsById(id)) {
                schedulingRepository.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar o agendamento: " + e.getMessage());
        }
    }
}
