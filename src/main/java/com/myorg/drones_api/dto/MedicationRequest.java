package com.myorg.drones_api.dto;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public MedicationRequest() {
		super();
	}
	
	public MedicationRequest(String name, int weight, String code, String image) {
		super();
		this.name = name;
		this.weight = weight;
		this.code = code;
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "MedicationRequest [name=" + name + ", weight=" + weight + ", code=" + code + ", image=" + image + "]";
	}
	
	
	
	
	
	
}
