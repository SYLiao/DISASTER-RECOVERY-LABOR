package com.labor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "machinemanager")
public class MachineManager {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "machinecode")
	private String machinecode;
	
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
		return machinecode;
	}

	public void setMachinecode(String machinecode) {
		this.machinecode = machinecode;
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
