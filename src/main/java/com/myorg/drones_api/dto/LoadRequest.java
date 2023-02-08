package com.myorg.drones_api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoadRequest {
	
	
	@NotBlank(message = "Serial number should not be blank")
	@NotNull(message = "Serial number should not be null")
	@Size(min = 2, max = 100, message = "Serial number length should be greator than 2 and not exceed 100 characters")
	private String droneSerialNumber;
	
	@NotBlank(message = "Medication code should not be blank")
	@NotNull(message = "Medication code should not be null")
	@Pattern(regexp = "^[A-Z0-9_]{6,10}$", message = "Medication code  must be of 6 to 10 length with numbers, capital letters and underscore only")
	private String medicineCode;

	public String getDroneSerialNumber() {
		return droneSerialNumber;
	}

	public void setDroneSerialNumber(String droneSerialNumber) {
		this.droneSerialNumber = droneSerialNumber;
	}

	public String getMedicineCode() {
		return medicineCode;
	}

	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}

	public LoadRequest(
			@NotBlank(message = "Serial number should not be blank") @NotNull(message = "Serial number should not be null") @Size(min = 2, max = 100, message = "Serial number length should be greator than 2 and not exceed 100 characters") String droneSerialNumber,
			@NotBlank(message = "Medication code should not be blank") @NotNull(message = "Medication code should not be null") @Pattern(regexp = "^[A-Z0-9_]{6,10}$", message = "Medication code  must be of 6 to 10 length with numbers, capital letters and underscore only") String medicineCode) {
		super();
		this.droneSerialNumber = droneSerialNumber;
		this.medicineCode = medicineCode;
	}

	public LoadRequest() {
		super();
	}

	@Override
	public String toString() {
		return "LoadRequest [droneSerialNumber=" + droneSerialNumber + ", medicineCode=" + medicineCode + "]";
	}
	
	
	
	
	
}
