package com.lernia.spring.registration.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lernia.spring.registration.api.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByuserNameAndPassword(String userName, String password);
	
	public User findBypersonalNumber(long personalNumber);

	public User findByuserName(String username);

	public User findByuserNameAndPersonalNumber(String username, long personalnumber);
	
}
