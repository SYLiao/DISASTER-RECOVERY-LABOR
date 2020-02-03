package com.labor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labor.model.JobManager;
import com.labor.repository.JobManagerRepository;

@Service
public class JobManagerService {
	
	@Autowired
	private JobManagerRepository jobManagerRepository;
	
	public JobManager createJobManager(JobManager jobManager) {
		return jobManagerRepository.save(jobManager);
	}
	
	public JobManager findById(long jobId) {
		JobManager jobManager = jobManagerRepository.findById(jobId).orElse(null);
		return jobManager;
	}
	
	public List<JobManager> findAll() {
		return (List<JobManager>) jobManagerRepository.findAll();
	}
	
	public JobManager findByCode(String jobCode) {
		Iterable<JobManager> iterable = jobManagerRepository.findAll();
		for(JobManager jobManager : iterable) {
			if(jobManager.getJobCode().equals(jobCode)) {
				return jobManager;
			}
		}
		return null;
	}
	
	public JobManager update(JobManager jobManager) {
		JobManager job = jobManagerRepository.findById(jobManager.getJobId()).orElse(null);
		if(job != null) {
			jobManagerRepository.save(jobManager);
		}
		return jobManager;
	}
	
	public void deleteById(long id) {
		jobManagerRepository.deleteById(id);
	}
	
	public void deleteAll() {
		jobManagerRepository.deleteAll();
	}
}
