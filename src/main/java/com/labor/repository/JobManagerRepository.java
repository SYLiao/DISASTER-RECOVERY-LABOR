package com.labor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labor.model.JobManager;

@Repository("jobManagerRepository")
public interface JobManagerRepository extends JpaRepository<JobManager, Long>{

}
