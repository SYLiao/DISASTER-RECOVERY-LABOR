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

import com.labor.model.MachineManager;
import com.labor.service.MachineManagerService;

@RestController
public class MachineManagerController {

	@Autowired
	private MachineManagerService machineManagerService;
	
	@GetMapping("/machineManager")
	public List<MachineManager> displayMachineManager(){
		return machineManagerService.listMachineManager();
	}

	@PostMapping("/newTMachineManager")
	public ResponseEntity<Object> createMachineManager(@RequestBody MachineManager machineManager) {
		MachineManager savedMachineManager = machineManagerService.saveMachineManager(machineManager);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedMachineManager.getId()).toUri();
		return ResponseEntity.created(location).build();
	}	

	@GetMapping("/machineManager/{id}")
	public MachineManager getMachineManagerById(@PathVariable Long id) {
		return machineManagerService.getMachineManager(id);
	}
	
	@DeleteMapping("/machineManager/{id}")
	public void deleteMachineManager(@PathVariable Long id) {
		machineManagerService.deleteMachineManager(id);
	}
	
	@PutMapping("/machineManager/{id}")
	  public ResponseEntity<MachineManager> updateMachineManager(@RequestBody MachineManager machineManager, @PathVariable Long id) {

		MachineManager machineManagerUpdated = machineManagerService.saveMachineManager(machineManager);
	    return new ResponseEntity<MachineManager>(machineManager, HttpStatus.OK);
	  }
}
