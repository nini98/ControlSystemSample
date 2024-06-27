package com.example.controlsystemsample.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.controlsystemsample.common.response.Response;
import com.example.controlsystemsample.model.dto.request.IndexRequestDTO;
import com.example.controlsystemsample.model.dto.response.IndexResponseDTO;
import com.example.controlsystemsample.service.IndexService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class IndexRestController {

    private final IndexService indexService;

    @PostMapping("/test")
    public Response<IndexResponseDTO> test(@RequestBody IndexRequestDTO params) throws Exception {
        return Response.success(indexService.test(params));
    }
}
