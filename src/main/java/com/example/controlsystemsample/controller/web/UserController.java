package com.example.controlsystemsample.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping("/login")
	public String login(Model model){
		// model.addAttribute("testValue", "JAY JAY");
		return "login";
	}

	@GetMapping("/signup")
	public String signup(Model model){
		// model.addAttribute("testValue", "JAY JAY");
		return "signup";
	}
}
