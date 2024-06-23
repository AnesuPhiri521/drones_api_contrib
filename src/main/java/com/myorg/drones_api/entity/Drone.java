package com.myorg.drones_api.entity;

import com.myorg.drones_api.utils.DroneState;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="drones")
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Drone {
	@Id
	@GeneratedValue
	private int id;
	private String serialNumber;
	private String model;
	private int weight;
	private int battreyPercent;
	@Enumerated(EnumType.STRING)
	private DroneState state;
	
	public Drone(int id, String serialNumber, String model, int weight, int battreyPercent, DroneState state) {
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.model = model;
		this.weight = weight;
		this.battreyPercent = battreyPercent;
		this.state = state;
	}
}
