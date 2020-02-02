package com.labor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labor.model.JobManager;

@Repository
public interface JobManagerRepository extends JpaRepository<JobManager, Long>{

}
