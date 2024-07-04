package com.example.controlsystemsample.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataTablePageController {
	@GetMapping("/data-table")
	public String goDataTablePage(Model model){
		return "dataTable";
	}

	@GetMapping("/data-table-2")
	public String goDataTable2Page(Model model){
		return "dataTable2";
	}
}
