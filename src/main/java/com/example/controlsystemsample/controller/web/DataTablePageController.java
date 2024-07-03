package com.example.controlsystemsample.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataTablePageController {
	@GetMapping("/data-table")
	public String login(Model model){
		return "dataTable";
	}
}
