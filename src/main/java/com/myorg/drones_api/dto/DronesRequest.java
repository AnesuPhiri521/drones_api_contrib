package com.myorg.drones_api.dto;

import com.myorg.drones_api.utils.DroneState;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class DronesRequest {

	@NotBlank(message = "Serial number should not be blank")
	@NotNull(message = "Serial number should not be null")
	@Size(min = 2, max = 100, message = "Serial number length should be greator than 2 and not exceed 100 characters")
	@Column(unique = true)
	private String serialNumber;
	
	@NotBlank(message = "Model should not be blank")
	@NotNull(message = "Model should not be null")
	@Pattern(regexp="^(Lightweight|Middleweight|Cruiserweight|Heavyweight)$",message="Invalid model")
	private String model;
	
	@Min(value=50, message="Weight must be equal or greater than 50")  
    @Max(value=500, message="Weight must be equal or less than 500")  
	private int weight;
	
	@Min(value=1, message="Battrey percent must be equal or greater than 1")  
    @Max(value=100, message="Weight must be equal or less than 100") 
	private int battreyPercent;
	
	@NotBlank(message = "Model should not be blank")
	@NotNull(message = "Model should not be null")
	@Pattern(regexp="^(IDLE|LOADING|LOADED|DELIVERING|DELIVERED|RETURNING)$",message="Invalid state")
	private DroneState state;
}
