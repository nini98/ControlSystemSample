package com.example.controlsystemsample.security;

import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.controlsystemsample.mapper.UserMapper;
import com.example.controlsystemsample.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

	private final UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException, LockedException {
		User user = userMapper.selectUser(loginId);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		if ("Y".equals(user.getBlockYn())) {
			throw new LockedException("User is blocked");
		}
		return new CustomUserDetails(user);
	}
}
