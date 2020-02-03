package com.labor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labor.model.TimeSheet;
import com.labor.repository.TimeSheetRepository;

@Service("timeSheetService")
public class TimeSheetService {
	
	@Autowired 
	private TimeSheetRepository timeSheetRepository;
	
	public List<TimeSheet> listTimeSheet() {
		return (List<TimeSheet>) (timeSheetRepository.findAll());
	}
		
	public TimeSheet saveTimeSheet(TimeSheet timeSheet) {
		return timeSheetRepository.save(timeSheet);
	}
	
	public TimeSheet getTimeSheet(Long id) {
		return timeSheetRepository.findById(id).get();
	}
	
	public void deleteTimeSheet(Long id) {
		timeSheetRepository.deleteById(id);
	}

}
