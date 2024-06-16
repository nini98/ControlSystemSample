package com.example.controlsystemsample.service;

import org.springframework.stereotype.Service;

import com.example.controlsystemsample.common.exception.ControlSystemException;
import com.example.controlsystemsample.common.response.ResultCode;
import com.example.controlsystemsample.dto.request.IndexRequestDTO;
import com.example.controlsystemsample.dto.response.IndexResponseDTO;
import com.example.controlsystemsample.mapper.IndexMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IndexService {

    private final IndexMapper indexMapper;

    public IndexResponseDTO test(IndexRequestDTO params) throws Exception {
        return indexMapper.test(params);
    }
}
