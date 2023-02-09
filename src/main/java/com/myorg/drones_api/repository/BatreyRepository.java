package com.myorg.drones_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myorg.drones_api.entity.DronesBattrey;

public interface BatreyRepository extends JpaRepository<DronesBattrey, Integer> {

}
