package com.myorg.drones_api.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dronebatrey")
public class DronesBattrey {
	@Id
	@GeneratedValue
	private int id;
	private String serialNumber;
	@Column(name="time", columnDefinition="DATETIME DEFAULT NOW()")
    private LocalDateTime checkTime;
	private int battreyPercent;
	
	public DronesBattrey(int id, String serialNumber, LocalDateTime checkTime, int battreyPercent) {
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.checkTime = checkTime;
		this.battreyPercent = battreyPercent;
	}
	
	
	public DronesBattrey() {
		super();
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public LocalDateTime getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(LocalDateTime checkTime) {
		this.checkTime = checkTime;
	}
	public int getBattreyPercent() {
		return battreyPercent;
	}
	public void setBattreyPercent(int battreyPercent) {
		this.battreyPercent = battreyPercent;
	}


	@Override
	public String toString() {
		return "DronesBattrey [id=" + id + ", serialNumber=" + serialNumber + ", checkTime=" + checkTime
				+ ", battreyPercent=" + battreyPercent + "]";
	}
	
	
	
	
}
