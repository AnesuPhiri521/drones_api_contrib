package com.myorg.drones_api.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class MedicationRequest {

	@NotBlank(message = "Medication name should not be blank")
	@NotNull(message = "Medication name should not be null")
	@Pattern(regexp = "^[a-zA-Z0-9_-]{6,50}$", message = "Medication name  must be of 6 to 50 length with numbers, letters, underscore and dash (-) only")
    private String name;
	
	@Min(value=10, message="Weight must be equal or greater than 10 gr")  
    @Max(value=500, message="Weight must be equal or less than 500 gr") 
	private int weight;
	
	
	@NotBlank(message = "Medication code should not be blank")
	@NotNull(message = "Medication code should not be null")
	@Pattern(regexp = "^[A-Z0-9_]{6,10}$", message = "Medication code  must be of 6 to 10 length with numbers, capital letters and underscore only")
	@Column(unique = true)
	private String code;
	
	@NotBlank(message = "Medication image should not be blank")
	@NotNull(message = "Medication image should not be null")
	private String image;
}
