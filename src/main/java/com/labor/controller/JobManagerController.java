package com.labor.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.labor.model.JobManager;
import com.labor.service.JobManagerService;

@RestController
public class JobManagerController {
	
	@Autowired
	private JobManagerService jobManagerService;
	
	@GetMapping("/jobManager")
	public List<JobManager> jobManagerDisplay(){
		return jobManagerService.findAll();
	}
	
	@PostMapping("/jobManagerCreate")
	public ResponseEntity<Object> jobManagerCreate(@RequestBody JobManager jobManager){
		JobManager jobManagerNew = jobManagerService.createJobManager(jobManager);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(jobManagerNew.getJobId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/getJobManager/{id}")
	public JobManager getJobManager(@PathVariable long id) {
		return jobManagerService.findById(id);
	}
	
	@DeleteMapping("/deleteJobManager/{id}")
	public void deleteJobManager(@PathVariable long id) {
		jobManagerService.deleteById(id);
	}
	
	@PutMapping("/updateJobManager/{id}")
	public ResponseEntity<JobManager> updateJobManager(@RequestBody JobManager jobManager, @PathVariable long id){
		JobManager jobManagerUpdated = jobManagerService.update(jobManager);
		
		return new ResponseEntity<JobManager>(jobManager, HttpStatus.OK);
	}
}
