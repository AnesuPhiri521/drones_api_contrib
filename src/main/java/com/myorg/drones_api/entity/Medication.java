package com.myorg.drones_api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="medication_tbl")
public class Medication {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int weight;
	private String code;
	private String image;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
	public Medication(int id, String name, int weight, String code, String image) {
		super();
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.code = code;
		this.image = image;
	}
	
	public Medication() {
		super();
	}
	
	@Override
	public String toString() {
		return "Medication [id=" + id + ", name=" + name + ", weight=" + weight + ", code=" + code + ", image=" + image
				+ "]";
	}
	
	
	
	
	
	
}
