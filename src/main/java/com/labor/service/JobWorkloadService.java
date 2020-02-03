package com.labor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labor.model.JobWorkload;
import com.labor.repository.JobWorkloadRepository;

@Service("jobWorkloadService")
public class JobWorkloadService {
	
	@Autowired
	private JobWorkloadRepository jobWorkloadRepository;
	
	public void createJobWorkload(JobWorkload jobWorkload) {
		jobWorkloadRepository.save(jobWorkload);
	}
	
	public JobWorkload findById(long jobworkId) {
		JobWorkload jobWorkload = jobWorkloadRepository.findById(jobworkId).orElse(null);
		return jobWorkload;
	}
	
	public List<JobWorkload> findAll(){
		return (List<JobWorkload>) jobWorkloadRepository.findAll();
	}
	
	public void updateJobWorkload(JobWorkload jobWorkload) {
		JobWorkload jobWorkload2 = jobWorkloadRepository.findById(jobWorkload.getJobWorkloadId()).orElse(null);
		if(jobWorkload2 != null) {
			jobWorkloadRepository.save(jobWorkload);
		}
	}
	
	public void deleteById(long id) {
		jobWorkloadRepository.deleteById(id);
	}
	
	public void deleteAll() {
		jobWorkloadRepository.deleteAll();
	}
}
