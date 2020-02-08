package com.labor.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
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
	
	private User user;
	
	public User getUser(String username) {
		return userService.findUserByUsername(username);
	}
	
	//Get list
	public void getUsers() {
		UsernamePasswordAuthenticationToken u = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		String username = u.getName();
		User user1 = getUser(username);
		this.user = user1;
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/users")
	public List<User> displayUsers() {
		return userService.listUser();
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/machineManager")
//	@PreAuthorize("hasAuthority('admin')")
	public List<MachineManager> displayMachineManager(){
		return machineManagerService.listMachineManager();
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/machineWorkload")
	public List<MachineWorkload> displayMachineWorkload(){
		return machineWorkloadService.findAll();
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/timeSheet")
	@PreAuthorize("hasAuthority('contractor')")
	public List<TimeSheet> displayTimeSheet(){
		return timeSheetService.listTimeSheet();
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping(value="/userTimeSheet")
//	@PreAuthorize("hasAuthority('admin')")
	public List<TimeSheet> displayUserTimeSheet(){
		getUsers();
		return user.getTimeSheets();
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/jobManager")
//	@PreAuthorize("hasAuthority('admin')")
	public List<JobManager> jobManagerDisplay(){
		return jobManagerService.findAll();
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/jobManagerCode")
	@PreAuthorize("hasAuthority('admin')")
	public List<String> jobManagerCode(){
		List<JobManager> list = jobManagerService.findAll();
		List<String> resultList = new ArrayList<String>();
		for(JobManager jobManager : list) {
			resultList.add(jobManager.getJobCode());
		}
		System.out.println(resultList);
		return resultList;
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/jobWorkload")
	public List<JobWorkload> jobWorkloadDisplay(){
		return jobWorkloadService.findAll();
	}
	
	// Add data
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/newUser")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		userService.saveUser(user);		
		return new  ResponseEntity<Object>("user created successfully.",HttpStatus.OK);
	}	
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/newMachineManager")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Object> createMachineManager(@RequestBody MachineManager machineManager) {
		machineManagerService.saveMachineManager(machineManager);
		return new  ResponseEntity<Object>("MachineManager created successfully.",HttpStatus.OK);
	}	
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/newMachineWorkload")
	@PreAuthorize("hasAuthority('contractor')")
	public ResponseEntity<Object> createMachineWorkload(@RequestBody MachineWorkload machineWorkload) {
		machineWorkloadService.createMachineWorkload(machineWorkload);
		return new  ResponseEntity<Object>("MachineWorkload created successfully.",HttpStatus.OK);
	}	
	
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping(value = "/newTimeSheet", method = RequestMethod.POST , produces = "application/json")
	@PreAuthorize("hasAuthority('contractor')")
	public void createTimeSheet(@RequestBody JSONObject jsonParam) {
		getUsers();
		System.out.println(jsonParam.getString("username"));
		double Hours = 0;
		double amount = 0;
		JobManager job1 = jobManagerService.findByCode(jsonParam.getString("jobCode1"));
		JobManager job2 = jobManagerService.findByCode(jsonParam.getString("jobCode2"));
		JobManager job3 = jobManagerService.findByCode(jsonParam.getString("jobCode3"));
		MachineManager machine1 = machineManagerService.findByCode(jsonParam.getString("machineCode1"));
		MachineManager machine2 = machineManagerService.findByCode(jsonParam.getString("machineCode2"));
		MachineManager machine3 = machineManagerService.findByCode(jsonParam.getString("machineCode3"));
		if(job1 != null && isNumeric(jsonParam.getString("jobHour1"))) {
			double jobhour1 = Double.parseDouble(jsonParam.getString("jobHour1"));
			double jobAmount1 = jobhour1 * job1.getRateHourly();
			Hours += jobhour1;
			amount += jobAmount1;
		}
		if(job2 != null && isNumeric(jsonParam.getString("jobHour2"))) {
			double jobhour2 = Double.parseDouble(jsonParam.getString("jobHour2"));
			double jobAmount2 = jobhour2 * job2.getRateHourly();
			Hours += jobhour2;
			amount += jobAmount2;
		}
		if(job3 != null && isNumeric(jsonParam.getString("jobHour3"))) {
			double jobhour3 = Double.parseDouble(jsonParam.getString("jobHour3"));
			double jobAmount3 = jobhour3 * job3.getRateHourly();
			Hours += jobhour3;
			amount += jobAmount3;
		}
		if(machine1 != null && isNumeric(jsonParam.getString("machineHour1"))) {
			double machinehour1 = Double.parseDouble(jsonParam.getString("machineHour1"));
			double machineAmount1 = machinehour1 * machine1.getRate();
			Hours += machinehour1;
			amount += machineAmount1;
		}
		if(machine2 != null && isNumeric(jsonParam.getString("machineHour2"))) {
			double machinehour2 = Double.parseDouble(jsonParam.getString("machineHour2"));
			double machineAmount2 = machinehour2 * machine2.getRate();
			Hours += machinehour2;
			amount += machineAmount2;
		}
		if(machine3 != null && isNumeric(jsonParam.getString("machineHour3"))) {
			double machinehour3 = Double.parseDouble(jsonParam.getString("machineHour3"));
			double machineAmount3 = machinehour3 * machine3.getRate();
			Hours += machinehour3;
			amount += machineAmount3;
		}
		TimeSheet timeSheet = new TimeSheet(jsonParam.getString("date"), jsonParam.getString("siteCode"),
				Hours, amount, user.getUsername());
		timeSheetService.saveTimeSheet(timeSheet);
		List<TimeSheet> timeSheets = user.getTimeSheets();
		timeSheets.add(timeSheet);
		user.setTimeSheets(timeSheets);
		userService.saveUser(user);
	}	
	
    public boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
	
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/newJobManager")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Object> jobManagerCreate(@RequestBody JobManager jobManager){
		jobManagerService.createJobManager(jobManager);
		return new  ResponseEntity<Object>("Job manager created successfully.",HttpStatus.OK);
	}
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/newJobWorkload")
	@PreAuthorize("hasAuthority('contractor')")
	public ResponseEntity<Object> jobWorkloadCreate(@RequestBody JobWorkload jobWorkload){
		jobWorkloadService.createJobWorkload(jobWorkload);
		return new  ResponseEntity<Object>("Job workload created successfully.",HttpStatus.OK);
	}
	
	//Get Data By Id
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable long id) {
		return userService.getUser(id);
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/machineManager/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public MachineManager getMachineManagerById(@PathVariable long id) {
		System.out.println(id);
		return machineManagerService.getMachineManager(id);
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/machineWorkload/{id}")
	public MachineWorkload getMachineWorkloadById(@PathVariable long id) {
		return machineWorkloadService.findById(id);
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/timeSheet/{id}")
	public TimeSheet getTimeSheetById(@PathVariable long id) {
		return timeSheetService.getTimeSheet(id);
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/getJobManager/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public JobManager getJobManager(@PathVariable long id) {
		return jobManagerService.findById(id);
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/getJobWorkload/{id}")
	public JobWorkload getJobWorkload(@PathVariable long id) {
		return jobWorkloadService.findById(id);
	}
	
	//Delete Data
	
	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
	}
	
	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/deleteMachineManager/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public void deleteMachineManager(@PathVariable long id) {
		machineManagerService.deleteMachineManager(id);
	}
	
	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/machineWorkload/{id}")
	public void deleteMachineWorkloadr(@PathVariable long id) {
		machineWorkloadService.deleteById(id);
	}
	
	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/timeSheet/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public void deleteTimeSheet(@PathVariable long id) {
		timeSheetService.deleteTimeSheet(id);
	}
	
	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/deleteJobManager/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public void deleteJobManager(@PathVariable long id) {
		System.out.println("delete");
		jobManagerService.deleteById(id);
	}
	
	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/deleteJobWorkload/{id}")
	public void deleteJobWorkload(@PathVariable long id) {
		jobWorkloadService.deleteById(id);
	}
	
	//Update data by id
	
	@CrossOrigin("http://localhost:3000")
	@PutMapping("/users/update/{id}")
	public String updateUser(@RequestBody User user, @PathVariable long id) {
	
		userService.saveUser(user);
		return "User record for id=" +id+"updated.";
	}
	
	@CrossOrigin("http://localhost:3000")
	@PutMapping("/machineManager/update/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public String updateMachineManager(@RequestBody MachineManager machineManager, @PathVariable long id) {
		machineManager.setId(id);
		machineManagerService.saveMachineManager(machineManager);
		return "Machine manager record for id=" +id+"updated.";
	}
	
	@CrossOrigin("http://localhost:3000")
	@PutMapping("/machineWorkload/update/{id}")
	public String updateMachineWorkload(@RequestBody MachineWorkload machineWorkload, @PathVariable long id) {

		machineWorkloadService.createMachineWorkload(machineWorkload);
		return "Machine workload record for id=" +id+"updated.";
	}
	
	@CrossOrigin("http://localhost:3000")
	@PutMapping("/timeSheet/{id}")
	@PreAuthorize("hasAuthority('contractor')")
	public String updateTimeSheet(@RequestBody TimeSheet timeSheet, @PathVariable long id) {

		timeSheetService.saveTimeSheet(timeSheet);
		return "Timesheet record for id=" +id+"updated.";
	}
	
	@CrossOrigin("http://localhost:3000")
	@PutMapping("/updateJobManager/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public String updateJobManager(@RequestBody JobManager jobManager, @PathVariable long id){
		jobManager.setJobId(id);
		jobManagerService.update(jobManager);
		return "Job manager record for id=" +id+"updated.";
	}
	
	@CrossOrigin("http://localhost:3000")
	@PutMapping("/updateJobWorkload/{id}")
	public String updateJobWorkload(@RequestBody JobWorkload jobWorkload, @PathVariable long id){
		
		jobWorkloadService.updateJobWorkload(jobWorkload);
		return "Job workload record for id=" +id+"updated.";
	}
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/approvalTimesheet/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public String approvalTimesheet(@PathVariable long id){
		TimeSheet timeSheet = timeSheetService.getTimeSheet(id);
		System.out.println(timeSheet.getSiteCode());
		timeSheet.setStatus("Approval");
		timeSheetService.saveTimeSheet(timeSheet);
		return "Timesheet record for id=" +id+" is approvaled.";
	}
	
	@GetMapping("/")
	public String home() {
		System.out.println("----------------------------------");
		return "home!";
	}
	
	@GetMapping("/hello")
	public String Hello() {
		return "hello jwt !";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "hello admin !";
	}
}
