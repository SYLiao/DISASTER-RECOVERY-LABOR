package com.labor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jobWorkload")
public class JobWorkload {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long jobWorkloadId;
	
	@Column(name = "jobCode")
	private String jobCode;
	
	@Column(name = "hoursWorked")
	private double hoursWorked;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "totalAmount")
	private double totalAmount;
	
	@ManyToOne
	private JobManager jobManager;

	public long getJobWorkloadId() {
		return jobWorkloadId;
	}

	public void setJobWorkloadId(long jobWorkloadId) {
		this.jobWorkloadId = jobWorkloadId;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public JobManager getJobManager() {
		return jobManager;
	}

	public void setJobManager(JobManager jobManager) {
		this.jobManager = jobManager;
	}

}
