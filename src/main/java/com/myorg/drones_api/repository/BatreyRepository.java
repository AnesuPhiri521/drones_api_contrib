package com.myorg.drones_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myorg.drones_api.entity.DronesBattery;

public interface BatreyRepository extends JpaRepository<DronesBattery, Integer> {

}
