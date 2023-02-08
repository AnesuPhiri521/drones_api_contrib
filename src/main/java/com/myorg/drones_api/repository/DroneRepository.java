package com.myorg.drones_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.myorg.drones_api.entity.Drone;

public interface DroneRepository extends JpaRepository<Drone, Integer> {
	Drone findById(int id);

	List<Drone> findByState(String string);
	
	public Drone findBySerialNumber(String serialNumber);
	
	
}
