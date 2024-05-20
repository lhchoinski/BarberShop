package com.BarberShop.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BarberShop.Entities.Scheduling;

public interface SchedulingRepository extends JpaRepository<Scheduling, UUID> {
}
