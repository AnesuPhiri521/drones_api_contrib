package com.myorg.drones_api.repository;

import java.util.List;
import java.util.Optional;

import com.myorg.drones_api.utils.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;


import com.myorg.drones_api.entity.Drone;

import javax.swing.text.html.Option;

public interface DroneRepository extends JpaRepository<Drone, Integer> {
	List<Drone> findByState(DroneState state);
	
	 Optional<Drone> findBySerialNumber(String serialNumber);
}
