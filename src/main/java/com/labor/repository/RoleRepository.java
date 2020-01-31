package com.labor.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.labor.model.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepositoryImplementation<Role, Long> {

	Role findByRole(String role);
}
