package com.myorg.drones_api.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.myorg.drones_api.dto.LoadRequest;
import com.myorg.drones_api.entity.Drone;
import com.myorg.drones_api.entity.Load;
import com.myorg.drones_api.entity.Medication;
import com.myorg.drones_api.execption.LoadingExeption;
import com.myorg.drones_api.repository.DroneRepository;
import com.myorg.drones_api.repository.LoadRepository;
import com.myorg.drones_api.repository.MedicationRepository;

import static com.myorg.drones_api.utils.DroneState.IDLE;
import static com.myorg.drones_api.utils.DroneState.LOADING;

@Service
@RequiredArgsConstructor
public class LoadService {
    private final LoadRepository loadRepo;
    private final MedicationRepository medicationRepo;
    private final DroneRepository droneRepo;

    public Load saveLoad(LoadRequest loadRequest) throws LoadingExeption {
        String droneSerial = loadRequest.getDroneSerialNumber();
        String medicineCode = loadRequest.getMedicineCode();
        int medicineWeight = 0;

        //Search for drone using Request Serial Number
        Drone selectedDrone = droneRepo.findBySerialNumber(loadRequest.getDroneSerialNumber()).orElseThrow(
                () -> new LoadingExeption("Drone with serial number: " + droneSerial + " is not found in system.")
        );

        //Search for medicine using Request Code
        Medication selectedMedicine = medicationRepo.findByCode(medicineCode).orElseThrow(
                () -> new LoadingExeption("Medication with code: " + medicineCode + " is not found in system.")
        );
        if (selectedDrone.getState().equals(LOADING) || selectedDrone.getState().equals(IDLE)) {
            //Check mass already loaded versus the mass that we now want to load
            List<Load> alreadyLoaded = loadRepo.findByDroneSerialNumberAndDelivered(droneSerial, false);
            int loadedWeight = 0;
            //Loop and get so far loaded weight
            for (Load oneLoad : alreadyLoaded) {
                loadedWeight = loadedWeight + oneLoad.getMedicineWeight();
            }

            //Set weight of the loaded medicine to load record
            medicineWeight = selectedMedicine.getWeight();
            Load load = new Load(0, droneSerial, medicineCode, medicineWeight, false);

            //Check to see if we are not overloading the drone
            if ((loadedWeight + medicineWeight) <= selectedDrone.getWeight()) {
                //Check to see if drone battery is not less than 25%
                if (selectedDrone.getBattreyPercent() < 25) {
                    throw new LoadingExeption("The drone battery percent is now less than 25 % please recharge it first before loading!!!");
                } else {
                    /*
                     * EVERYTHING IS CHECKED AND SAVING CAN NOW BE DONE
                     */
                    //Change state of drone to Loading
                    selectedDrone.setState(LOADING);
                    droneRepo.save(selectedDrone);
                    //Save new load in loads table
                    return loadRepo.save(load);
                }
            } else {
                throw new LoadingExeption("The drone is now full and can not carry more items. So far loaded weight is : " + loadedWeight + " and can not add this " + medicineWeight + " grames");
            }
        } else {
            throw new LoadingExeption("The drone state is on : " + selectedDrone.getState() + " and can not be loaded now.");
        }

    }


    public List<Load> getAllLoads() {
        return loadRepo.findAll();
    }

    public List<Load> getDroneMedicines(String droneSerial) throws LoadingExeption {
        List<Load> loads = loadRepo.findByDroneSerialNumberAndDelivered(droneSerial, false);

        if (loads != null) {
            return loads;
        } else {
            throw new LoadingExeption("There are no any loaded medicines for the specified drone.");
        }
    }
}
