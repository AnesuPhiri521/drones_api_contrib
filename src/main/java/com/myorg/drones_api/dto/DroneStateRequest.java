package com.myorg.drones_api.dto;

import com.myorg.drones_api.utils.DroneState;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class DroneStateRequest {
	
	@NotBlank(message = "Serial number should not be blank")
	@NotNull(message = "Serial number should not be null")
	@Size(min = 2, max = 100, message = "Serial number length should be greator than 2 and not exceed 100 characters")
	private String serialNumber;
	
	@NotBlank(message = "Model should not be blank")
	@NotNull(message = "Model should not be null")
	@Pattern(regexp="^(IDLE|LOADING|LOADED|DELIVERING|DELIVERED|RETURNING)$",message="Invalid state")
	private DroneState state;
}
