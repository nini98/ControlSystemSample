package com.example.controlsystemsample.config;

import java.time.Duration;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.example.controlsystemsample.security.CustomAuthenticationFailureHandler;
import com.example.controlsystemsample.security.CustomAuthenticationSuccessHandler;
import com.example.controlsystemsample.security.CustomLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final UserDetailsService userDetailsService;
	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorizeRequests ->
				authorizeRequests
					.requestMatchers("/css/**", "/js/**", "/webfonts/**", "/WEB-INF/views/**").permitAll()
					.requestMatchers("/login", "/signup").permitAll()
					.requestMatchers("/api/login", "/api/signup").permitAll()
					.anyRequest().authenticated()
			)
			.formLogin(formLogin ->
				formLogin
					.loginPage("/login")
					.usernameParameter("loginId") // 커스텀 로그인 필드 이름 설정
					.passwordParameter("password")
					.successHandler(customAuthenticationSuccessHandler)
					.failureHandler(customAuthenticationFailureHandler)
					.permitAll()
			)
			.logout((logout) -> logout
				.logoutUrl("/logout")
				.logoutSuccessHandler(customLogoutSuccessHandler)
				.logoutSuccessUrl("/login")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
			)
			// .sessionManagement(sessionManagement ->
			// 	sessionManagement
			// 		.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			// 		.invalidSessionUrl("/login")
			// 		.sessionFixation().none()
			// 		.maximumSessions(1).expiredUrl("/login")
			// );
			// .csrf(AbstractHttpConfigurer::disable)
		;
		return http.build();
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setHideUserNotFoundExceptions(false); // UsernameNotFoundException 이 BadCredentialsException로 감싸지는 걸 방지
		return authProvider;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	// @Bean
	// public HttpSessionEventPublisher httpSessionEventPublisher() {
	// 	return new HttpSessionEventPublisher();
	// }

}
