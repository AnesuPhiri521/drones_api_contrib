package com.myorg.drones_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myorg.drones_api.dto.DronesRequest;
import com.myorg.drones_api.entity.Drone;
import com.myorg.drones_api.execption.DroneNotFoundException;
import com.myorg.drones_api.repository.DroneRepository;

@Service
public class DroneService {
	
	@Autowired
	private DroneRepository droneRepo;
	
	public Drone saveDrone(DronesRequest droneRequest) {
		Drone drone = new Drone(0,droneRequest.getSerialNumber(),droneRequest.getModel(),droneRequest.getWeight(),droneRequest.getBattreyPercent(),droneRequest.getState() );
		return droneRepo.save(drone);
	}
	
	public List<Drone> getAllDrones() {
		return droneRepo.findAll();
	}
	
	public List<Drone> getAvailable() {
		return droneRepo.findByState("IDLE");
	}
	
	public Drone getSingleDrone(int id) throws DroneNotFoundException {
		Drone drone = droneRepo.findById(id);
		
		if (drone!=null) {
			return drone;
		}else {
			throw new DroneNotFoundException("Drone with id: "+id+" is not found in system.");
		}
	}
	
	public Drone updateDrone(Drone droneRequest) {
		Drone drone = new Drone(droneRequest.getId(),droneRequest.getSerialNumber(),droneRequest.getModel(),droneRequest.getWeight(),droneRequest.getBattreyPercent(),droneRequest.getState() );
		return droneRepo.save(drone);
	}
	
}
