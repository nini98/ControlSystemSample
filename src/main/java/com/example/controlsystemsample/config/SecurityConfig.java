package com.example.controlsystemsample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorizeRequests ->
				authorizeRequests
					.requestMatchers("/login", "/signup", "/css/**", "/js/**", "/WEB-INF/views/**").permitAll()
					.anyRequest().authenticated()


			)
			.formLogin(formLogin ->
				formLogin
					.loginPage("/login")
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
}
