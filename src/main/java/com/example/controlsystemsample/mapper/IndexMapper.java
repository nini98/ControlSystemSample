package com.example.controlsystemsample.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.controlsystemsample.model.dto.request.IndexRequestDTO;
import com.example.controlsystemsample.model.dto.response.IndexResponseDTO;

@Mapper
public interface IndexMapper {
	IndexResponseDTO test(IndexRequestDTO params);
}
