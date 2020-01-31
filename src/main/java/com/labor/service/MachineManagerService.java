package com.labor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labor.model.MachineManager;
import com.labor.repository.MachineManagerRepository;

@Service("machineManagerService")
public class MachineManagerService {

	@Autowired
	private MachineManagerRepository machineManagerRepository;
	
	public List<MachineManager> listMachineManager() {
		return (List<MachineManager>) (machineManagerRepository.findAll());
	}
		
	public void saveMachineManager(MachineManager machineManager) {
		machineManagerRepository.save(machineManager);
	}
	
	public MachineManager getMachineManager(Long id) {
		return machineManagerRepository.findById(id).get();
	}
	
	public void deleteMachineManager(Long id) {
		machineManagerRepository.deleteById(id);
	}
}
