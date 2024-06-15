package com.example.controlsystemsample.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controlsystemsample.dto.request.IndexRequestDTO;
import com.example.controlsystemsample.dto.response.IndexResponseDTO;

import jakarta.xml.ws.Response;

@RestController
public class IndexRestController {

    @PostMapping("/test")
    public Response<IndexResponseDTO> test(IndexRequestDTO request){

    }
}
