package com.labor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labor.model.JobWorkload;

@Repository("jobWorkloadRepository")
public interface JobWorkloadRepository extends JpaRepository<JobWorkload, Long>{

}
