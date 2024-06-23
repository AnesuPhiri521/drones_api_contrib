package com.myorg.drones_api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="medication_tbl")
@Builder
@ToString
@Setter
@Getter
public class Medication {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int weight;
	private String code;
	private String image;
	
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

}
