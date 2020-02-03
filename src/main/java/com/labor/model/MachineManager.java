package com.labor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "machineManager")
public class MachineManager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "machineCode")
	private String machineCode;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "rate")
	private double rate;
	
	@Column(name = "maxHours")
	private double maxHours;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMachinecode() {
		return machineCode;
	}

	public void setMachinecode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getMaxHours() {
		return maxHours;
	}

	public void setMaxHours(double maxHours) {
		this.maxHours = maxHours;
	}
	
	
}
