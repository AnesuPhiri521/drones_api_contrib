package com.myorg.drones_api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myorg.drones_api.dto.LoadRequest;
import com.myorg.drones_api.entity.Load;
import com.myorg.drones_api.execption.LoadingExeption;
import com.myorg.drones_api.service.LoadService;

@RestController
@RequestMapping("/load")
public class LoadController {

	@Autowired
	private LoadService loadService;
	
	@PostMapping("/save")
	public ResponseEntity<Load>saveLoad(@RequestBody @Valid LoadRequest loadRequest) throws LoadingExeption{
		return new ResponseEntity<>(loadService.saveLoad(loadRequest), HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchall")
	public ResponseEntity<List<Load>>getAllLoads() {
		return new ResponseEntity<>(loadService.getAllLoads(), HttpStatus.CREATED);
	}
}
