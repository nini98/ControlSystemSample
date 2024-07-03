package com.example.controlsystemsample.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.controlsystemsample.common.util.DTOUtil;
import com.example.controlsystemsample.common.util.ExcelUtil;
import com.example.controlsystemsample.mapper.DataTablePageMapper;
import com.example.controlsystemsample.model.dto.request.UserData1ListExcelRequestDTO;
import com.example.controlsystemsample.model.dto.request.UserData1ListRequestDTO;
import com.example.controlsystemsample.model.dto.response.UserData1ListResponseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataTablePageService {

	private final DataTablePageMapper dataTablePageMapper;

	public List<UserData1ListResponseDTO> getUserData1List(UserData1ListRequestDTO params){
		return dataTablePageMapper.selectUserData1List(params);
	};

	public int getCountUserData1List(UserData1ListRequestDTO params){
		return dataTablePageMapper.selectCountUserData1List(params);
	}

	// public byte[] generateExcel(Map<String, String> params, List<Map<String, Object>> headers) throws IOException {
	// 	List<Map<String, Object>> data = dataTablePageMapper.selectDataForExcel(params);
	// 	return ExcelUtil.createExcel(headers, data);
	// }

	public byte[] generateExcel(UserData1ListExcelRequestDTO params) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> excelHeaders = objectMapper.readValue(params.getExcelHeader(), new TypeReference<List<Map<String, Object>>>() {});
		List<Map<String, Object>> excelData = DTOUtil.convertToMapList(dataTablePageMapper.selectUserData1ListForExcel(params));

		// List<Map<String, Object>> data = dataTablePageMapper.selectDataForExcel(params);

		return ExcelUtil.createExcel(excelHeaders, excelData);
	}
}
