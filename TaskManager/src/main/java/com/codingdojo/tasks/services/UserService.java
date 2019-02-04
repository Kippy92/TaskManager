package com.codingdojo.tasks.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.codingdojo.tasks.models.User;
import com.codingdojo.tasks.repositories.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User createUser(Map<String, String> body) {
		User user = new User(body);
		this.userRepository.save(user);
		return user;
	}
	
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	
}