package com.myorg.drones_api.execption;

@SuppressWarnings("serial")
public class MedicationNotFoundExeption extends Exception {

	public MedicationNotFoundExeption(String message) {
		super(message);
	}
	
}
