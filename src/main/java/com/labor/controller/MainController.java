package com.labor.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labor.model.JobManager;
import com.labor.model.JobWorkload;
import com.labor.model.MachineManager;
import com.labor.model.MachineWorkload;
import com.labor.model.TimeSheet;
import com.labor.model.User;
import com.labor.service.JobManagerService;
import com.labor.service.JobWorkloadService;
import com.labor.service.MachineManagerService;
import com.labor.service.MachineWorkloadService;
import com.labor.service.TimeSheetService;
import com.labor.service.UserService;

@RestController
@RequestMapping("/")
public class MainController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TimeSheetService timeSheetService;
	
	@Autowired
	private MachineManagerService machineManagerService;
	
	@Autowired
	private MachineWorkloadService machineWorkloadService;
	
	@Autowired
	private JobManagerService jobManagerService;
	
	@Autowired
	private JobWorkloadService jobWorkloadService;
	
	//Get list
	
	@GetMapping("/users")
	public List<User> displayUsers() {
		return userService.listUser();
	}
	
	@GetMapping("/machineManager")
	public List<MachineManager> displayMachineManager(){
		return machineManagerService.listMachineManager();
	}
	
	@GetMapping("/machineWorkload")
	public List<MachineWorkload> displayMachineWorkload(){
		return machineWorkloadService.findAll();
	}
	
	@GetMapping("/timeSheet")
	public List<TimeSheet> displayTimeSheet(){
		return timeSheetService.listTimeSheet();
	}
	
	@GetMapping("/jobManager")
	public List<JobManager> jobManagerDisplay(){
		return jobManagerService.findAll();
	}
	
	@GetMapping("/jobWorkload")
	public List<JobWorkload> jobWorkloadDisplay(){
		return jobWorkloadService.findAll();
	}
	
	// Add data
	
	@PostMapping("/newUser")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		userService.saveUser(user);		
		return new  ResponseEntity<Object>("user created successfully.",HttpStatus.OK);
	}	
	
	@PostMapping("/newMachineManager")
	public ResponseEntity<Object> createMachineManager(@RequestBody MachineManager machineManager) {
		machineManagerService.saveMachineManager(machineManager);
		return new  ResponseEntity<Object>("MachineManager created successfully.",HttpStatus.OK);
	}	
	
	@PostMapping("/newMachineWorkload")
	public ResponseEntity<Object> createMachineWorkload(@RequestBody MachineWorkload machineWorkload) {
		machineWorkloadService.createMachineWorkload(machineWorkload);
		return new  ResponseEntity<Object>("MachineWorkload created successfully.",HttpStatus.OK);
	}	
	
	@PostMapping("/newTimeSheet")
	public ResponseEntity<Object> createTimeSheet(@RequestBody TimeSheet timeSheet) {
		timeSheetService.saveTimeSheet(timeSheet);
		return new  ResponseEntity<Object>("TimeSheet created successfully.",HttpStatus.OK);
	}	
	
	@PostMapping("/newJobManager")
	public ResponseEntity<Object> jobManagerCreate(@RequestBody JobManager jobManager){
		jobManagerService.createJobManager(jobManager);
		return new  ResponseEntity<Object>("Job manager created successfully.",HttpStatus.OK);
	}
	
	@PostMapping("/newJobWorkload")
	public ResponseEntity<Object> jobWorkloadCreate(@RequestBody JobWorkload jobWorkload){
		jobWorkloadService.createJobWorkload(jobWorkload);
		return new  ResponseEntity<Object>("Job workload created successfully.",HttpStatus.OK);
	}
	
	//Get Data By Id
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	@GetMapping("/machineManager/{id}")
	public MachineManager getMachineManagerById(@PathVariable Long id) {
		return machineManagerService.getMachineManager(id);
	}
	
	@GetMapping("/machineWorkload/{id}")
	public MachineWorkload getMachineWorkloadById(@PathVariable Long id) {
		return machineWorkloadService.findById(id);
	}
	
	@GetMapping("/timeSheet/{id}")
	public TimeSheet getTimeSheetById(@PathVariable Long id) {
		return timeSheetService.getTimeSheet(id);
	}
	
	@GetMapping("/getJobManager/{id}")
	public JobManager getJobManager(@PathVariable long id) {
		return jobManagerService.findById(id);
	}
	
	@GetMapping("/getJobWorkload/{id}")
	public JobWorkload getJobWorkload(@PathVariable long id) {
		return jobWorkloadService.findById(id);
	}
	
	//Delete Data
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
	
	@DeleteMapping("/machineManager/{id}")
	public void deleteMachineManager(@PathVariable Long id) {
		machineManagerService.deleteMachineManager(id);
	}
	
	@DeleteMapping("/machineWorkload/{id}")
	public void deleteMachineWorkloadr(@PathVariable Long id) {
		machineWorkloadService.deleteById(id);
	}
	
	@DeleteMapping("/timeSheet/{id}")
	public void deleteTimeSheet(@PathVariable Long id) {
		timeSheetService.deleteTimeSheet(id);
	}
	
	@DeleteMapping("/deleteJobManager/{id}")
	public void deleteJobManager(@PathVariable long id) {
		jobManagerService.deleteById(id);
	}
	
	@DeleteMapping("/deleteJobWorkload/{id}")
	public void deleteJobWorkload(@PathVariable long id) {
		jobWorkloadService.deleteById(id);
	}
	
	//Update data by id
	
	@PutMapping("/users/update/{id}")
	public String updateUser(@RequestBody User user, @PathVariable Long id) {
	
		userService.saveUser(user);
		return "User record for id=" +id+"updated.";
	}
	
	@PutMapping("/machineManager/update/{id}")
	  public String updateMachineManager(@RequestBody MachineManager machineManager, @PathVariable Long id) {

		machineManagerService.saveMachineManager(machineManager);
		return "Machine manager record for id=" +id+"updated.";
	  }
	
	@PutMapping("/machineWorkload/update/{id}")
	  public String updateMachineWorkload(@RequestBody MachineWorkload machineWorkload, @PathVariable Long id) {

		machineWorkloadService.createMachineWorkload(machineWorkload);
		return "Machine workload record for id=" +id+"updated.";
	  }
	
	@PutMapping("/timeSheet/{id}")
	  public String updateTimeSheet(@RequestBody TimeSheet timeSheet, @PathVariable Long id) {

		timeSheetService.saveTimeSheet(timeSheet);
		return "Timesheet record for id=" +id+"updated.";
	  }
	
	@PutMapping("/updateJobManager/{id}")
	public String updateJobManager(@RequestBody JobManager jobManager, @PathVariable long id){
		
		jobManagerService.update(jobManager);
		return "Job manager record for id=" +id+"updated.";
	}
	
	@PutMapping("/updateJobWorkload/{id}")
	public String updateJobWorkload(@RequestBody JobWorkload jobWorkload, @PathVariable long id){
		
		jobWorkloadService.updateJobWorkload(jobWorkload);
		return "Job workload record for id=" +id+"updated.";
	}
}
