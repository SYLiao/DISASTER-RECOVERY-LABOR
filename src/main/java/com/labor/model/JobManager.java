package com.labor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JobManager {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long JobId;
	
	private String jobCode;
	
	private String description;
	
	private 
	
}
