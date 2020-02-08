package com.labor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.labor.model.User;
import com.labor.repository.UserRepository;

@Service("userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User findUserByUsername(String username){
		Iterable<User> usersIterable = userRepository.findAll();
		User userInfo = null;
		for (User user : usersIterable) {
			if(user.getUsername().equals(username)) {
				userInfo = user;
			}
		}
		return userInfo;
	}
	
	public List<User> listUser() {
		return (List<User>) (userRepository.findAll());
	}
		
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
