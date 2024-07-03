package com.example.controlsystemsample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.controlsystemsample.model.dto.request.UserData1ListExcelRequestDTO;
import com.example.controlsystemsample.model.dto.request.UserData1ListRequestDTO;
import com.example.controlsystemsample.model.dto.response.UserData1ListResponseDTO;

@Mapper
public interface DataTablePageMapper {

	List<UserData1ListResponseDTO> selectUserData1List(UserData1ListRequestDTO params);

	int selectCountUserData1List(UserData1ListRequestDTO params);

	List<UserData1ListResponseDTO> selectUserData1ListForExcel(UserData1ListExcelRequestDTO params);
}
