package com.example.controlsystemsample.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RealTimeChartPageController {
	@GetMapping("/realtime-chart")
	public String goRealTimeChartPage(Model model){
		return "realtimeChart";
	}
}
