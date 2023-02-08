package com.myorg.drones_api.execption;

@SuppressWarnings("serial")
public class DroneNotFoundException  extends Exception {

	public DroneNotFoundException(String message) {
		super(message);
	}

}
