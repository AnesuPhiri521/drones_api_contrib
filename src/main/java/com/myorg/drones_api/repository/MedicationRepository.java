package com.myorg.drones_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myorg.drones_api.entity.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
	Medication findById(int id);
	
	Medication findByCode(String code);
}
