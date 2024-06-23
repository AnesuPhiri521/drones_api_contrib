package com.myorg.drones_api.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.myorg.drones_api.dto.MedicationRequest;
import com.myorg.drones_api.entity.Medication;
import com.myorg.drones_api.execption.MedicationNotFoundExeption;
import com.myorg.drones_api.repository.MedicationRepository;

@Service
@RequiredArgsConstructor
public class MedicationService {
	private final MedicationRepository medicationRepo;
	
	public Medication saveMedication(MedicationRequest medicationRequest) {
		Medication medication = new Medication(0,medicationRequest.getName(),medicationRequest.getWeight(),medicationRequest.getCode(),medicationRequest.getImage());
		return medicationRepo.save(medication);
	}
	
	public List<Medication> getAllMedications() {
		return medicationRepo.findAll();
	}
	
	public Medication getSingleMedication(int id) throws MedicationNotFoundExeption {
		return medicationRepo.findById(id).orElseThrow(
				()-> new MedicationNotFoundExeption("Medication with id: "+id+" is not found in system.")
		);
	}
	
	public Medication updateMedication(Medication medicationRequest) {
		Medication medication = new Medication(medicationRequest.getId(),medicationRequest.getName(),medicationRequest.getWeight(),medicationRequest.getCode(),medicationRequest.getImage() );
		return medicationRepo.save(medication);
	}
}
