package com.lernia.spring.registration.api.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lernia.spring.registration.api.model.Role;
import com.lernia.spring.registration.api.model.User;
import com.lernia.spring.registration.api.repository.RoleRepository;
import com.lernia.spring.registration.api.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

	// @Autowired
	// BCryptPasswordEncoder encoder;
	// private Random random = new Random();

	public UserService() {
		super();
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public void saveMyUser(User user) {
		Role role = roleRepository.findByRole("USER");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setPassword(encoder().encode(user.getPassword()));
		user.setActive(true);
		user.setRating(ThreadLocalRandom.current().nextInt(0, 10 + 1));
		user.setBalance(1000 * ThreadLocalRandom.current().nextInt(0, 50 + 1));
		user.setRoles(roles);
		user.setRoles(user.getRoles());
		System.out.println("Baq" + roleRepository.findAll());
		userRepository.save(user);

	}

	public void updateMyUser(User user) {
		user.setPassword(encoder().encode(user.getPassword()));
		userRepository.save(user);

	}

	public List<User> showAllUsers() {
		List<User> users = new ArrayList<User>();
		for (User user : userRepository.findAll()) {
			users.add(user);
		}
		return users;
	}

	public void deleteTheUser(int id) {
		userRepository.deleteById(id);
	}

	public User editUser(int id) {
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id"));
	}

	public User findByUsernameAndPassword(String userName, String password) {

		return userRepository.findByuserNameAndPassword(userName, password);
	}

	public User findBypersonalNumber(long personalNumber) {
		return userRepository.findBypersonalNumber(personalNumber);
	}

	public User findByUserName(String username) {
		return userRepository.findByuserName(username);
	}

	public User findByuserNameAndpersoNalumber(String username, long personalnumber) {
		return userRepository.findByuserNameAndPersonalNumber(username, personalnumber);
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();

	}

	public User getByID(long id) {
		return userRepository.findById((int) id).orElseThrow(() -> new EntityNotFoundException("id"));

	}

	public int getUserIdFromPrinciple() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		System.out.println("Baaaaaaaaaq2" + name.toString());
		User user = userRepository.findByuserName(name);
		if (user == null) {
			return 0;
		}
		System.out.println("Baaaaaaaaaq" + user.toString());
		return user.getUser_id();
	}
}
