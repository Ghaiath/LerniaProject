package com.lernia.spring.registration.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lernia.spring.registration.api.model.User;
import com.lernia.spring.registration.api.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	HttpSession session;

	@Autowired
	UserService userService;

	@GetMapping("/show-all-users")
	public String showAllUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("users", userService.showAllUsers());
		System.out.println(userService.showAllUsers());
		// request.getRequestDispatcher("/allusers").forward(request, response);
		return "allusers";
	}

	@GetMapping("/delete-user")
	public String deleteUser(@RequestParam int id, HttpServletRequest request) {
		userService.deleteTheUser(id);
		request.setAttribute("users", userService.showAllUsers());
		return "redirect:/admin/show-all-users";
	}

	@GetMapping("/admin-dashboard")
	public String showDashboard(HttpServletRequest request, HttpServletResponse response) {
		// userService.getUserIdFromPrinciple();
		session.setAttribute("user_id", userService.getUserIdFromPrinciple());
		return "admindashboard";

	}

	@PostMapping("/update-user")
	public String updateUser(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Samer " + user.toString());
		Map<String, String> messages = new HashMap<String, String>();
		messages.put("Updated", "This user has been uodated.");
		request.setAttribute("messages", messages);
		User userFromDatabase = userService.findByUserName(user.getUserName());
		System.out.println("Samer    2 " + userFromDatabase.toString());

		if (user.getPersonalNumber() == userFromDatabase.getPersonalNumber()) {
			user.setActive(userFromDatabase.getActive());
			user.setRating(userFromDatabase.getRating());
			user.setRoles(userFromDatabase.getRoles());
			userService.updateMyUser(user);
			return "login";

		} else {
			messages.put("NotFound", "This user can't be updated.");
			request.setAttribute("mode", "MODE_UPDATE");
			return "welcomepage";
		}
	}

	@RequestMapping("/edit-user")
	public String editUser(@RequestParam int id, HttpServletRequest request) {
		request.setAttribute("user", userService.getByID(id));
		request.setAttribute("mode", "MODE_UPDATE");
		return "welcomepage";
	}

}
