package com.myorg.drones_api.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblLoad")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Load {
	@Id
	@GeneratedValue
	private int id;
	private String droneSerialNumber;
	private String medicineCode;
	private int medicineWeight;
	private boolean delivered = false;
	
	public Load(int id, String droneSerialNumber, String medicineCode, int medicineWeight, boolean delivered) {
		super();
		this.id = id;
		this.droneSerialNumber = droneSerialNumber;
		this.medicineCode = medicineCode;
		this.medicineWeight = medicineWeight;
		this.delivered = delivered;
	}
}
