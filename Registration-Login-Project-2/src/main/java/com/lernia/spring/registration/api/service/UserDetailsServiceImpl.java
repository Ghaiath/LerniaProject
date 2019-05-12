package com.lernia.spring.registration.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lernia.spring.registration.api.model.User;
import com.lernia.spring.registration.api.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByuserName(username);
		if (user == null)
			throw new UsernameNotFoundException(username);
		//Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		//for (Role role : user.getRoles()) {
		//	grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
		//}
		UserDetailsPrincipal userPrincipal = new UserDetailsPrincipal(user);
		System.out.println("User Principals = " + userPrincipal.getAuthorities().toString());
		return userPrincipal;
	}

}
