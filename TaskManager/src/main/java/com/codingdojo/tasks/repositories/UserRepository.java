package com.codingdojo.tasks.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.tasks.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	public User findByEmail(String email);
	
}
