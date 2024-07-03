package com.example.controlsystemsample.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controlsystemsample.common.response.Response;
import com.example.controlsystemsample.model.dto.request.UserData1ListRequestDTO;
import com.example.controlsystemsample.model.dto.response.UserData1ListResponseDTO;
import com.example.controlsystemsample.service.DataTablePageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DataTablePageRestController {

	private final DataTablePageService dataTablePageService;

	@GetMapping("/user-data-1")
	public Response<List<UserData1ListResponseDTO>> getUserData1List(@ModelAttribute UserData1ListRequestDTO params){
		List<UserData1ListResponseDTO> result = dataTablePageService.getUserData1List(params);
		return Response.success(result);
	}

}
