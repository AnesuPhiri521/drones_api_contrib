package com.myorg.drones_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myorg.drones_api.entity.Load;

public interface LoadRepository extends JpaRepository<Load, Integer> {
	List<Load> findByDroneSerialNumberAndDelivered(String serialNum, Boolean del);
}
