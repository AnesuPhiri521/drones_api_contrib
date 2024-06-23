package com.myorg.drones_api.service;

import java.util.List;

import com.myorg.drones_api.utils.DroneState;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.myorg.drones_api.dto.DroneStateRequest;
import com.myorg.drones_api.dto.DronesRequest;
import com.myorg.drones_api.entity.Drone;
import com.myorg.drones_api.entity.DronesBattery;
import com.myorg.drones_api.entity.Load;
import com.myorg.drones_api.execption.DroneNotFoundException;
import com.myorg.drones_api.repository.BatreyRepository;
import com.myorg.drones_api.repository.DroneRepository;
import com.myorg.drones_api.repository.LoadRepository;

import static com.myorg.drones_api.utils.DroneState.LOADED;
import static com.myorg.drones_api.utils.DroneState.LOADING;

@Service
@RequiredArgsConstructor
public class DroneService {
	private final DroneRepository droneRepository;
	private final LoadRepository loadRepository;
	private final BatreyRepository batteryRepository;
	
	public Drone saveDrone(DronesRequest droneRequest) {
		Drone drone = Drone.builder()
				.serialNumber(droneRequest.getSerialNumber())
				.model(droneRequest.getModel())
				.weight(droneRequest.getWeight())
				.battreyPercent(droneRequest.getBattreyPercent())
				.state(droneRequest.getState())
				.build();
		return droneRepository.save(drone);
	}
	
	public List<Drone> getAllDrones() {
		return droneRepository.findAll();
	}
	
	public List<Drone> getAvailable() {
		return droneRepository.findByState(DroneState.IDLE);
	}
	
	public Drone getSingleDrone(int id) throws DroneNotFoundException {
		return droneRepository.findById(id).orElseThrow(
				()-> new DroneNotFoundException("Drone with id: "+id+" is not found in system."));
	}
	
	public Drone updateDrone(Drone droneRequest) {
		Drone drone = new Drone(droneRequest.getId(),droneRequest.getSerialNumber(),droneRequest.getModel(),droneRequest.getWeight(),droneRequest.getBattreyPercent(),droneRequest.getState() );
		return droneRepository.save(drone);
	}
	
	public String getBatteryLevel(String droneSerial) throws DroneNotFoundException {
		Drone drone = droneRepository.findBySerialNumber(droneSerial).orElseThrow(
				()-> new DroneNotFoundException("Drone with serial id: "+droneSerial+" is not found in system.")
		);
		return "Drone with serial id: "+ droneSerial +" battery percent is: "+drone.getBattreyPercent();
	}
	
	public Drone saveState(DroneStateRequest droneRequest) throws DroneNotFoundException {
		Drone drone = droneRepository.findBySerialNumber(droneRequest.getSerialNumber()).orElseThrow(
				()->new DroneNotFoundException("Drone with serial number: "+droneRequest.getSerialNumber()+" is not found in system.")
		);

		int currentBatteryPercent = drone.getBattreyPercent();
			
		//If user (dispatch controller) set drone to IDLE, everything will be reseted
        switch (droneRequest.getState()) {
            case LOADED:
                if (drone.getState().equals(LOADING)) {
                    drone.setState(LOADED);
                    return droneRepository.save(drone);
                } else if (drone.getState().equals(LOADED)) {
                    throw new DroneNotFoundException("Drone state is already on " + droneRequest.getState() + " state");
                } else {
                    throw new DroneNotFoundException("Drone state can not be shifted to loaded from " + drone.getState() + " state");
                }
            case DELIVERING:
                if (drone.getState().equals(LOADED)) {
                    drone.setState(DroneState.DELIVERING);
                    //Each time a drone is delivering(going to deliver), batrey percent is reduced by 10 %
                    double usedPercent = 0.1 * currentBatteryPercent;
                    double newPercentDouble = currentBatteryPercent - usedPercent;
                    int newPercentInt = (int) (newPercentDouble);
                    if (newPercentInt < 0) {
                        newPercentInt = 0;
                    }
                    drone.setBattreyPercent(newPercentInt);

                    return droneRepository.save(drone);
                } else if (drone.getState().equals(DroneState.DELIVERING)) {
                    throw new DroneNotFoundException("Drone state is already on " + droneRequest.getState() + " state");
                } else {
                    throw new DroneNotFoundException("Drone state can not be shifted to loaded from " + drone.getState() + " state");
                }
            case DELIVERED:
                if (drone.getState().equals(DroneState.DELIVERING)) {
                    drone.setState(DroneState.DELIVERED);
                    List<Load> loads = loadRepository.findByDroneSerialNumberAndDelivered(droneRequest.getSerialNumber(), false);
                    //Change state of all drugs to delivered
                    loads.forEach((ld) -> {
                        ld.setDelivered(true);
                        loadRepository.save(ld);
                    });

                    return droneRepository.save(drone);
                } else if (drone.getState().equals(DroneState.DELIVERED)) {
                    throw new DroneNotFoundException("Drone state is already on " + droneRequest.getState() + " state");
                } else {
                    throw new DroneNotFoundException("Drone state can not be shifted to loaded from " + drone.getState() + " state");
                }
            case RETURNING:
                if (drone.getState().equals(DroneState.DELIVERED)) {
                    drone.setState(DroneState.RETURNING);
                    //Each time a drone is returning (coming back from delivery), batrey percent is reduced by 10 %
                    double usedPercent = 0.1 * currentBatteryPercent;
                    double newPercentDouble = currentBatteryPercent - usedPercent;
                    int newPercentInt = (int) (newPercentDouble);
                    if (newPercentInt < 0) {
                        newPercentInt = 0;
                    }
                    drone.setBattreyPercent(newPercentInt);
                    return droneRepository.save(drone);
                } else if (drone.getState().equals(DroneState.RETURNING)) {
                    throw new DroneNotFoundException("Drone state is already on " + droneRequest.getState() + " state");
                } else {
                    throw new DroneNotFoundException("Drone state can not be shifted to loaded from " + drone.getState() + " state");
                }
            case LOADING:
                throw new DroneNotFoundException("Drone state can not be shifted to loading when there is no load inside");
			default:
				drone.setState(DroneState.IDLE);
				loadRepository.deleteByDroneSerialNumberAndDelivered(droneRequest.getSerialNumber(), false);
				return droneRepository.save(drone);
        }
	}
	
	@Scheduled(fixedRate = 5000)
	public void checkBatteryLevels() {
    	//Change state of all drugs to delivered
		droneRepository.findAll().forEach(dr -> {
            DronesBattery droneBattery = new DronesBattery();
            droneBattery.setSerialNumber(dr.getSerialNumber());
            droneBattery.setBatteryPercent(dr.getBattreyPercent());
            batteryRepository.save(droneBattery);
        });
	}
	
	public List<DronesBattery> getAllBattery() {
		return batteryRepository.findAll();
	}
	
}
