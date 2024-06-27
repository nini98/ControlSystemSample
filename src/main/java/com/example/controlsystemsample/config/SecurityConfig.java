package com.example.controlsystemsample.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorizeRequests ->
				authorizeRequests
					.requestMatchers("/css/**", "/js/**", "/WEB-INF/views/**").permitAll()
					.requestMatchers("/login", "/signup").permitAll()
					.requestMatchers("/api/login", "/api/signup").permitAll()
					.anyRequest().authenticated()
			)
			.formLogin(formLogin ->
				formLogin
					.loginPage("/login")
					.usernameParameter("loginId") // 커스텀 로그인 필드 이름 설정
					.passwordParameter("password")
					.permitAll()
					.defaultSuccessUrl("/", true)
			)
			.logout((logout) -> logout
				.logoutSuccessUrl("/login")
				.invalidateHttpSession(true)
			)
			.csrf(AbstractHttpConfigurer::disable)
		;
		return http.build();
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}
