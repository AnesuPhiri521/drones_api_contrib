package com.myorg.drones_api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="drones")
public class Drone {
	@Id
	@GeneratedValue
	private int id;
	private String serialNumber;
	private String model;
	private int weight;
	private int battreyPercent;
	private String state;
	
	public Drone(int id, String serialNumber, String model, int weight, int battreyPercent, String state) {
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.model = model;
		this.weight = weight;
		this.battreyPercent = battreyPercent;
		this.state = state;
	}
	
	public Drone() {
		super();
	}

	
	
	public int getId() {
		return id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getBattreyPercent() {
		return battreyPercent;
	}

	public void setBattreyPercent(int battreyPercent) {
		this.battreyPercent = battreyPercent;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Drone [id=" + id + ", serialNumber=" + serialNumber + ", model=" + model + ", weight=" + weight
				+ ", battreyPercent=" + battreyPercent + ", state=" + state + "]";
	}
	
	
	
	
}
