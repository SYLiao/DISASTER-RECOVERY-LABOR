package com.labor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labor.model.Role;
import com.labor.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Role findByName(String name) {
		Iterable<Role> iterable = roleRepository.findAll();
		for(Role role : iterable) {
			if(role.getRole().equals(name)) {
				return role;
			}
		}
		return null;
	}
}
