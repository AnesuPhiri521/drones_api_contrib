package com.myorg.drones_api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.myorg.drones_api.entity.Load;

public interface LoadRepository extends JpaRepository<Load, Integer> {
	List<Load> findByDroneSerialNumberAndDelivered(String serialNum, Boolean del);
	
	@Transactional
	@Modifying
	void deleteByDroneSerialNumberAndDelivered(String field1, Boolean field2);
}
