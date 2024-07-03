package com.example.controlsystemsample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.controlsystemsample.mapper.DataTablePageMapper;
import com.example.controlsystemsample.model.dto.request.UserData1ListRequestDTO;
import com.example.controlsystemsample.model.dto.response.UserData1ListResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataTablePageService {

	private final DataTablePageMapper dataTablePageMapper;

	public List<UserData1ListResponseDTO> getUserData1List(UserData1ListRequestDTO params){
		return dataTablePageMapper.selectUserData1List(params);
	};
}
