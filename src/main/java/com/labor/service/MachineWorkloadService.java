package com.labor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labor.model.MachineWorkload;
import com.labor.repository.MachineWorkloadRepository;

@Service
public class MachineWorkloadService {

	@Autowired
	private MachineWorkloadRepository machineWorkloadRepository;
	
	public void createMachineWorkload(MachineWorkload machineWorkload) {
		machineWorkloadRepository.save(machineWorkload);
	}
	
	public MachineWorkload findById(long id) {
		MachineWorkload machineWorkload = machineWorkloadRepository.findById(id).orElse(null);
		return machineWorkload;
	}
	
	public List<MachineWorkload> findAll(){
		return (List<MachineWorkload>) machineWorkloadRepository.findAll();
	}
	
	public void updateMachineWorkload(MachineWorkload machineWorkload) {
		MachineWorkload machineWorkload2 = machineWorkloadRepository.findById(machineWorkload.getMachineWorkloadId()).orElse(null);
		if(machineWorkload2 != null) {
			machineWorkloadRepository.save(machineWorkload);
		}
	}
	
	public void deleteById(long id) {
		machineWorkloadRepository.deleteById(id);
	}
	
	public void deleteAll() {
		machineWorkloadRepository.deleteAll();
	}
}
