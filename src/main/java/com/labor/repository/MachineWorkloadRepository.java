package com.labor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labor.model.MachineWorkload;

@Repository
public interface MachineWorkloadRepository extends JpaRepository<MachineWorkload, Long>{

}
