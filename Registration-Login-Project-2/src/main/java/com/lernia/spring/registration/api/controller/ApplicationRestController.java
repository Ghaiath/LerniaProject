package com.lernia.spring.registration.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lernia.spring.registration.api.model.User;
import com.lernia.spring.registration.api.service.UserService;

@RestController
public class ApplicationRestController {
	

	@Autowired
	private UserService userService;

	@GetMapping("/saveuser")
	public String saveUser(@RequestParam int personalNumber, @RequestParam String userName,
			@RequestParam String firstName, @RequestParam String lastName, @RequestParam String address,
			@RequestParam String email, @RequestParam Long phone, @RequestParam String password) {
		User newUser = new User(personalNumber, userName, firstName, lastName, address, email, phone, password);
		userService.saveMyUser(newUser);
		System.out.println("Samer     " + newUser.getUserName());

		return "User has been saved";

	}
	
	@GetMapping("/getoneuser/{id}")
	public User findOneUser(@PathVariable int id) {
		//ModelAndView mav = new ModelAndView("userCenter/loginPage");
		//mav.addObject("id", id);
		//mav.setViewName("login");		
		return userService.getByID(id);

	}
	
	

}
