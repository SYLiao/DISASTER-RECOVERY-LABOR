package com.labor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labor.model.TimeSheet;

@Repository("timeSheetRepository")
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {

}
