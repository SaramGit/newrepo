package com.nt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nt.model.User;
import com.nt.service.UserService;



@Controller
@RequestMapping(value="/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@GetMapping(value = "/createuser")
	public ModelAndView createUserView() {
		ModelAndView modelAndView = new ModelAndView("createusers");
		modelAndView.addObject("user", new User());
		modelAndView.addObject("allProfiles", getProfiles());
		return modelAndView;
	}

	@PostMapping("/createuser")
	public ModelAndView createUser(@Valid User user, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		if (result.hasErrors()) {
			logger.info("Validation errors while submitting form.");
			modelAndView.setViewName("createuser");
			modelAndView.addObject("user", user);
			modelAndView.addObject("allProfiles", getProfiles());
			return modelAndView;
		}
		userService.addUser(user);
		modelAndView.addObject("allUsers", userService.getAllUsers());
		modelAndView.setViewName("userinfo");
		logger.info("Form submitted successfully.");
		return modelAndView;
	}

	private List<String> getProfiles() {
		List<String> list = new ArrayList<>();
		list.add("Associate");
		list.add("AVP");
		list.add("VP");
		list.add("Director");
		return list;
	}
	@InitBinder
    public void initBinder (WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true));
    }
	
	
}
