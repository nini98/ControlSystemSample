package com.example.controlsystemsample.controller.api;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controlsystemsample.common.response.Response;
import com.example.controlsystemsample.model.dto.request.UserData1ListExcelRequestDTO;
import com.example.controlsystemsample.model.dto.request.UserData1ListRequestDTO;
import com.example.controlsystemsample.model.dto.response.UserData1ListResponseDTO;
import com.example.controlsystemsample.model.dto.response.UserData1ListResponseWrapperDTO;
import com.example.controlsystemsample.service.DataTablePageService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DataTablePageRestController {

	private final DataTablePageService dataTablePageService;

	@GetMapping("/user-data-1")
	public Response<UserData1ListResponseWrapperDTO> getUserData1List(@ModelAttribute UserData1ListRequestDTO params){
		List<UserData1ListResponseDTO> result = dataTablePageService.getUserData1List(params);
		int totalRecords = dataTablePageService.getCountUserData1List(params);

		return Response.success(new UserData1ListResponseWrapperDTO(params.getDraw(), totalRecords, result));
	}

	@GetMapping("/user-data-1/excel/download")
	public void downloadExcel(@ModelAttribute UserData1ListExcelRequestDTO params, HttpServletResponse response
		// HttpServletResponse response,
		// @RequestParam("headers") String headersJson,
		// @RequestParam Map<String, String> params
	) throws IOException {

		// List<Map<String, Object>> headers = parseHeaders(headersJson); // JSON 문자열을 파싱하여 헤더 리스트를 반환하는 로직을 구현하세요
		// byte[] excelData = dataTablePageService.generateExcel(params, headers);

		System.out.println("JAY TEST :1: " + params.getExcelHeader());

		byte[] excelData = dataTablePageService.generateExcel(params);

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=data.xlsx");
		response.getOutputStream().write(excelData);
	}

}
