package com.myorg.drones_api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblLoad")
public class Load {
	@Id
	@GeneratedValue
	private int id;
	private String droneSerialNumber;
	private String medicineCode;
	private int medicineWeight;
	private boolean delivered = false;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getMedicineWeight() {
		return medicineWeight;
	}
	public void setMedicineWeight(int medicineWeight) {
		this.medicineWeight = medicineWeight;
	}
	public boolean isDelivered() {
		return delivered;
	}
	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}
	
	public Load(int id, String droneSerialNumber, String medicineCode, int medicineWeight, boolean delivered) {
		super();
		this.id = id;
		this.droneSerialNumber = droneSerialNumber;
		this.medicineCode = medicineCode;
		this.medicineWeight = medicineWeight;
		this.delivered = delivered;
	}
	public Load() {
		super();
	}
	
	@Override
	public String toString() {
		return "Load [id=" + id + ", droneSerialNumber=" + droneSerialNumber + ", medicineCode=" + medicineCode
				+ ", medicineWeight=" + medicineWeight + ", delivered=" + delivered + "]";
	} 
	
	
	
	
}
