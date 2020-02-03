package com.labor.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "timeSheet")
public class TimeSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "siteCode")
	private String siteCode;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "totalAmount")
	private double totalAmount;
	
	@Column(name = "status")
	private String status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jobWorkload_id")
	private JobWorkload jobWorkload;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "machineWorkload_id")
	private MachineWorkload machineWorkload;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSitecode() {
		return siteCode;
	}

	public void setSitecode(String siteCode) {
		this.siteCode = siteCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotalamount() {
		return totalAmount;
	}

	public void setTotalamount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JobWorkload getJobworkload() {
		return jobWorkload;
	}

	public void setJobworkload(JobWorkload jobWorkload) {
		this.jobWorkload = jobWorkload;
	}

	public MachineWorkload getMachineworkload() {
		return machineWorkload;
	}

	public void setMachineworkload(MachineWorkload machineWorkload) {
		this.machineWorkload = machineWorkload;
	}
	
	
	
}
