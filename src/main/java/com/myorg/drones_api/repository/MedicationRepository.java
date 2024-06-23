package com.myorg.drones_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myorg.drones_api.entity.Medication;

import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
	Optional<Medication> findByCode(String code);
}
