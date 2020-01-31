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
@Table(name = "timesheet")
public class TimeSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "sitecode")
	private String sitecode;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "totalamount")
	private double totalamount;
	
	@Column(name = "status")
	private String status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jobworkload_id")
	private JobWorkload jobworkload;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "machineworkload_id")
	private MachineWorkload machineworkload;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSitecode() {
		return sitecode;
	}

	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
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
		return jobworkload;
	}

	public void setJobworkload(JobWorkload jobworkload) {
		this.jobworkload = jobworkload;
	}

	public MachineWorkload getMachineworkload() {
		return machineworkload;
	}

	public void setMachineworkload(MachineWorkload machineworkload) {
		this.machineworkload = machineworkload;
	}
	
	
	
}
