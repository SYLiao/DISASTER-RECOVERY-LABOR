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

import com.labor.model.TimeSheet;
import com.labor.service.TimeSheetService;

@RestController
public class TimeSheetController {

	@Autowired
	private TimeSheetService timeSheetService;
	
	@GetMapping("/timeSheet")
	public List<TimeSheet> displayTimeSheet(){
		return timeSheetService.listTimeSheet();
	}

	@PostMapping("/newTimeSheet")
	public ResponseEntity<Object> createTimeSheet(@RequestBody TimeSheet timeSheet) {
		TimeSheet savedTimeSheet = timeSheetService.saveTimeSheet(timeSheet);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedTimeSheet.getId()).toUri();
		return ResponseEntity.created(location).build();
	}	

	@GetMapping("/timeSheet/{id}")
	public TimeSheet getTimeSheetById(@PathVariable Long id) {
		return timeSheetService.getTimeSheet(id);
	}
	
	@DeleteMapping("/timeSheet/{id}")
	public void deleteTimeSheet(@PathVariable Long id) {
		timeSheetService.deleteTimeSheet(id);
	}
	
	@PutMapping("/timeSheet/{id}")
	  public ResponseEntity<TimeSheet> updateTimeSheet(@RequestBody TimeSheet timeSheet, @PathVariable Long id) {

		TimeSheet timeSheetUpdated = timeSheetService.saveTimeSheet(timeSheet);
	    return new ResponseEntity<TimeSheet>(timeSheet, HttpStatus.OK);
	  }
}
