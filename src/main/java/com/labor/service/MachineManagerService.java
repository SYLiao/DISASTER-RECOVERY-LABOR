package com.labor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labor.model.JobManager;
import com.labor.model.MachineManager;
import com.labor.repository.MachineManagerRepository;

@Service("machineManagerService")
public class MachineManagerService {

	@Autowired
	private MachineManagerRepository machineManagerRepository;
	
	public List<MachineManager> listMachineManager() {
		return (List<MachineManager>) (machineManagerRepository.findAll());
	}
		
	public MachineManager saveMachineManager(MachineManager machineManager) {
		return machineManagerRepository.save(machineManager);
	}
	
	public MachineManager getMachineManager(Long id) {
		return machineManagerRepository.findById(id).get();
	}
	
	public MachineManager findByCode(String machineCode) {
		Iterable<MachineManager> iterable = machineManagerRepository.findAll();
		for(MachineManager machineManager : iterable) {
			if(machineManager.getMachineCode().equals(machineCode)) {
				return machineManager;
			}
		}
		return null;
	}
	
	public void deleteMachineManager(Long id) {
		machineManagerRepository.deleteById(id);
	}
}
