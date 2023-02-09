package com.myorg.drones_api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class DroneStateRequest {
	
	@NotBlank(message = "Serial number should not be blank")
	@NotNull(message = "Serial number should not be null")
	@Size(min = 2, max = 100, message = "Serial number length should be greator than 2 and not exceed 100 characters")
	private String serialNumber;
	
	@NotBlank(message = "Model should not be blank")
	@NotNull(message = "Model should not be null")
	@Pattern(regexp="^(IDLE|LOADING|LOADED|DELIVERING|DELIVERED|RETURNING)$",message="Invalid state")
	private String state;
	

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public DroneStateRequest(String serialNumber, String state) {
		super();
		this.serialNumber = serialNumber;
		this.state = state;
	}

	public DroneStateRequest() {
		super();
	}

	@Override
	public String toString() {
		return "DroneState [serialNumber=" + serialNumber + ", state=" + state + "]";
	}
	
	
	
	
}
