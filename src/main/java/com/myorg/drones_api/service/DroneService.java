package com.myorg.drones_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myorg.drones_api.dto.DroneStateRequest;
import com.myorg.drones_api.dto.DronesRequest;
import com.myorg.drones_api.entity.Drone;
import com.myorg.drones_api.execption.DroneNotFoundException;
import com.myorg.drones_api.repository.DroneRepository;
import com.myorg.drones_api.repository.LoadRepository;

@Service
public class DroneService {
	
	@Autowired
	private DroneRepository droneRepo;
	
	@Autowired
	private LoadRepository loadRepo;
	
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
	
	public String getBattreyLevel(String droneSerial) throws DroneNotFoundException {
		Drone drone = droneRepo.findBySerialNumber(droneSerial);
		
		if (drone!=null) {
			return "Drone with serial id: "+ droneSerial +" battrey percent is: "+drone.getBattreyPercent();
		}else {
			throw new DroneNotFoundException("Drone with serila id: "+droneSerial+" is not found in system.");
		}
	}
	
	public Drone saveState(DroneStateRequest droneRequest) throws DroneNotFoundException {
		Drone drone = droneRepo.findBySerialNumber(droneRequest.getSerialNumber());
		
		if (drone!=null) {
			int currentBatreyPercent = drone.getBattreyPercent();
			
			//If user (dispatch controller) set drone to IDLE, everything will be reseted
			if(droneRequest.getState().equals("IDLE")) {
				drone.setState("IDLE");
				loadRepo.deleteByDroneSerialNumberAndDelivered(droneRequest.getSerialNumber(), false);
				return droneRepo.save(drone);
			}
			
			//Using if statements. Lets check the previous state first to avoid skipping states
			if (droneRequest.getState().equals("LOADED")) {
				if (drone.getState().equals("LOADING")) {
					drone.setState("LOADED");
					return droneRepo.save(drone);
				}else if (drone.getState().equals("LOADED")) {
					throw new DroneNotFoundException("Drone state is already on "+droneRequest.getState()+" state");
				}else {
					throw new DroneNotFoundException("Drone state can not be shifted to loaded from "+drone.getState()+" state");
				}
			}else if(droneRequest.getState().equals("DELIVERING")) {
                if (drone.getState().equals("LOADED")) {
                	drone.setState("DELIVERING");
                	//Each time a drone is delivering(going to deliver), batrey percent is reduced by 10 %
                	double usedPercent = 0.1 * currentBatreyPercent;
                	double newPercentDouble = currentBatreyPercent - usedPercent;
                	int newPercentInt = (int) (newPercentDouble);
                	if (newPercentInt<0) {
                		newPercentInt = 0;
                	}
                	drone.setBattreyPercent(newPercentInt);
                	
					return droneRepo.save(drone);
				}else if (drone.getState().equals("DELIVERING")) {
					throw new DroneNotFoundException("Drone state is already on "+droneRequest.getState()+" state");
				}else {
					throw new DroneNotFoundException("Drone state can not be shifted to loaded from "+drone.getState()+" state");
				}
			}else if(droneRequest.getState().equals("DELIVERED")) {
                if (drone.getState().equals("DELIVERING")) {
                	drone.setState("DELIVERED");
					return droneRepo.save(drone);
				}else if (drone.getState().equals("DELIVERED")) {
					throw new DroneNotFoundException("Drone state is already on "+droneRequest.getState()+" state");
				}else {
					throw new DroneNotFoundException("Drone state can not be shifted to loaded from "+drone.getState()+" state");
				}
			}else if(droneRequest.getState().equals("RETURNING")) {
                if (drone.getState().equals("DELIVERED")) {
                	drone.setState("RETURNING");
                	//Each time a drone is returning (coming back from delivery), batrey percent is reduced by 10 %
                	double usedPercent = 0.1 * currentBatreyPercent;
                	double newPercentDouble = currentBatreyPercent - usedPercent;
                	int newPercentInt = (int) (newPercentDouble);
                	if (newPercentInt<0) {
                		newPercentInt = 0;
                	}
                	drone.setBattreyPercent(newPercentInt);
					return droneRepo.save(drone);
				}else if (drone.getState().equals("RETURNING")) {
					throw new DroneNotFoundException("Drone state is already on "+droneRequest.getState()+" state");
				}else {
					throw new DroneNotFoundException("Drone state can not be shifted to loaded from "+drone.getState()+" state");
				}
			}else if(droneRequest.getState().equals("LOADING")) {
               throw new DroneNotFoundException("Drone state can not be shifted to loading when there is no load inside");
			}
			drone = droneRepo.findBySerialNumber(droneRequest.getSerialNumber());
			return drone;
		}else {
			throw new DroneNotFoundException("Drone with serila number: "+droneRequest.getSerialNumber()+" is not found in system.");
		}
	}
	
}
