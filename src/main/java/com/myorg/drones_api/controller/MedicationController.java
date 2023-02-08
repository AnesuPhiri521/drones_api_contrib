package com.myorg.drones_api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myorg.drones_api.dto.MedicationRequest;
import com.myorg.drones_api.entity.Medication;
import com.myorg.drones_api.execption.MedicationNotFoundExeption;
import com.myorg.drones_api.service.MedicationService;

@RestController
@RequestMapping("/medication")
public class MedicationController {
	
	@Autowired
	private MedicationService medicationService;
	
	@PostMapping("/save")
	public ResponseEntity<Medication>saveMedication(@RequestBody @Valid MedicationRequest medicationRequest) {
		return new ResponseEntity<>(medicationService.saveMedication(medicationRequest), HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Medication>updateMedication(@RequestBody @Valid Medication medicationRequest) {
		return new ResponseEntity<>(medicationService.updateMedication(medicationRequest), HttpStatus.OK);
	}
	
	@GetMapping("/fetchall")
	public ResponseEntity<List<Medication>>getAllMedications() {
		return new ResponseEntity<>(medicationService.getAllMedications(), HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchsingle/{id}")
	public ResponseEntity<Medication> getSingleMedication(@PathVariable  int id) throws MedicationNotFoundExeption {
		return ResponseEntity.ok(medicationService.getSingleMedication(id));
	}
}
