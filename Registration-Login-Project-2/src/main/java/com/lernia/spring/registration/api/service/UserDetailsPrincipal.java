package com.lernia.spring.registration.api.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lernia.spring.registration.api.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsPrincipal implements UserDetails {

	public UserDetailsPrincipal(User user) {
		this.user = user;
	}

	private static final long serialVersionUID = 1L;

	private User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println(user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
				.collect(Collectors.toList()));
		return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getActive();
	}

}
