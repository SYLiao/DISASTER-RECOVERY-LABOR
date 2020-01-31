package com.labor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MachineWorkload {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long machineWorkloadId;
	
	private String machineCode;
	
	private double hoursWorked;
	
	private double totalAmount;
	
	private String username;
	
	@ManyToOne
	private MachineManager machineManager;

	public long getMachineWorkloadId() {
		return machineWorkloadId;
	}

	public void setMachineWorkloadId(long machineWorkloadId) {
		this.machineWorkloadId = machineWorkloadId;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public MachineManager getMachineManager() {
		return machineManager;
	}

	public void setMachineManager(MachineManager machineManager) {
		this.machineManager = machineManager;
	}
	
	
}
