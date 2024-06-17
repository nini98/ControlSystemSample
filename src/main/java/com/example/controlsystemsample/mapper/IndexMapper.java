package com.example.controlsystemsample.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.controlsystemsample.dto.request.IndexRequestDTO;
import com.example.controlsystemsample.dto.response.IndexResponseDTO;

@Mapper
public interface IndexMapper {
	IndexResponseDTO test(IndexRequestDTO params);
}
