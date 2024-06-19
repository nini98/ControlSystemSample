package com.example.controlsystemsample.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.controlsystemsample.model.User;

@Mapper
public interface UserMapper {
	// User findByUsername(String username);
	User selectUser(String loginId);
}
