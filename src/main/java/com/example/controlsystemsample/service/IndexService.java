package com.example.controlsystemsample.service;

import org.springframework.stereotype.Service;

import com.example.controlsystemsample.model.dto.request.IndexRequestDTO;
import com.example.controlsystemsample.model.dto.response.IndexResponseDTO;
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
