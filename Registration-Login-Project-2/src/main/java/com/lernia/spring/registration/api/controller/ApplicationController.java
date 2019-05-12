package com.lernia.spring.registration.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lernia.spring.registration.api.model.User;
import com.lernia.spring.registration.api.service.UserService;

@Controller
public class ApplicationController {

	@Bean
	public BCryptPasswordEncoder encoderPWD() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	UserService userService;

	@Autowired
	HttpSession session;

	@RequestMapping({ "/welcome", "/" })
	public String welcom(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage";
	}

	@RequestMapping("/register")
	public String registration(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_REGISTER");
		return "/register";
	}

	@PostMapping("/save-user")
	public String registerUser(@ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Samer " + user.toString());

		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		User userExist = userService.findBypersonalNumber(user.getPersonalNumber());
		Boolean isExist = (userExist != null);
		if (isExist) {
			messages.put("AlreadyExist", "This user is already exist, try again with new input data.");
			System.out.println(messages);
			request.setAttribute("samer", messages);
			request.setAttribute("isExist", isExist);
			request.getRequestDispatcher("/register").forward(request, response);

			return "/register";
		}
		userService.saveMyUser(user);
		request.setAttribute("mode", "MODE_HOME");
		return "redirect:/login";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGIN");
		return "/login";
	}

	@RequestMapping("/login-user")
	public String loginUser(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, InterruptedException {
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		User userFromDatabase = userService.findByUserName(user.getUserName());

		if (userFromDatabase == null) {
			Boolean isExist = false;
			request.setAttribute("isExist", isExist);
			request.setAttribute("messages", messages);
			messages.put("UserNotFound", "There is no account with these information");
			request.setAttribute("messages", messages);
			System.out.println(messages);
			request.getRequestDispatcher("/login").forward(request, response);
			return "login";

		}

		if (encoderPWD().matches(user.getPassword(), userFromDatabase.getPassword())) {
			Boolean isExist = true;
			messages.put("Approved", "Welcome - You will be forwarded to your dashboard");
			request.setAttribute("isExist", isExist);
			request.setAttribute("messages", messages);
			System.out.println("samer" + request.getContextPath());
			System.out.println(messages);
			System.out.println("Baq baq");
			return "redirect:/my-dashboard";
		} else {
			Boolean BadCredentials = true;
			messages.put("BadCredentials", "Invalid Username or Password");
			request.setAttribute("messages", messages);
			request.setAttribute("BadCredentials", BadCredentials);
			System.out.println(messages);
			request.getRequestDispatcher("/login").forward(request, response);
			return "login";
		}
	}

	public class obteinUserSession {
		@RequestMapping(value = "/loginds", method = RequestMethod.GET)
		public String UserSession(ModelMap modelMap) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			System.out.println(auth.toString());
			String name = auth.getName();
			modelMap.addAttribute("username", name);
			return "hellos " + name;
		}
	}

	@RequestMapping(value = "/samer", method = RequestMethod.GET)
	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		System.out.println("att  " + attr);
		return attr.getRequest().getSession(true); // true == allow create
	}

	@GetMapping("/my-dashboard")
	public String showDashboard(HttpServletRequest request, HttpServletResponse response) {
		// userService.getUserIdFromPrinciple();
		if (userService.getUserIdFromPrinciple() == 0) {
			request.setAttribute("login", "You have to login first");
			return "/login";
		}
		session.setAttribute("user_id", userService.getUserIdFromPrinciple());
		return "dashboard";
		
		
		
	}

}