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

import com.myorg.drones_api.dto.DronesRequest;
import com.myorg.drones_api.entity.Drone;
import com.myorg.drones_api.execption.DroneNotFoundException;
import com.myorg.drones_api.service.DroneService;

@RestController
@RequestMapping("/drones")
public class DroneController {
	
	@Autowired
	private DroneService droneservice;
	
	@PostMapping("/save")
	public ResponseEntity<Drone>saveDrone(@RequestBody @Valid DronesRequest droneRequest) {
		return new ResponseEntity<>(droneservice.saveDrone(droneRequest), HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Drone>updateDrone(@RequestBody @Valid Drone droneRequest) {
		return new ResponseEntity<>(droneservice.updateDrone(droneRequest), HttpStatus.OK);
	}
	
	@GetMapping("/fetchall")
	public ResponseEntity<List<Drone>>getAllDrones() {
		return new ResponseEntity<>(droneservice.getAllDrones(), HttpStatus.OK);
	}
	
	@GetMapping("/fetchsingle/{id}")
	public ResponseEntity<Drone> getSingleDrone(@PathVariable  int id) throws DroneNotFoundException {
		return ResponseEntity.ok(droneservice.getSingleDrone(id));
	}
	
	@GetMapping("/fetchavailable")
	public ResponseEntity<List<Drone>>getAvailable() {
		return new ResponseEntity<>(droneservice.getAvailable(), HttpStatus.OK);
	}

	
}
