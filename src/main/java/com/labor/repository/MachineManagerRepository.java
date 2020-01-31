package com.labor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labor.model.MachineManager;

@Repository("machinemanagerRepository")
public interface MachineManagerRepository extends JpaRepository<MachineManager, Long> {

}
