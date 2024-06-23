package com.myorg.drones_api.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="drone-battery")
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class DronesBattery {
	@Id
	@GeneratedValue
	private int id;
	private String serialNumber;
	@Column(name="time", columnDefinition="DATETIME DEFAULT NOW()")
    private LocalDateTime checkTime;
	private int batteryPercent;
	
	public DronesBattery(int id, String serialNumber, LocalDateTime checkTime, int batteryPercent) {
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.checkTime = checkTime;
		this.batteryPercent = batteryPercent;
	}
}
