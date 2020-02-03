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
	private Long TimeSheetId;

	@Column(name = "siteCode")
	private String siteCode;

	@Column(name = "totalAmount")
	private double totalAmount;

	@Column(name = "status")
	private String status;

	@Column(name = "creatDate")
	private String creatDate;

	@Column(name = "Hours")
	private double Hours;

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

	public TimeSheet(Date date, String sitecode) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		this.creatDate = format.format(date);
		this.siteCode = sitecode;
		this.status = "submitted";
	}

	public Long getTimeSheetId() {
		return TimeSheetId;
	}

	public void setTimeSheetId(Long timeSheetId) {
		TimeSheetId = timeSheetId;
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
		return Hours;
	}

	public void setHours(double hours) {
		Hours = hours;
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
		this.Hours += this.jobWorkload.getHoursWorked();
		this.amount += this.jobWorkload.getJobManager().getRateHourly()*this.jobWorkload.getHoursWorked();;
	}

	public MachineWorkload getMachineWorkload() {
		return machineWorkload;
	}

	public void setMachineWorkload(MachineWorkload machineWorkload) {
		this.machineWorkload = machineWorkload;
		this.Hours += this.machineWorkload.getHoursWorked();
		this.amount += this.machineWorkload.getMachineManager().getRate()*this.machineWorkload.getHoursWorked();
	}

}
