
package com.labor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jobManager")
public class JobManager {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long JobId;
	
	@Column(name = "jobCode")
	private String jobCode;
	
	@Column(name = "jobDescription")
	private String jobDescription;
	
	@Column(name = "rateHourly")
	private String rateHourly;
	
	@Column(name = "maxHour")
	private double maxHour;

	public long getJobId() {
		return JobId;
	}

	public void setJobId(long jobId) {
		JobId = jobId;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getRateHourly() {
		return rateHourly;
	}

	public void setRateHourly(String rateHourly) {
		this.rateHourly = rateHourly;
	}

	public double getMaxHour() {
		return maxHour;
	}

	public void setMaxHour(double maxHour) {
		this.maxHour = maxHour;
	}
		
}
