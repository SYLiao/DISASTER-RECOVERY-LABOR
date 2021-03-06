package com.labor.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

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
	private Long timeSheetId;

	@Column(name = "siteCode")
	private String siteCode;

	@Column(name = "totalAmount")
	private double totalAmount;

	@Column(name = "status")
	private String status;

	@Column(name = "creatDate")
	private String creatDate;

	@Column(name = "Hours")
	private double hours;

	@Column(name = "amount")
	private double amount;

	@Column(name = "user_name")
	private String user_name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jobWorkload_id")
	private JobWorkload jobWorkload;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "machineWorkload_id")
	private MachineWorkload machineWorkload;

	public TimeSheet() {
		this.status = "submitted";
	}

	public TimeSheet(String date, String sitecode, double Hours, double amount, String username) {
		this.creatDate = date;
		this.siteCode = sitecode;
		this.hours = Hours;
		this.totalAmount = amount;
		this.user_name = username;
		this.status = "submitted";
	}

	public Long getTimeSheetId() {
		return timeSheetId;
	}

	public void setTimeSheetId(Long timeSheetId) {
		timeSheetId = timeSheetId;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		this.creatDate = format.format(creatDate);
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		hours = hours;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name() {
		if(this.user != null) {
			this.user_name = user.getFirstname();
		}
		else{
			this.user_name = "None";
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		setUser_name();
	}

	public JobWorkload getJobWorkload() {
		return jobWorkload;
	}

	public void setJobWorkload(JobWorkload jobWorkload) {
		this.jobWorkload = jobWorkload;
		this.hours += this.jobWorkload.getHoursWorked();
		this.amount += this.jobWorkload.getJobManager().getRateHourly()*this.jobWorkload.getHoursWorked();;
	}

	public MachineWorkload getMachineWorkload() {
		return machineWorkload;
	}

	public void setMachineWorkload(MachineWorkload machineWorkload) {
		this.machineWorkload = machineWorkload;
		this.hours += this.machineWorkload.getHoursWorked();
		this.amount += this.machineWorkload.getMachineManager().getRate()*this.machineWorkload.getHoursWorked();
	}

}
