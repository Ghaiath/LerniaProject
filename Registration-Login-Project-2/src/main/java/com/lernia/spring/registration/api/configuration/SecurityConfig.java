package com.lernia.spring.registration.api.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.lernia.spring.registration.api.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

//	@Autowired
//	private DataSource dataSource;
//
//	@Value("${spring.queries.users-query}")
//	private String usersQuery;
//
//	@Value("${spring.queries.roles-query}")
//	private String rolesQuery;

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
//		authenticationMgr.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
//				.dataSource(dataSource).passwordEncoder(encoderPassword());
//
//		System.out.println(usersQuery);
//		System.out.println(rolesQuery);
//		System.out.println(
//				encoderPassword().matches("abc123ABC", "$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i"));
//
//		System.out.println("Zai   " + authenticationMgr.jdbcAuthentication().usersByUsernameQuery(usersQuery)
//				.authoritiesByUsernameQuery(rolesQuery).dataSource(dataSource).passwordEncoder(encoderPassword()));
//
//	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());

	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(encoderPassword());
		daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl);

		return daoAuthenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				.antMatchers("/welcome", "/register", "/login").permitAll()
				.antMatchers("/login-user", "/save-user", "/my-dahsboard").authenticated()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.and()
				.formLogin()
				.loginProcessingUrl("/login-user")
				.loginPage("/login")
				.defaultSuccessUrl("/my-dashboard")
				.usernameParameter("userName")
				.passwordParameter("password")
				.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(true);
		//web.ignoring().anyRequest().antMatchers("**/resources/**");
	}

	@Bean
	public BCryptPasswordEncoder encoderPassword() {
		return new BCryptPasswordEncoder();
	}

}
