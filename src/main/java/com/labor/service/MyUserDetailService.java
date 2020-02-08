package com.labor.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.labor.model.*;
import com.labor.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Iterable<User> usersIterable = userRepository.findAll();
		UserDetails userDetails = null;
		User userInfo = null;
		System.out.println("username:"+username);
		for (User user : usersIterable) {
			System.out.println(user.getUsername());
			if(user.getUsername().equals(username)) {
				System.out.println("check");
				userDetails = user;
				userInfo = user;
			}
		}
		
		if(userInfo == null) {
			return null;
//			throw new UsernameNotFoundException("Not Found");
		}
		Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities();
		return new org.springframework.security.core.userdetails.User(userInfo.getUsername(), userInfo.getPassword(), authorities);
	}
	
}