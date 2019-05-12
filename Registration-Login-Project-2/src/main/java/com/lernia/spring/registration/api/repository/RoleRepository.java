package com.lernia.spring.registration.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lernia.spring.registration.api.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	public Role findByRole(String role);

}
